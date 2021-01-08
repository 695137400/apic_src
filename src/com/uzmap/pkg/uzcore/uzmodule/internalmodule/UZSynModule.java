package com.uzmap.pkg.uzcore.uzmodule.internalmodule;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.external.p;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzkit.UZUtility;

public class UZSynModule extends UZModule {
    public static String a = "os";

    public UZSynModule(UZWebView webView) {
        super(webView);
        this.setModuleName(a);
    }

    @JavascriptInterface
    public void setItem(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            p.a().a(key, value);
        }

    }

    @JavascriptInterface
    public String getItem(String key) {
        return p.a().a(key);
    }

    @JavascriptInterface
    public void removeItem(String key) {
        p.a().b(key);
    }

    @JavascriptInterface
    public void clear() {
        p.a().b();
    }

    @JavascriptInterface
    public Object localStorage() {
        return this;
    }

    @JavascriptInterface
    public String base64(String path) {
        return UZUtility.bitmapToBase64(path);
    }

    protected void onClean() {
    }

    public String toString() {
        return "undefine";
    }
}
