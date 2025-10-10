package com.example.product_processor_service.repository.product;

import com.example.grpc.product.ProductServiceProtoConfiguration;
import org.jooq.DSLContext;

public abstract class ProtoProductRepository<P extends ProductServiceProtoConfiguration.ProductMessage> extends ProductRepository<P> {
    public ProtoProductRepository(DSLContext dslContext) {
        super(dslContext);
    }
}
