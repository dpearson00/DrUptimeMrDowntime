package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;
import com.dumd.server.monitor.service.models.requests.CreateAccountRequest;
import com.dumd.server.monitor.service.models.results.CreateAccountResult;

public class CreateAccountActivityProvider implements RequestHandler<CreateAccountRequest, CreateAccountResult> {

    public CreateAccountActivityProvider() {

    }

    @Override
    public CreateAccountResult handleRequest(CreateAccountRequest input, Context context) {
        return getServiceComponent().provideCreateAccountActivity().handleRequest(input, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
