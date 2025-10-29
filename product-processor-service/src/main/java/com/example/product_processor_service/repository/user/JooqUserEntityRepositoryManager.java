package com.example.product_processor_service.repository.user;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqUserEntityRepositoryManager extends JooqUserEntityRepository{
    public JooqUserEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
