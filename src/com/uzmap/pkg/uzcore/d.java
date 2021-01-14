package com.uzmap.pkg.uzcore;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import com.uzmap.pkg.uzapp.PropertiesUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class d {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String f;
    public static final String g;
    public static final String h;
    public static final String i;
    private static final List<d.a> C = new ArrayList();
    private static final List<d.a> D;
    public static boolean j = false;
    public static boolean v = PropertiesUtil.b();
    public static boolean z = false;
    public static boolean A = false;
    private static d B;

    static {
        C.add(new d.a("meizu", "4.1.1"));
        D = new ArrayList();
        D.add(new d.a("letv", ""));
        Locale language = Locale.getDefault();
        if (!language.equals(Locale.CHINA) && !language.equals(Locale.CHINESE) && !language.equals(Locale.TAIWAN) && !language.equals(Locale.TRADITIONAL_CHINESE) && !language.equals(Locale.SIMPLIFIED_CHINESE) && !language.equals(Locale.PRC)) {
            a = "Back";
            b = "Ok";
            c = "Cancel";
            d = "Prompt";
            e = "Exit Application?";
            f = "Exit Application";
            g = "Error";
            h = "Application Broken!";
            i = "Config File Was Missing!";
        } else {
            a = "返回";
            b = "确定";
            c = "取消";
            d = "提示";
            e = "确定要退出程序吗？";
            f = "退出提示";
            g = "错误提示";
            h = "缺少必须的资源!";
            i = "应用config文件损坏或不存在!";
        }

    }

    public int k;
    public int l;
    public int m;
    public float n;
    public int o;
    public int p;
    public int q;
    public int r;
    public boolean s;
    public PackageInfo t;
    public ApplicationInfo applicationInfo;
    public int w;
    public int x;
    public int y;

    private d(Context context) {
        this.b(context);
    }

    public static d a(Context context) {
        if (B == null) {
            B = new d(context);
        }

        return B;
    }

    public static d a() {
        return B;
    }

    public String b() {
        return this.t.packageName;
    }

    public boolean c() {
        return this.t.versionName.equals("0.0.0");
    }

    private void b(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dispm = resources.getDisplayMetrics();
        this.k = dispm.heightPixels;
        this.m = dispm.widthPixels;
        this.n = dispm.density;
        this.o = dispm.densityDpi;
        this.r = ViewConfiguration.get(context).getScaledTouchSlop();
        this.c(context);
        this.s = this.f();
        this.l = this.k - this.p;
        this.q = (int) (45.0F * this.n);
        this.d(context);
        this.e(context);
        this.e();
        this.f(context);
    }

    private final void c(Context context) {
        boolean var5 = false;

        try {
            Class<?> classl = Class.forName("com.android.internal.R$dimen");
            Object dimen = classl.newInstance();
            Field field = classl.getField("status_bar_height");
            int dimenH = Integer.parseInt(field.get(dimen).toString());
            this.p = context.getResources().getDimensionPixelSize(dimenH);
        } catch (Exception var7) {
        }

    }

    private void d(Context context) {
        String pkg = context.getPackageName();
        PackageManager pkm = context.getPackageManager();
        char flags = '\uffff';

        try {
            this.t = pkm.getPackageInfo(pkg, flags);
            this.applicationInfo = pkm.getApplicationInfo(pkg, flags);
        } catch (NameNotFoundException var7) {
            var7.printStackTrace();
        }

        if (com.uzmap.pkg.uzcore.external.l.a >= 13) {
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
            int type = uiModeManager.getCurrentModeType();
            if (type == 4) {
                v = true;
            }
        }

    }

    private void e(Context context) {
        String packageName = context.getPackageName();
        Resources resources = context.getResources();
        this.w = this.applicationInfo.icon;
        this.x = resources.getIdentifier("uz_splash_bg", "drawable", packageName);
        this.y = resources.getIdentifier("uz_pull_down_refresh_arrow", "drawable", packageName);
    }

    private void e() {
        try {
            if ("sdk".equals(PropertiesUtil.r())) {
                j = true;
                return;
            }

            Bundle metaData = this.applicationInfo.metaData;
            String v = metaData.getString("uz_version");
            if (TextUtils.isEmpty(v)) {
                return;
            }

            String[] vs = v.split("\\.");
            if (vs != null && vs.length >= 3) {
                String v1 = vs[0];
                String v2 = vs[1];
                int ver1 = Integer.valueOf(v1);
                int ver2 = Integer.valueOf(v2);
                if (ver1 == 1 && ver2 >= 2 || ver1 > 1) {
                    j = true;
                }
            }
        } catch (Exception var8) {
        }

    }

    public String a(String channelId) {
        if (TextUtils.isEmpty(channelId)) {
            channelId = "TD_CHANNEL_ID";
        }

        try {
            Bundle metaData = this.applicationInfo.metaData;
            String channal = metaData.getString(channelId);
            return channal;
        } catch (Exception var4) {
            return null;
        }
    }

    public boolean d() {
        ApplicationInfo var10000 = this.applicationInfo;
        return (var10000.flags &= 2) != 0;
    }

    private boolean f() {
        try {
            Method method = Class.forName("android.os.Build").getMethod("hasSmartBar");
            return (Boolean) method.invoke(null);
        } catch (Exception var2) {
            return false;
        }
    }

    private void f(Context context) {
        if (com.uzmap.pkg.uzcore.external.l.a >= 11) {
            String fingerprint = Build.FINGERPRINT != null ? Build.FINGERPRINT.toLowerCase() : "";
            Iterator var4 = C.iterator();

            d.a device;
            while (var4.hasNext()) {
                device = (d.a) var4.next();
                if (device.a(fingerprint)) {
                    z = true;
                    break;
                }
            }

            var4 = D.iterator();

            while (var4.hasNext()) {
                device = (d.a) var4.next();
                if (device.a(fingerprint)) {
                    A = true;
                    break;
                }
            }
        }

    }

    static class a {
        public String a;
        public String b;

        public a(String brand, String model) {
            this.a = brand;
            this.b = model;
        }

        public boolean a(String fingerprint) {
            fingerprint = fingerprint != null ? fingerprint : "";
            return fingerprint.contains(this.a) && fingerprint.contains(this.b);
        }
    }
}
