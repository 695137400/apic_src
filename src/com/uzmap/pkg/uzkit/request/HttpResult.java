package com.uzmap.pkg.uzkit.request;

import java.util.Map;

public class HttpResult {
    public static final int ERROR_NONE = -1;
    public static final int ERROR_UNKOWN = 0;
    public static final int ERROR_NETWORK = 1;
    public static final int ERROR_NO_CONNECTION = 2;
    public static final int ERROR_PARSE = 3;
    public static final int ERROR_SERVER = 4;
    public static final int ERROR_TIME_OUT = 5;
    public static final int ERROR_AUTH_FAIL = 6;
    public static final int ERROR_CANCELED = 10;
    public final int statusCode;
    private int errorCode = -1;
    public String data;
    public Map<String, String> headers;
    public long fileSize;
    public String savePath;
    public String contentType;

    public HttpResult(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setErrorType(int errorType) {
        this.errorCode = errorType;
    }

    public int getErrorType() {
        return this.errorCode;
    }

    public boolean success() {
        return this.errorCode == -1;
    }
}
