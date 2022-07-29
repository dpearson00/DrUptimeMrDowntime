package com.dumd.server.monitor.service.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    public static final String URL_REGEX = "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)";

    public static boolean validateURL(String url) {
        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(url);
        return m.find();
    }
}
