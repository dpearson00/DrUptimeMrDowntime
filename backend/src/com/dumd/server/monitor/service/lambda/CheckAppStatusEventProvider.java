package com.dumd.server.monitor.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.dumd.server.monitor.service.dependency.DaggerServiceComponent;
import com.dumd.server.monitor.service.dependency.ServiceComponent;

import java.util.Map;

public class CheckAppStatusEventProvider {

    public CheckAppStatusEventProvider(){}

    public void handleRequest(Map<String, String> input, Context context) {
        getServiceComponent().provideCheckAppStatusEvent().handleRequest(input, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
