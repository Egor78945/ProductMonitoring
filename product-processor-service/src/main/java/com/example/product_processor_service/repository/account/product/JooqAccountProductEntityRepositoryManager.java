package com.example.product_processor_service.repository.account.product;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class JooqAccountProductEntityRepositoryManager extends JooqAccountProductEntityRepository {
    public JooqAccountProductEntityRepositoryManager(DSLContext dslContext) {
        super(dslContext);
    }
}
