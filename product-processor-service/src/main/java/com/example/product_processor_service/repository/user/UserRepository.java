package com.example.product_processor_service.repository.user;

import com.example.product_processor_service.model.user.entity.User;
import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;
import java.util.UUID;

public abstract class UserRepository extends JooqRepository<User> {
    protected UserRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public User save(User user) {
        return dslContext
                .insertInto(Tables.USERS)
                .set(Tables.USERS.UUID, user.getUuid())
                .set(Tables.USERS.EMAIL, user.getEmail())
                .set(Tables.USERS.REGISTERED_AT, user.getRegistered_at().toLocalDateTime())
                .set(Tables.USERS.STATUS_ID, user.getStatus_id())
                .returning()
                .fetchOneInto(User.class);
    }

    @Override
    public User update(User user) {
        return dslContext
                .update(Tables.USERS)
                .set(Tables.USERS.UUID, user.getUuid())
                .set(Tables.USERS.EMAIL, user.getEmail())
                .set(Tables.USERS.REGISTERED_AT, user.getRegistered_at().toLocalDateTime())
                .set(Tables.USERS.STATUS_ID, user.getStatus_id())
                .returning()
                .fetchOneInto(User.class);
    }

    @Override
    public void saveAll(List<User> userList) {
        for (User user : userList) {
            save(user);
        }
    }

    public User getByUuid(UUID uuid) {
        return dslContext
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.UUID.eq(uuid))
                .fetchOneInto(User.class);
    }

    public List<User> findAllByProductUrl(String url) {
        return dslContext
                .selectDistinct(Tables.USERS)
                .from(Tables.USERS.join(Tables.ACCOUNT)
                        .on(Tables.USERS.UUID.eq(Tables.ACCOUNT.USER_UUID))
                        .join(Tables.ACCOUNT_PRODUCTS)
                        .on(Tables.ACCOUNT.UUID.eq(Tables.ACCOUNT_PRODUCTS.ACCOUNT_UUID))
                        .join(Tables.PRODUCT)
                        .on(Tables.ACCOUNT_PRODUCTS.PRODUCT_URL.eq(Tables.PRODUCT.URL)))
                .where(Tables.PRODUCT.URL.eq(url))
                .fetchInto(User.class);
    }
}
