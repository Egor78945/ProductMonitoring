package com.example.product_manager_service.model.product.dto

import java.io.Serializable

class ProductPublisherDto() : Serializable{
    var publisherEmail: String = ""
    var productUri: String = ""
    constructor(publisherEmail: String, productUri: String) : this() {
        this.publisherEmail = publisherEmail
        this.productUri = productUri
    }
}