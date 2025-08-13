package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.model.product.entity.Product;

public abstract class EntityProductRepository<P extends Product> extends ProductRepository<P> {
}
