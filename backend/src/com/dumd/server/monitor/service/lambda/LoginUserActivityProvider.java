package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;
import com.dumd.server.monitor.service.models.requests.LoginUserRequest;
import com.dumd.server.monitor.service.models.results.LoginUserResult;

public class LoginUserActivityProvider implements RequestHandler<LoginUserRequest, LoginUserResult> {

    public LoginUserActivityProvider() {

    }

    @Override
    public LoginUserResult handleRequest(LoginUserRequest input, Context context) {
        return null;
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
