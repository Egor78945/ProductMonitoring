package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.model.product.entity.Product;
import org.jooq.DSLContext;

public abstract class EntityProductRepository extends ProductRepository<Product> {
    public EntityProductRepository(DSLContext dslContext) {
        super(dslContext);
    }
}
