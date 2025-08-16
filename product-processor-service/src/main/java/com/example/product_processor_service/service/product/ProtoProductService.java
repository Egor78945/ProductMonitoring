package com.example.product_processor_service.service.product;

import com.example.grpc.product.ProductServiceProtoConfiguration;

public interface ProtoProductService<P extends ProductServiceProtoConfiguration.ProductMessage> extends ProductService<P>{
}
