package com.example.product_manager_service.service.product

import java.util.*

abstract class ProductService<P>() {
    abstract fun getByAccountUuid(accountUuid: UUID, page: Int): List<P>
}