package com.example.product_processor_service.service.product.processor;

import com.example.product_processor_service.model.account.entity.Account;
import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.service.account.AccountService;
import com.example.product_processor_service.service.account.product.AccountProductService;
import com.example.product_processor_service.util.UrlMapper;
import com.example.product_processor_service.service.marketplace.manager.router.MarketplaceManagerRouterService;
import com.example.product_processor_service.service.product.EntityProductService;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class ProductProcessorServiceManager implements ProductProcessorService {
    private final MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService;
    private final AccountService<Account> accountService;
    private final AccountProductService<AccountProduct> accountProductService;
    private final EntityProductService entityProductService;
    private final DSLContext dslContext;

    public ProductProcessorServiceManager(MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService, AccountService<Account> accountService, AccountProductService<AccountProduct> accountProductService, EntityProductService entityProductService, DSLContext dslContext) {
        this.marketplaceManagerRouterService = marketplaceManagerRouterService;
        this.accountService = accountService;
        this.accountProductService = accountProductService;
        this.entityProductService = entityProductService;
        this.dslContext = dslContext;
    }


    @Override
    public void register(ProductPublisherDTO productDTO) {
        System.out.println("saving new product");
        URI uri = URI.create(productDTO.getProductUri());
        String baseUrl = UrlMapper.extractBaseUrl(uri.toString());
        ProductDTO product = marketplaceManagerRouterService.getByBaseUrl(baseUrl).loadProduct(uri);
        UUID accountUuid = accountService.getByUserEmail(productDTO.getPublisherEmail()).getUuid();

//        System.out.println("before save transaction, dslContext: " + dslContext);
//        dslContext.transaction(() -> {
            System.out.println("in save transaction");
            Product p = entityProductService.save(new Product(product.getUrl(), product.getName(), product.getPrice(), product.getPrice(), Timestamp.from(Instant.now())));
            System.out.println("after save product");
            accountProductService.save(new AccountProduct(p.getUrl(), accountUuid));
            System.out.println("after save account");
//        });
        System.out.println("after save transaction");
    }

    @Override
    public void update(String productUrl) {
        System.out.println("updating outdated product");
        Product product = entityProductService.getByUrl(productUrl);
        ProductDTO productDTO = marketplaceManagerRouterService.getByBaseUrl(UrlMapper.extractBaseUrl(productUrl)).loadProduct(URI.create(product.getUrl()));
        if (product.getActualPrice() != productDTO.getPrice()) {
            product.setPastPrice(product.getActualPrice());
            product.setActualPrice(productDTO.getPrice());

            entityProductService.update(product);
        }
    }
}
