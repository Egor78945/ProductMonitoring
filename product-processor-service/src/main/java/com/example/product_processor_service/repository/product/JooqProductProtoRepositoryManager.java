package com.example.product_processor_service.repository.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqProductProtoRepositoryManager extends JooqProductProtoRepository {
    public JooqProductProtoRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
