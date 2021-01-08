package com.uzmap.pkg.uzsocket;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.uzmap.pkg.uzsocket.g.c;

public class UPnsService extends Service {
    private c a = new c();

    public UPnsService() {
        this.a.a(this);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        this.a.b();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return this.a.a(intent, flags, startId);
    }

    public void onDestroy() {
        this.a.c();
        this.a = null;
    }

    public static void a(Context ctx, Bundle data) {
        Intent intent = new Intent();
        intent.putExtra("operate", 1);
        if (data != null) {
            intent.putExtra("data", data);
        }

        a(ctx, intent);
    }

    public static void a(Context ctx, boolean flag, Bundle data) {
        Intent intent = new Intent();
        intent.putExtra("operate", 2);
        intent.putExtra("value", flag);
        if (data != null) {
            intent.putExtra("data", data);
        }

        a(ctx, intent);
    }

    public static void a(Context ctx) {
        Intent intent = new Intent();
        intent.putExtra("operate", 4);
        a(ctx, intent);
    }

    private static void a(Context ctx, Intent intent) {
        intent.setClass(ctx, UPnsService.class);
        intent.setAction(com.uzmap.pkg.uzsocket.g.a.a);
        ctx.startService(intent);
    }
}
