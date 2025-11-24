package com.example.product_processor_service.service.product.processor;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.example.product_processor_service.service.account.product.AccountProductService;
import com.example.product_processor_service.util.mapper.UserGrpcMapper;
import com.example.product_processor_service.service.marketplace.manager.router.MarketplaceManagerRouterService;
import com.example.product_processor_service.service.product.ProductService;
import com.example.product_processor_service.service.product.mapper.ProductMapper;
import com.example.product_processor_service.util.mapper.UrlMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Service
public class ProductProcessorServiceManager implements ProductProcessorService {
    private final MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService;
    private final AccountProductService<UserProtoConfiguration.AccountProductMessage> accountProductService;
    private final ProductService<UserProtoConfiguration.ProductMessage> productService;

    public ProductProcessorServiceManager(MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService, AccountProductService<UserProtoConfiguration.AccountProductMessage> accountProductService, ProductService<UserProtoConfiguration.ProductMessage> productService) {
        this.marketplaceManagerRouterService = marketplaceManagerRouterService;
        this.accountProductService = accountProductService;
        this.productService = productService;
    }

    @Override
    public void register(ProductPublisherDTO productDTO) {
        URI uri = URI.create(productDTO.getProductUri());
        String baseUrl = UrlMapper.extractBaseUrl(uri.toString());

        System.out.println("product register before load");

        ProductDTO product = marketplaceManagerRouterService.getByBaseUrl(baseUrl).loadProduct(uri);

        System.out.println("product register loaded: " + product);

        UserProtoConfiguration.ProductMessage p = productService.save(ProductMapper.mapTo(product.getUrl(), product.getName(), product.getPrice(), product.getPrice(), new Date().toInstant().toEpochMilli()));
        accountProductService.save(UserGrpcMapper.mapTo(p.getUrl(), UUID.fromString(productDTO.getPublisherAccountUuid()).toString()));
    }

    @Override
    public void update(String productUrl) {
        System.out.println("updating product...");
        UserProtoConfiguration.ProductMessage product = productService.getByUrl(productUrl);
        System.out.println("update from database: " + product);
        System.out.println("product update before load");
        ProductDTO productDTO = marketplaceManagerRouterService.getByBaseUrl(UrlMapper.extractBaseUrl(productUrl)).loadProduct(URI.create(product.getUrl()));
        System.out.println("product update loaded: " + productDTO);
        if (product.getActualPrice() != productDTO.getPrice()) {
            System.out.println("price changed !");
            product = product.toBuilder()
                    .setPastPrice(product.getActualPrice())
                    .setActualPrice(productDTO.getPrice())
                    .setUpdatedAt(new Date().toInstant().toEpochMilli())
                    .build();
            productService.update(product);
        }
    }
}
