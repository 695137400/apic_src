package com.uzmap.pkg.uzkit.request;

public abstract class RequestCallback {
    public abstract void onFinish(HttpResult var1);

    public void onProgress(long total, double progress) {
    }
}
