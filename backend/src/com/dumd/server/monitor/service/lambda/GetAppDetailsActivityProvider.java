package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;
import com.dumd.server.monitor.service.models.requests.GetAppDetailsRequest;
import com.dumd.server.monitor.service.models.results.GetAppDetailsResult;

public class GetAppDetailsActivityProvider implements RequestHandler<GetAppDetailsRequest, GetAppDetailsResult> {

    public GetAppDetailsActivityProvider() {

    }

    @Override
    public GetAppDetailsResult handleRequest(GetAppDetailsRequest input, Context context) {
        return null;
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
