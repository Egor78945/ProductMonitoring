package com.example.product_processor_service.repository.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProtoProductRepositoryManager extends ProtoProductRepository{
    public ProtoProductRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
