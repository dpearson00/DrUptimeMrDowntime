package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;
import com.dumd.server.monitor.service.models.requests.GetUserAppsRequest;
import com.dumd.server.monitor.service.models.results.GetUserAppsResult;

public class GetUserAppsActivityProvider implements RequestHandler<GetUserAppsRequest, GetUserAppsResult> {

    public GetUserAppsActivityProvider() {

    }

    @Override
    public GetUserAppsResult handleRequest(GetUserAppsRequest input, Context context) {
        return getServiceComponent().provideGetUserAppsActivity().handleRequest(input, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
