package com.example.product_manager_service.controller.product

import com.example.product_manager_service.service.product.ProductService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/product")
class ProductController(private val productService: ProductService<URI>) {
    @PostMapping("/add")
    fun addProduct(@RequestParam("url") productUrl: String?) {
        val cl = Thread.currentThread()
        println("CONTROLLER CLASS THREAD = ${cl.name}, CONTROLLER CLASS CL NAME = ${cl.contextClassLoader.name}, has = ${cl.contextClassLoader.loadClass("com.example.product_manager_service.exception.GrpcHandlerException")}")
        val senderAccountUuid: String? = SecurityContextHolder.getContext()?.authentication?.principal.toString()
        println("SENDER ACCOUNT UUID = $senderAccountUuid")
        productService.save(
            senderAccountUuid ?: throw IllegalArgumentException("sender email is missing"),
            URI(productUrl ?: throw IllegalArgumentException("product url is null"))
        )
    }
}