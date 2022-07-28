package com.dumd.server.monitor.service.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

    public static int checkStatus(String appUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(appUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.disconnect();
        return connection.getResponseCode();
    }
}
