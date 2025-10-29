package com.example.product_processor_service.service.product.processor;

import com.example.product_processor_service.enumeration.UserNotificationMessageEnumeration;
import com.example.product_processor_service.enumeration.UserNotificationTypeEnumeration;
import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.model.mail.dto.MailMessage;
import com.example.product_processor_service.model.notification.user.UserNotification;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.model.user.entity.User;
import com.example.product_processor_service.service.account.AccountService;
import com.example.product_processor_service.service.account.product.AccountProductService;
import com.example.product_processor_service.service.common.messaging.MessagingService;
import com.example.product_processor_service.service.marketplace.manager.router.MarketplaceManagerRouterService;
import com.example.product_processor_service.service.notification.user.UserNotificationService;
import com.example.product_processor_service.service.product.EntityProductService;
import com.example.product_processor_service.service.user.UserService;
import com.example.product_processor_service.service.user.messaging.UserMailMessagingService;
import com.example.product_processor_service.util.UrlMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductProcessorServiceManager implements ProductProcessorService {
    private final MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService;
    private final AccountProductService<AccountProduct> accountProductService;
    private final EntityProductService entityProductService;
    private final UserService<User> userService;
    private final UserMailMessagingService messagingService;
    private final UserNotificationService<UserNotification> userNotificationService;

    public ProductProcessorServiceManager(MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService, AccountProductService<AccountProduct> accountProductService, EntityProductService entityProductService, UserService<User> userService, UserMailMessagingService messagingService, UserNotificationService<UserNotification> userNotificationService) {
        this.marketplaceManagerRouterService = marketplaceManagerRouterService;
        this.accountProductService = accountProductService;
        this.entityProductService = entityProductService;
        this.userService = userService;
        this.messagingService = messagingService;
        this.userNotificationService = userNotificationService;
    }


    @Override
    public void register(ProductPublisherDTO productDTO) {
        System.out.println("saving new product");
        URI uri = URI.create(productDTO.getProductUri());
        String baseUrl = UrlMapper.extractBaseUrl(uri.toString());

        System.out.println("uri = " + uri + " baseUrl = " + baseUrl);
        ProductDTO product = marketplaceManagerRouterService.getByBaseUrl(baseUrl).loadProduct(uri);

//        System.out.println("before save transaction, dslContext: " + dslContext);
//        dslContext.transaction(() -> {
        System.out.println("in save transaction");
        Product p = entityProductService.save(new Product(product.getUrl(), product.getName(), product.getPrice(), product.getPrice(), Timestamp.from(Instant.now())));
        System.out.println("after save product");
        accountProductService.save(new AccountProduct(p.getUrl(), UUID.fromString(productDTO.getPublisherAccountUuid())));
        System.out.println("after save account");
//        });
        System.out.println("after save transaction");
    }

    @Override
    public void update(String productUrl) {
        System.out.println("updating outdated product");
        Product product = entityProductService.getByUrl(productUrl);
        ProductDTO productDTO = marketplaceManagerRouterService.getByBaseUrl(UrlMapper.extractBaseUrl(productUrl)).loadProduct(URI.create(product.getUrl()));
//        if (product.getActualPrice() != productDTO.getPrice()) {
            product.setPastPrice(product.getActualPrice());
            product.setActualPrice(productDTO.getPrice());
            entityProductService.update(product);
            List<User> users = userService.getAllByProductUrl(URI.create(productUrl));
            for (User user : users) {
                UserNotification userNotification;
                System.out.println("USER -> " + user);
                if(userNotificationService.existsByUserUuidAndNotificationTypeId(user.getUuid(), UserNotificationTypeEnumeration.PRODUCT_UPDATED.getId())) {
                    System.out.println("EXISTS");
                    userNotification = userNotificationService.findByUserUuidAndNotificationTypeId(user.getUuid(), UserNotificationTypeEnumeration.PRODUCT_UPDATED.getId());
                    System.out.println("USER NOTIFICATION -> " + userNotification);
                    if(LocalDateTime.now().minusHours(userNotification.getNotifiedAt().toLocalDateTime().getHour()).getHour() > 12) {
                        userNotification.setNotifiedAt(Timestamp.from(Instant.now()));
                        System.out.println("UPDATING USER NOTIFICATION -> " + userNotification);
                        userNotificationService.update(userNotification);
                        System.out.println("UPDATED USER NOTIFICATION -> " + userNotification);
                        messagingService.sendMessage(new MailMessage(user.getEmail(), UserNotificationMessageEnumeration.PRODUCT_UPDATED.getMessage()));
                        System.out.println("SENT TO TOPIC");
                    }
                } else {
                    userNotification = new UserNotification(user.getUuid(), UserNotificationTypeEnumeration.PRODUCT_UPDATED.getId(), Timestamp.from(Instant.now()));
                    System.out.println("SAVING USER NOTIFICATION -> " + userNotification);
                    userNotificationService.save(userNotification);
                    System.out.println("SAVED USER NOTIFICATION -> " + userNotification);
                    messagingService.sendMessage(new MailMessage(user.getEmail(), UserNotificationMessageEnumeration.PRODUCT_UPDATED.getMessage()));
                    System.out.println("SENT TO TOPIC");
                }
            }
//        }
    }
}
