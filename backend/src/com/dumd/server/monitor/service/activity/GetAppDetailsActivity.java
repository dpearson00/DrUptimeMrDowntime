package com.dumd.server.monitor.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dynamodb.daos.ApplicationDao;
import com.dumd.server.monitor.service.dynamodb.daos.ServerHistoryDao;
import com.dumd.server.monitor.service.dynamodb.daos.UserDao;
import com.dumd.server.monitor.service.dynamodb.models.Application;
import com.dumd.server.monitor.service.dynamodb.models.ServerHistory;
import com.dumd.server.monitor.service.exceptions.InvalidRequestException;
import com.dumd.server.monitor.service.exceptions.UserNotFoundException;
import com.dumd.server.monitor.service.models.requests.GetAppDetailsRequest;
import com.dumd.server.monitor.service.models.results.GetAppDetailsResult;
import com.dumd.server.monitor.service.models.ApplicationModel;
import com.dumd.server.monitor.service.models.utils.Status;
import com.dumd.server.monitor.service.models.utils.StatusMessage;
import com.dumd.server.monitor.service.utils.converters.ModelConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  Implementation of the GetAppDetailsActivity for the DUMDServerMonitorService GetAppDetails API.
 *
 *  This API allows a user to get an application's details.
 */
public class GetAppDetailsActivity implements RequestHandler<GetAppDetailsRequest, GetAppDetailsResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final ApplicationDao applicationDao;
    private final ServerHistoryDao serverHistoryDao;

    /**
     *  Instantiates a new GetAppDetailsActivity object
     *
     * @param userDao UserDao to access the users table
     * @param applicationDao ApplicationDao to access the applications table
     */
    @Inject
    public GetAppDetailsActivity(UserDao userDao, ApplicationDao applicationDao, ServerHistoryDao serverHistoryDao) {
        this.userDao = userDao;
        this.applicationDao = applicationDao;
        this.serverHistoryDao = serverHistoryDao;
    }

    /**
     *  This method handles an incoming request by retrieving an applications details.
     *
     *  if the request is sent with an unknown appId, an ApplicationNotFoundException will be thrown.
     *
     * @param getAppDetailsRequest request object containing the appId
     * @param context
     * @return getAppDetailsResult result object containing the API defined {@link ApplicationModel}
     */
    @Override
    public GetAppDetailsResult handleRequest(final GetAppDetailsRequest getAppDetailsRequest, Context context) {
        log.info("Received GetAppDetailsRequest {}", getAppDetailsRequest);

        if (getAppDetailsRequest.getAppId() == null) {
            throw new InvalidRequestException("No AppId provided. Please enter valid Application Id.");
        }

        Application app = applicationDao.getApplication(getAppDetailsRequest.getAppId());
        ServerHistory sh = serverHistoryDao.getServerHistory(app.getServerHistoryId());


        return GetAppDetailsResult.builder()
                .withStatus(new Status(StatusMessage.SUCCESS, "200"))
                .withApplication(ModelConverterUtil.toApplicationModel(app))
                .withErrorLogs(sh.getErrorLogs())
                .build();
    }
}
