package com.example.product_processor_service.repository.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqProductEntityRepositoryManager extends JooqProductEntityRepository {
    public JooqProductEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
