package com.uzmap.pkg.uzapp;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import com.uzmap.pkg.uzcore.external.l;

public class e {
    private static e c;
    private final CookieManager a;
    private CookieSyncManager b;

    private e(Context context) {
        try {
            this.b = CookieSyncManager.createInstance(context);
        } catch (Exception var3) {
        }

        this.a = CookieManager.getInstance();
        this.a.setAcceptCookie(true);
        if (l.a >= 12) {
            CookieManager.setAcceptFileSchemeCookies(true);
        }

        if (l.a < 21) {
            this.a.removeSessionCookie();
            this.a.removeExpiredCookie();
        }

    }

    public static final synchronized e a() {
        if (c == null) {
            c = new e(com.uzmap.pkg.uzcore.b.a().b());
        }

        return c;
    }

    public final void b() {
        if (l.a < 21) {
            if (this.b == null) {
                return;
            }

            this.b.sync();
        } else {
            this.a.flush();
        }

    }

    public final void c() {
        if (l.a < 21) {
            if (this.b == null) {
                return;
            }

            this.b.stopSync();
        }

    }

    public final void a(WebView webview, boolean accept) {
        if (l.a >= 21) {
            this.a.setAcceptThirdPartyCookies(webview, accept);
        }

    }

    public final void a(String url, String cookie) {
        if (cookie != null) {
            this.a.setCookie(url, cookie);
            this.b();
        }
    }

    public final String a(String url) {
        return this.a.getCookie(url);
    }
}
