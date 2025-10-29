package com.example.product_manager_service.controller.product

import com.example.product_manager_service.model.product.dto.ProductPublisherDto
import com.example.product_manager_service.service.product.ProductService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/product")
class ProductController(private val productService: ProductService<ProductPublisherDto>) {
    @PostMapping("/add")
    fun addProduct(@RequestParam("url") productUrl: String?) {
        val senderAccountUuid: String? = SecurityContextHolder.getContext().authentication?.principal?.toString()
        productService.save(
            ProductPublisherDto(
                senderAccountUuid ?: throw IllegalArgumentException("sender account is missing"),
                URI(productUrl ?: throw IllegalArgumentException("product url is missing"))
            )
        )
    }
}