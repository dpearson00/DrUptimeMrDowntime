package com.dumd.server.monitor.service.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HTTPRequest {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

    public static int checkStatus(String appUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(appUrl).openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.disconnect();
            return connection.getResponseCode();
        } catch (SocketTimeoutException e) {
            return -1;
        } catch (IOException e) {
            return -2;
        }
    }
}
