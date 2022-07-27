package com.dumd.server.monitor.service;


import com.dumd.server.monitor.service.lambda.CheckAppStatusActivityProvider;

public class Runner {
    public static void main(String[] args) {
        CheckAppStatusActivityProvider ch = new CheckAppStatusActivityProvider();
        ch.handleRequest(null, null);
    }
}
