package com.example.product_manager_service.service.product

interface ProductService<P> {
    fun save(publisherEmail : String, product: P);
}