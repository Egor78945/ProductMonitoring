package com.example.product_manager_service.exception

class GrpcHandlerException : RuntimeException {
    constructor(e: Exception) : super(e)

    constructor(message: String) : super(message)
}
