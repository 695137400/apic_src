package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;

public class t extends s {
    t(Activity context, Object o) {
        super(context, o);
    }

    public WebResourceResponse shouldInterceptRequest(WebView view, String u) {
        if (!this.b) {
            return super.shouldInterceptRequest(view, u);
        } else {
            String e = com.uzmap.pkg.uzcore.aa.j.d(u);
            boolean s = com.uzmap.pkg.uzcore.aa.j.e(e);
            if (!s) {
                return this.b(u, e);
            } else {
                com.uzmap.pkg.uzcore.aa.i response = this.a(u, e);
                return response != null ? response : super.shouldInterceptRequest(view, u);
            }
        }
    }

    protected com.uzmap.pkg.uzcore.aa.i a(String u, String e) {
        String p = com.uzmap.pkg.uzcore.aa.b.a();
        if (u.startsWith(p)) {
            String mt = com.uzmap.pkg.uzcore.aa.b.a(e);
            u = com.uzmap.pkg.uzcore.aa.b.c(u);
            byte[] c = com.uzmap.pkg.uzcore.aa.e.a().a(u);
            com.uzmap.pkg.uzcore.aa.c s1;
            if (c != null) {
                s1 = new com.uzmap.pkg.uzcore.aa.c(c, u);
                return new com.uzmap.pkg.uzcore.aa.i(mt, s1);
            }

            c = com.uzmap.pkg.uzcore.aa.e.a().c(u);
            if (c != null) {
                s1 = new com.uzmap.pkg.uzcore.aa.c(c, u);
                return new com.uzmap.pkg.uzcore.aa.i(mt, s1);
            }
        }

        return null;
    }

    protected com.uzmap.pkg.uzcore.aa.i b(String u, String e) {
        String p = com.uzmap.pkg.uzcore.aa.b.a();
        if (u.startsWith(p)) {
            u = UZCoreUtil.makeRealPath(u, null);
            String mt = com.uzmap.pkg.uzcore.aa.b.a(e);
            int l = 0;
            byte[] c = com.uzmap.pkg.uzcore.aa.g.a().a(u);
            if (c != null) {
                l = c.length;
            } else {
                c = com.uzmap.pkg.uzcore.aa.g.a().c(u);
                if (c != null) {
                    l = c.length;
                }
            }

            if (l <= 0) {
                return null;
            } else {
                com.uzmap.pkg.uzcore.aa.c s1 = new com.uzmap.pkg.uzcore.aa.c(c, u);
                return new com.uzmap.pkg.uzcore.aa.i(mt, s1);
            }
        } else {
            return null;
        }
    }
}
