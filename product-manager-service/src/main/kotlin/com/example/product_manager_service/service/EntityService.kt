package com.example.product_manager_service.service

interface EntityService<E> {
    fun save(entity: E);
}