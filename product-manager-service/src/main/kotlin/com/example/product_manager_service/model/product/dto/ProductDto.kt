package com.example.product_manager_service.model.product.dto

import java.net.URI
import java.util.Date

data class ProductDto(val uri: URI, val name: String, val actualPrice: Int, val pastPrice: Int, val updatedAt: Date) {
    override fun toString(): String {
        return "ProductDto(uri=$uri, name='$name', actualPrice=$actualPrice, pastPrice=$pastPrice, updatedAt=$updatedAt)"
    }
}