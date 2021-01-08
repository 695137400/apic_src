package com.uzmap.pkg.uzkit.fineHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {
    public static final int ERROR = 0;
    public static final int TIMEOUT = 1;
    public static final int AUTH = 2;
    public static final int UN_VALID_FORMAT = 3;
    public int statusCode;
    public int errorCode;
    public String content;
    public JSONObject headers;
    public String error;
    public Object object;

    public Response(int code) {
        this.statusCode = code;
        this.errorCode = unAuth(this.statusCode) ? 2 : this.statusCode;
        this.error = this.errorCode == 2 ? "权限错误" : null;
    }

    public Response setContent(String content) {
        this.content = content;
        return this;
    }

    public Response setHeader(String key, String value) {
        if (this.headers == null) {
            this.headers = new JSONObject();
        }

        try {
            this.headers.put(key, value);
        } catch (JSONException var4) {
            var4.printStackTrace();
        }

        return this;
    }

    public Response setHeaders(JSONObject headers) {
        this.headers = headers;
        return this;
    }

    public Response setError(String error) {
        this.error = error;
        return this;
    }

    public Response setObject(Object o) {
        this.object = o;
        return this;
    }

    public Response transCache(String cache) {
        this.statusCode = 200;
        this.content = cache;
        return this;
    }

    public Response setTimeout(boolean flag) {
        if (flag) {
            this.errorCode = 1;
            this.error = "网络请求超时，请稍后重试";
        }

        return this;
    }

    public boolean success() {
        return success(this.statusCode);
    }

    public static boolean success(int status) {
        return status >= 200 && status < 300;
    }

    public static boolean unAuth(int status) {
        return status >= 401 && status < 404;
    }

    public String toString() {
        return !this.success() ? this.error : this.content;
    }
}
