package com.uzmap.pkg.uzkit.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;

public final class c {
    public static final String b = com.uzmap.pkg.uzapp.b.h() + "/AM_Service_API/StatisticAnalysis";
    public static final String c = com.uzmap.pkg.uzapp.b.h() + "/AM_Service_API/GeoDataReport";
    public static final String d = com.uzmap.pkg.uzapp.b.c() + "/AM_Service_API/StartupReport";
    public static final String e = com.uzmap.pkg.uzapp.b.e() + "/getUserStatus";
    public static boolean a = com.uzmap.pkg.uzapp.b.o();
    private static c t;
    public DisplayMetrics f;
    public PackageInfo g;
    public ApplicationInfo h;
    public int i;
    public int j;
    public String k;
    public String l;
    public String m;
    public int n;
    public String o;
    public String p;
    public String q;
    public String r;
    public boolean s;

    private c(Context context) {
        this.o = VERSION.RELEASE;
        this.p = "android";
        this.q = !TextUtils.isEmpty(Build.DEVICE) ? Build.DEVICE : "unknown";
        this.s = false;
        this.b(context);
    }

    public static c a(Context context) {
        if (t == null) {
            t = new c(context);
        }

        return t;
    }

    public static c a() {
        if (t == null) {
            throw new IllegalAccessError("DeviceAndAppInfo is not initialize");
        } else {
            return t;
        }
    }

    private void b(Context context) {
        this.f = context.getResources().getDisplayMetrics();
        String pkg = context.getPackageName();
        PackageManager pkm = context.getPackageManager();
        char flags = '\uffff';

        try {
            this.g = pkm.getPackageInfo(pkg, flags);
            this.h = pkm.getApplicationInfo(pkg, flags);
            this.k = this.g.versionName;
            this.l = this.h.loadLabel(pkm).toString();
            this.n = this.g.versionCode;
            this.r = this.g.packageName;
            this.i = this.f.heightPixels;
            this.j = this.f.widthPixels;
            Bundle metaData = this.h.metaData;
            if (metaData != null) {
                this.m = metaData.getString("uz_version");
                if (this.m == null) {
                    this.m = "1.2.0";
                }
            }

            if (a) {
                this.k = com.uzmap.pkg.uzapp.b.p();
            }

            this.s = "sdk".equals(com.uzmap.pkg.uzapp.b.r());
        } catch (NameNotFoundException var6) {
            var6.printStackTrace();
        }

    }
}
