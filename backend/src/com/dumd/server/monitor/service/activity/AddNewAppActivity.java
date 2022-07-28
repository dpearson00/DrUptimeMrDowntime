package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.exceptions.InvalidRequestException;
import com.dumd.server.monitor.service.models.requests.AddNewAppRequest;
import com.dumd.server.monitor.service.models.results.AddNewAppResult;
import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.utils.Status;
import com.dumd.server.monitor.service.models.utils.StatusMessage;
import com.dumd.server.monitor.service.utils.converters.ModelConverterUtil;

import javax.inject.Inject;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *  Implementation of the AddNewAppActivity for the DUMDServerMonitorService AddNewApp API.
 *
 *  This API allows a user to add a new application to be monitored.
 */
public class AddNewAppActivity implements RequestHandler<AddNewAppRequest, AddNewAppResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final ApplicationDao applicationDao;

    /**
     *  Instantiates a new AddNewAppActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the applications table;
     */
    @Inject
    public AddNewAppActivity(UserDao userDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }

    /**
     *  This method handles an incoming request by created a new application.
     *
     *  if the request is sent with malformed data, an InvalidInputException will be thrown.
     *
     * @param addNewAppRequest request object containing the application's information
     * @param context
     * @return addNewAppResult result object containing the API defined {@link ApplicationModel}
     */
    @Override
    public AddNewAppResult handleRequest(final AddNewAppRequest addNewAppRequest, Context context) {
        log.info("Received AddNewAppsRequest {}", addNewAppRequest);

        Application application = new Application();
        String appId = String.valueOf(UUID.randomUUID());
        application.setAppId(appId);
        application.setAppName(addNewAppRequest.getAppName());
        application.setAppUrl(addNewAppRequest.getUrl());
        application.setDescription(addNewAppRequest.getAppDescription());
        application.setServerHistoryIds(null);
        application.setUserId(addNewAppRequest.getUserId());

        applicationDao.saveApplication(application);

        User user = userDao.getUser(addNewAppRequest.getUserId());
        user.getAppIds().add(appId);
        userDao.saveUser(user);

        return AddNewAppResult.builder()
                .withStatus(new Status(StatusMessage.SUCCESS, "200"))
                .withApplication(ModelConverterUtil.toApplicationModel(application))
                .build();
    }
}
