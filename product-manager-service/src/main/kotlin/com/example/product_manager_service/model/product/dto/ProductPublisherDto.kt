package com.example.product_manager_service.model.product.dto

import java.io.Serializable

data class ProductPublisherDto(val publisherEmail: String, val productUri: String) : Serializable{
}