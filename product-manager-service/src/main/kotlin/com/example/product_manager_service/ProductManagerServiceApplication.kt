package com.example.product_manager_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductManagerServiceApplication

fun main(args: Array<String>) {
    val cl = Thread.currentThread()
    println("MAIN CLASS THREAD = ${cl.name}, MAIN CLASS CL NAME = ${cl.contextClassLoader.name}, has = ${cl.contextClassLoader.loadClass("com.example.product_manager_service.exception.GrpcHandlerException")}")
    runApplication<ProductManagerServiceApplication>(*args)
}
