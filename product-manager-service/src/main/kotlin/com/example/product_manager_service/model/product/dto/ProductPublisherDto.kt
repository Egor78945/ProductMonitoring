package com.example.product_manager_service.model.product.dto

import java.io.Serializable
import java.net.URI

data class ProductPublisherDto(val publisherAccountUuid: String?, val productUri: URI?) : Serializable{
    constructor() : this(null, null) {
    }
}