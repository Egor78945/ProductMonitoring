package com.example.product_processor_service.repository.product;

import com.example.product_processor_service.model.product.entity.Product;
import org.jooq.DatePart;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public abstract class ProductRepository<P extends Product> {
    public abstract void save(P product);
    public abstract void saveAll(List<P> products);
    public abstract Optional<P> getByUrl(String url);
    public abstract List<P> getExpired(Timestamp now, int limit, DatePart datePart);
    public abstract boolean existsByUrl(String url);
}
