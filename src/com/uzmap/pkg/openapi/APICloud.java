package com.uzmap.pkg.openapi;

import android.app.Application;
import android.content.Context;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.b;

public class APICloud {
    private Application a;
    private b b;
    private static APICloud c;

    private APICloud(Context context) {
        this.a = (Application) context.getApplicationContext();
        this.b = com.uzmap.pkg.uzcore.b.a(UZCoreUtil.isMainProcess(context));
        this.b.a(this.a);
    }

    public static APICloud initialize(Context context) {
        if (c == null) {
            c = new APICloud(context);
        }

        return c;
    }

    public static APICloud get() {
        if (c == null) {
            throw new RuntimeException("You must call APICloud.initialize at first!");
        } else {
            return c;
        }
    }

    public Context getContext() {
        return this.a;
    }

    public void onResume() {
    }

    public void onPause() {
    }
}
