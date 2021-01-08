package com.uzmap.pkg.openapi;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.e;

public abstract class Html5EventListener {
    private final int a;

    public Html5EventListener(String eventName) {
        if (TextUtils.isEmpty(eventName)) {
            throw new IllegalArgumentException("eventName can not be empty");
        } else {
            this.a = e.a(eventName);
        }
    }

    public final boolean matching(int h5event) {
        return this.a == h5event;
    }

    public abstract void onReceive(WebViewProvider var1, Object var2);
}
