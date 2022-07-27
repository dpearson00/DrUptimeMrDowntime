package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;

import java.util.Map;

public class CheckAppStatusActivityProvider implements RequestHandler<Map<String, String>, String> {

    public CheckAppStatusActivityProvider(){}

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        return getServiceComponent().provideCheckAppStatusActivity().handleRequest(input, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
