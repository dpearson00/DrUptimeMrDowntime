package com.dumd.server.monitor.service.utils.converters;

import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.models.UserModel;

public class ModelConverterUtil {

    public static UserModel toUserModel(User user) {

        return UserModel.builder()
                .withUserId(user.getUserId())
                .withName(user.getCustomerName())
                .withEmail(user.getEmail())
                .withPhoneNumber(user.getPhoneNumber())
                .withAppIds(user.getAppIds())
                .build();
    }
}
