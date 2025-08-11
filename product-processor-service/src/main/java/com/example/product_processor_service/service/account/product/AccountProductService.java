package com.example.product_processor_service.service.account.product;

import com.example.product_processor_service.model.product.entity.Product;

import java.util.List;
import java.util.UUID;

public interface AccountProductService <P extends Product>{
    P getByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);
    List<P> getAllByAccountUuid(UUID accountUuid, int page, int size);
    boolean existsByAccountUuidAndProductUrl(UUID accountUuid, String productUrl);
}
