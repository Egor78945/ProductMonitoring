package com.example.user_database_manager_service.repository.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.UUID;

public abstract class JooqAccountProductProtoRepository extends JooqAccountProductRepository<UserProtoConfiguration.AccountUuidProductUriMessage> {
    public JooqAccountProductProtoRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserProtoConfiguration.AccountUuidProductUriMessage save(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
        return dslContext
                .insertInto(Tables.ACCOUNT_PRODUCTS)
                .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, UUID.fromString(entity.getAccountUuid()))
                .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, entity.getProductUri())
                .returning()
                .fetchOne(r -> entity);
    }

    @Override
    public UserProtoConfiguration.AccountUuidProductUriMessage update(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
        return dslContext
                .update(Tables.ACCOUNT_PRODUCTS)
                .set(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID, UUID.fromString(entity.getAccountUuid()))
                .set(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL, entity.getProductUri())
                .returning()
                .fetchOne(r -> entity);
    }
}
