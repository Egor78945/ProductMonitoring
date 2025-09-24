package com.example.product_manager_service.controller.product

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/product")
class ProductController {
    @PostMapping("/add")
    fun addProduct(@RequestParam("url") productUrl: String?) {

    }
}