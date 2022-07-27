package com.dumd.server.monitor.service.utils.converters;

import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.UserModel;

import java.util.ArrayList;
import java.util.List;

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

    public static ApplicationModel toApplicationModel(Application application) {

        return ApplicationModel.builder()
                .withAppId(application.getAppId())
                .withName(application.getAppName())
                .withAppUrl(application.getAppUrl())
                .withUserId(application.getUserId())
                .withDescription(application.getDescription())
                .withServerHistoryId(application.getServerHistoryId())
                .build();
    }

    public static List<ApplicationModel> toApplicationModelList(List<Application> applicationList) {
        List<ApplicationModel> applicationModelList = new ArrayList<>();

        for(Application app : applicationList) {
            applicationModelList.add(toApplicationModel(app));
        }

        return applicationModelList;
    }
}
