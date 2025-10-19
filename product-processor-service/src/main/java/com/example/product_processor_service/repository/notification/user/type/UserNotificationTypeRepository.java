package com.example.product_processor_service.repository.notification.user.type;

import com.example.product_processor_service.model.notification.user.type.entity.UserNotificationType;
import com.example.product_processor_service.repository.JooqRepository;
import nu.studer.sample.Tables;
import org.jooq.DSLContext;

import java.util.List;

public abstract class UserNotificationTypeRepository extends JooqRepository<UserNotificationType> {
    public UserNotificationTypeRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    public UserNotificationType save(UserNotificationType userNotificationType) {
        return dslContext
                .insertInto(Tables.USER_NOTIFICATION_TYPE)
                .set(Tables.USER_NOTIFICATION_TYPE.NAME, userNotificationType.getName())
                .returning()
                .fetchOneInto(UserNotificationType.class);
    }

    @Override
    public UserNotificationType update(UserNotificationType userNotificationType) {
        return dslContext
                .update(Tables.USER_NOTIFICATION_TYPE)
                .set(Tables.USER_NOTIFICATION_TYPE.NAME, userNotificationType.getName())
                .returning()
                .fetchOneInto(UserNotificationType.class);
    }

    @Override
    public void saveAll(List<UserNotificationType> userNotificationTypeList) {
        for(UserNotificationType userNotificationType : userNotificationTypeList){
            save(userNotificationType);
        }
    }
}
