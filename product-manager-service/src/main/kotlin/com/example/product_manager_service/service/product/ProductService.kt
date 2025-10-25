package com.example.product_manager_service.service.product

interface ProductService<P> {
    fun save(publisherUsername : String, product: P);
}