package com.example.product_processor_service.repository.product;

import com.example.grpc.product.ProductServiceProtoConfiguration;
import org.jooq.DSLContext;

public abstract class ProtoProductRepository extends ProductRepository<ProductServiceProtoConfiguration.ProductMessage> {
    public ProtoProductRepository(DSLContext dslContext) {
        super(dslContext);
    }
}
