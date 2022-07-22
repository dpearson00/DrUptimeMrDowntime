package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;
import com.dumd.server.monitor.service.models.requests.DeleteAppRequest;
import com.dumd.server.monitor.service.models.results.DeleteAppResult;

public class DeleteAppActivityProvider implements RequestHandler<DeleteAppRequest, DeleteAppResult> {

    public DeleteAppActivityProvider() {

    }

    @Override
    public DeleteAppResult handleRequest(DeleteAppRequest input, Context context) {
        return getServiceComponent().provideDeleteAppActivity().handleRequest(input, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
