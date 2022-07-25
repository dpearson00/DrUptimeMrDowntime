package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.dynamodb.models.User;
import com.dumd.server.monitor.service.exceptions.InvalidRequestException;
import com.dumd.server.monitor.service.exceptions.UserNotFoundException;
import com.dumd.server.monitor.service.models.requests.GetUserAppsRequest;
import com.dumd.server.monitor.service.models.results.GetUserAppsResult;
import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.utils.Status;
import com.dumd.server.monitor.service.models.utils.StatusMessage;
import com.dumd.server.monitor.service.utils.converters.ModelConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *  Implementation fo the GetUserAppsActivity for the DUMDServerMonitorService GetUserApps API.
 *
 *  This API allows a customer to get all the apps associated with their account.
 */
public class GetUserAppsActivity implements RequestHandler<GetUserAppsRequest, GetUserAppsResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final ApplicationDao applicationDao;

    /**
     *  Instantiates a new GetUserAppsActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the application table
     */
    @Inject
    public GetUserAppsActivity(UserDao userDao, ApplicationDao applicationDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
    }

    /**
     *  This method handles an incoming request by retrieving all of a user's apps.
     *
     *  if the request is sent with an unknown userId, an UnknownUserException will be thrown.
     *
     * @param getUserAppsRequest request object containing a userId
     * @param context
     * @return getUserAppsResult result object containing a list of {@link ApplicationModel}
     */
    @Override
    public GetUserAppsResult handleRequest(final GetUserAppsRequest getUserAppsRequest, Context context) {
        log.info("Received GetUserAppsRequest {}", getUserAppsRequest);

        User user = userDao.getUser(getUserAppsRequest.getUserId());

        if (getUserAppsRequest.getUserId() == null) {
            throw new InvalidRequestException("No UserId provided. Please enter valid User Id.");
        }

        if (user == null) {
            throw new UserNotFoundException(String.format("There is no User with User Id: %s", getUserAppsRequest.getUserId()));
        }

        List<Application> applications = new ArrayList<>();

        for (String appId : user.getAppIds()) {
            applications.add(applicationDao.getApplication(appId));
        }

        // Dummy return statement
        return GetUserAppsResult.builder()
                .withStatus(new Status(StatusMessage.SUCCESS, "200"))
                .withApplications(ModelConverterUtil.toApplicationModelList(applications))
                .build();
    }

}
