package com.uzmap.pkg.uzcore;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;

import java.lang.reflect.Method;

public class r {
    static final String a;
    static final String b;
    private static String e;
    private static String f;
    private static final String g;

    static {
        a = "Mozilla/5.0 (Linux; U; Android " + VERSION.RELEASE + "; en-us; " + Build.PRODUCT + " Build/FRF91) AppleWebKit/533.1 " + "(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        b = a;
        e = null;
        f = null;
        g = "APICloud/1.0 (Linux; U; Android " + VERSION.RELEASE + "; " + Build.PRODUCT + ")";
    }

    protected a c;
    protected WebSettings d;

    public r(a view) {
        this.c = view;
        this.d = view.getSettings();
    }

    public static void a(String userAgent) {
        e = userAgent;
    }

    public static void b(String agent) {
        f = agent;
    }

    public static final String a() {
        return e != null ? e : g;
    }

    public void a(boolean r) {
        this.d.setSaveFormData(false);
        this.d.setSavePassword(false);
        this.d.setLightTouchEnabled(false);
        this.d.setJavaScriptEnabled(true);
        this.d.setNeedInitialFocus(false);
        this.d.setSupportMultipleWindows(false);
        this.d.setAllowFileAccess(true);
        this.d.setJavaScriptCanOpenWindowsAutomatically(false);
        this.d.setUseWideViewPort(false);
        this.d.setLoadsImagesAutomatically(true);
        this.d.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        this.d.setRenderPriority(RenderPriority.HIGH);
        this.d.setDefaultTextEncodingName("UTF-8");
        Context ctx = this.c.getContext();
        this.d.setAppCacheEnabled(true);
        this.d.setAppCachePath(ctx.getDir("cache", 0).getPath());
        this.d.setGeolocationEnabled(true);
        String datebasePath = ctx.getDir("database", 0).getPath();
        this.d.setGeolocationDatabasePath(datebasePath);
        this.d.setDatabaseEnabled(true);
        this.d.setDatabasePath(datebasePath);
        this.d.setDomStorageEnabled(true);
        this.d.setLoadWithOverviewMode(false);
        int mode = this.d.getCacheMode();
        if (-1 != mode) {
            this.d.setCacheMode(-1);
        }

        String ua = this.b();
        if (f != null) {
            ua = ua + " " + f;
        } else if (e != null) {
            ua = e;
        }

        this.d.setUserAgentString(ua);
        if (com.uzmap.pkg.uzcore.external.l.a >= 8) {
            this.d.setPluginState(PluginState.ON);
        }

        if (com.uzmap.pkg.uzcore.external.l.a >= 11) {
            this.d.setAllowContentAccess(true);
            this.d.setEnableSmoothTransition(true);
        }

        if (com.uzmap.pkg.uzcore.external.l.a >= 16) {
            this.d.setAllowFileAccessFromFileURLs(true);
            this.d.setAllowUniversalAccessFromFileURLs(!r);
        }

        com.uzmap.pkg.uzapp.e.a().a(this.c, true);
        if (com.uzmap.pkg.uzcore.external.l.a >= 21) {
            this.d.setMixedContentMode(0);
        }

        if (com.uzmap.pkg.uzcore.external.l.a <= 11) {
            this.c();
        }

    }

    public void b(boolean flag) {
        this.d.setSupportZoom(true);
        this.d.setBuiltInZoomControls(true);
    }

    public void c(boolean flag) {
        if (flag != this.d.getUseWideViewPort()) {
            this.d.setUseWideViewPort(flag);
        }
    }

    private String b() {
        String ua = this.d.getUserAgentString();
        if (TextUtils.isEmpty(ua) && com.uzmap.pkg.uzcore.external.l.a >= 17) {
            String dua = WebSettings.getDefaultUserAgent(this.c.getContext());
            if (!TextUtils.isEmpty(dua)) {
                ua = dua;
            }
        }

        ua = !TextUtils.isEmpty(ua) ? ua : b;
        return ua;
    }

    private void c() {
        Class[] paramTypes = new Class[]{Boolean.TYPE};

        Method setPluginsEnabled;
        try {
            setPluginsEnabled = WebSettings.class.getDeclaredMethod("setNavDump", paramTypes);
            setPluginsEnabled.invoke(this.d, true);
        } catch (Exception var4) {
        }

        try {
            setPluginsEnabled = WebSettings.class.getDeclaredMethod("setPluginsEnabled", paramTypes);
            setPluginsEnabled.invoke(this.d, true);
        } catch (Exception var3) {
        }

    }
}
