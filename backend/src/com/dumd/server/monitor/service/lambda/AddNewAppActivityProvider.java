package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;
import com.dumd.server.monitor.service.models.requests.AddNewAppRequest;
import com.dumd.server.monitor.service.models.results.AddNewAppResult;

public class AddNewAppActivityProvider implements RequestHandler<AddNewAppRequest, AddNewAppResult> {

    public AddNewAppActivityProvider() {

    }

    @Override
    public AddNewAppResult handleRequest(final AddNewAppRequest input, Context context) {
        return getServiceComponent().provideAddNewAppActivity().handleRequest(input, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
