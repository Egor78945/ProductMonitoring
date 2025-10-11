package com.example.product_processor_service.service.product.processor;

import com.example.product_processor_service.model.product.entity.Product;
import com.example.product_processor_service.repository.product.EntityProductRepository;
import org.jooq.DatePart;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductProcessorServiceManager implements ProductProcessorService {
    private final EntityProductRepository entityProductRepository;

    public ProductProcessorServiceManager(EntityProductRepository entityProductRepository) {
        this.entityProductRepository = entityProductRepository;
    }

    @Override
    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void updateOutdated() {
        System.out.println("update outdated");
        List<Product> outdatedProducts = entityProductRepository.getAllExpired(1, DatePart.MINUTE, 10);
        System.out.println("outdatedProducts: " + outdatedProducts.toString());
    }
}
