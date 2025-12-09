package com.example.product_processor_service.service.product.processor;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.product_processor_service.model.product.ProductDTO;
import com.example.product_processor_service.model.product.ProductPublisherDTO;
import com.example.product_processor_service.model.product.ProductRegistrationModel;
import com.example.product_processor_service.service.product.ProductRegistrationService;
import com.example.product_processor_service.service.marketplace.manager.router.MarketplaceManagerRouterService;
import com.example.product_processor_service.service.product.ProductService;
import com.example.product_processor_service.util.mapper.UrlMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Service
public class ProductProcessorServiceManager implements ProductProcessorService {
    private final MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService;
    private final ProductRegistrationService productRegistrationService;
    private final ProductService<UserProtoConfiguration.ProductMessage> productService;

    public ProductProcessorServiceManager(MarketplaceManagerRouterService<ProductDTO> marketplaceManagerRouterService, ProductRegistrationService productRegistrationService, ProductService<UserProtoConfiguration.ProductMessage> productService) {
        this.marketplaceManagerRouterService = marketplaceManagerRouterService;
        this.productRegistrationService = productRegistrationService;
        this.productService = productService;
    }

    @Override
    public void register(ProductPublisherDTO productDTO) {
        URI uri = URI.create(productDTO.getProductUri());
        String baseUrl = UrlMapper.extractBaseUrl(uri.toString());

        System.out.println("product register before load");

        ProductDTO product = marketplaceManagerRouterService.getByBaseUrl(baseUrl).loadProduct(uri);

        System.out.println("product register loaded: " + product);

        productRegistrationService.register(new ProductRegistrationModel(product.getUrl(), product.getName(), product.getPrice(), product.getPrice(), new Date().toInstant().toEpochMilli(), productDTO.getPublisherAccountUuid()));
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

    @Override
    public void delete(ProductPublisherDTO accountProductDeleteDTO) {
        System.out.println("deleting product of: " +  accountProductDeleteDTO);
        productService.deleteByAccountUuidAndProductUrl(UUID.fromString(accountProductDeleteDTO.getPublisherAccountUuid()), URI.create(accountProductDeleteDTO.getProductUri()));
        System.out.println("product deleted: " + accountProductDeleteDTO);
    }
}
