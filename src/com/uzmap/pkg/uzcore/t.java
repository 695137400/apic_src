package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.uzmap.pkg.uzcore.aa.AssetStream;
import com.uzmap.pkg.uzcore.aa.AssetsFileUtil;
import com.uzmap.pkg.uzcore.aa.AssetsUtil;
import com.uzmap.pkg.uzcore.aa.UrlUtil;

public class t extends s {
    t(Activity context, Object o) {
        super(context, o);
    }

    public WebResourceResponse shouldInterceptRequest(WebView view, String u) {
        if (!this.b) {
            return super.shouldInterceptRequest(view, u);
        } else {
            String e = AssetsFileUtil.getFileExtension(u);
            boolean s = AssetsFileUtil.checkFileType(e);
            if (!s) {
                return this.b(u, e);
            } else {
                com.uzmap.pkg.uzcore.aa.i response = this.a(u, e);
                return response != null ? response : super.shouldInterceptRequest(view, u);
            }
        }
    }

    protected com.uzmap.pkg.uzcore.aa.i a(String u, String e) {
        String p = AssetsUtil.a();
        if (u.startsWith(p)) {
            String mt = AssetsUtil.a(e);
            u = AssetsUtil.getFinalDir(u);
            byte[] c = UrlUtil.getInstance().getUrlBit(u);
            AssetStream s1;
            if (c != null) {
                s1 = new AssetStream(c, u);
                return new com.uzmap.pkg.uzcore.aa.i(mt, s1);
            }

            c = UrlUtil.getInstance().putUrl(u);
            if (c != null) {
                s1 = new AssetStream(c, u);
                return new com.uzmap.pkg.uzcore.aa.i(mt, s1);
            }
        }

        return null;
    }

    protected com.uzmap.pkg.uzcore.aa.i b(String u, String e) {
        String p = AssetsUtil.a();
        if (u.startsWith(p)) {
            u = UZCoreUtil.makeRealPath(u, null);
            String mt = AssetsUtil.a(e);
            int l = 0;
            byte[] c = UrlUtil.getInstance().getUrlBit(u);
            if (c != null) {
                l = c.length;
            } else {
                c = UrlUtil.getInstance().putUrl(u);
                if (c != null) {
                    l = c.length;
                }
            }

            if (l <= 0) {
                return null;
            } else {
                AssetStream s1 = new AssetStream(c, u);
                return new com.uzmap.pkg.uzcore.aa.i(mt, s1);
            }
        } else {
            return null;
        }
    }
}
