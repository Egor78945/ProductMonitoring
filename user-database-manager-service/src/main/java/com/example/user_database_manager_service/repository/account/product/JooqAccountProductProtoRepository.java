package com.example.user_database_manager_service.repository.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.product.mapper.AccountProductMapper;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.net.URI;
import java.util.UUID;

public abstract class JooqAccountProductProtoRepository extends JooqAccountProductRepository<UserProtoConfiguration.AccountProductMessage> {
    public JooqAccountProductProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage save(UserProtoConfiguration.AccountProductMessage entity) {
        return dslContext
                .insertInto(Tables.ACCOUNT_PRODUCTS)
                .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, UUID.fromString(entity.getAccountUuid()))
                .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, entity.getProductUrl())
                .returning()
                .fetchOne(AccountProductMapper::mapTo);
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage update(UserProtoConfiguration.AccountProductMessage entity) {
        return dslContext
                .update(Tables.ACCOUNT_PRODUCTS)
                .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, UUID.fromString(entity.getAccountUuid()))
                .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, entity.getProductUrl())
                .returning()
                .fetchOne(AccountProductMapper::mapTo);
    }
}
