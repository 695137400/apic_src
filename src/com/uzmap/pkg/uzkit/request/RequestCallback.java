//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit.request;

public abstract class RequestCallback {
    public abstract void onFinish(HttpResult var1);

    public RequestCallback() {
    }

    public void onProgress(long total, double progress) {
    }
}
