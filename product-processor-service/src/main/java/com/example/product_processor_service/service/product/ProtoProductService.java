package com.example.product_processor_service.service.product;

import com.example.grpc.product.ProductServiceProtoConfiguration;

public interface ProtoProductService extends ProductService<ProductServiceProtoConfiguration.ProductMessage> {
}
