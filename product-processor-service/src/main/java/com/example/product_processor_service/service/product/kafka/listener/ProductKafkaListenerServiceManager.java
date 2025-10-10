package com.example.product_processor_service.service.product.kafka.listener;

import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.service.account.AccountService;
import com.example.product_processor_service.service.common.kafka.listener.KafkaListenerService;
import com.example.product_processor_service.service.marketplace.manager.router.MarketplaceManagerRouterService;
import com.example.product_processor_service.service.product.EntityProductService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jooq.impl.QOM;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.Function;

@Service
public class ProductKafkaListenerServiceManager implements KafkaListenerService<String, ProductPublisherDTO> {
    private final MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService;
    private final AccountService<Account> accountService;
    private final EntityProductService<Product>  entityProductService;

    public ProductKafkaListenerServiceManager(MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService, AccountService<Account> accountService, EntityProductService<Product> entityProductService) {
        this.marketplaceManagerRouterService = marketplaceManagerRouterService;
        this.accountService = accountService;
        this.entityProductService = entityProductService;
    }

    @Override
    @KafkaListener(topics = "${kafka.topic.product.save.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "productPublisherListenerContainerFactory")
    public void listen(ConsumerRecord<String, ProductPublisherDTO> listenableObject) {
            System.out.println("IN ASYNC "+Thread.currentThread().getName());
            URI uri = URI.create(listenableObject.value().getProductUri());
            System.out.println("URI = " + uri);
            String baseUrl = baseUrlExtractor.apply(uri.toString());
            System.out.println("BASE URL = " + baseUrl);
            ProductDTO product = marketplaceManagerRouterService.getByBaseUrl(baseUrl).loadProduct(uri);
            System.out.println("PRODUCT = " + product);
            entityProductService.register(new Product(product.getUrl(), product.getName(), product.getPrice(), product.getPrice(), Timestamp.from(Instant.now())), accountService.getByUserEmail(listenableObject.value().getPublisherEmail()).orElseThrow().getUuid());
            System.out.println("REGISTERED");
    }

    private final Function<String, String> baseUrlExtractor = t -> {
        int c = 0;
        int i = 0;
        while (i < t.length()) {
            if (t.charAt(i) == '/') {
                c++;
            }
            if (c == 3) {
                break;
            }
            i++;
        }
        return t.substring(0, i);
    };
}
