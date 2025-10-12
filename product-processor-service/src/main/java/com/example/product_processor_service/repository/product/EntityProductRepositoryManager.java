package com.example.product_processor_service.repository.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class EntityProductRepositoryManager extends EntityProductRepository {
    public EntityProductRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
