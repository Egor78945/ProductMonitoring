package com.example.product_manager_service.service

interface RegistrationService<S> {
    fun register(subject: S)
    fun delete(subject: S)
}