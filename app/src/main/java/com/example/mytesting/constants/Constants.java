package com.example.mytesting.constants;

import com.example.mytesting.BuildConfig;

import java.util.regex.Pattern;

public class Constants {
    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);

    public static final String DB_NAME = "app_database.db";

    public static final int HTTP_STATUS_SUCCESS = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_UN_AUTHORIZE = 401;
    public static final int HTTP_STATUS_FORBIDDEN_ACCESS = 403;
    public static final int HTTP_SERVER_ERROR = 500;
    public static final int HTTP_SERVICE_UNAVAILABLE = 503;
    public static final int HTTP_GATEWAY_TIMEOUT = 504;

    public static final String INTENT_USERNAME_EXTRA = "intent_username_extra";
}
