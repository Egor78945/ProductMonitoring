package com.example.product_manager_service.model.product.dto

import java.io.Serializable

class ProductPublisherDto() : Serializable{
    var publisherAccountUuid: String = ""
    var productUri: String = ""
    constructor(publisherAccountUuid: String, productUri: String) : this() {
        this.publisherAccountUuid = publisherAccountUuid
        this.productUri = productUri
    }
}