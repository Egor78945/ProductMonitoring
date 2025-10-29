package com.example.product_processor_service.repository;

import com.example.product_processor_service.util.function.Scrypt;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {
    public abstract T save(T product);

    public List<T> saveAll(List<T> entityList) {
        List<T> resultList = new ArrayList<>();
        for (T user : entityList) {
            resultList.add(save(user));
        }
        return resultList;
    }

    public abstract T update(T product);

    public abstract void transactional(Scrypt scrypt);
}
