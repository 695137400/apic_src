package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.webkit.*;
import com.uzmap.pkg.uzcore.aa.AssetsUtil;
import com.uzmap.pkg.uzkit.UZUtility;
import org.json.JSONObject;

import java.io.InputStream;

public class s extends WebViewClient {
    protected String a = "";
    protected boolean b;
    protected String c;
    protected boolean d;
    protected f e;

    s(Activity context, Object o) {
        this.e = f.b(context);
    }

    static s a(Activity context) {
        if (com.uzmap.pkg.uzcore.external.l.a < 11) {
            return new s(context, null);
        } else {
            return com.uzmap.pkg.uzcore.external.l.a < 21 ? new t(context, null) : new u(context, null);
        }
    }

    public void a(boolean g, String domain) {
        this.b = g;
        this.c = domain;
    }

    public void a(boolean smartUpdate) {
        this.d = smartUpdate;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (this.e.a(view, url)) {
            return true;
        } else {
            if (this.b) {
                url = AssetsUtil.getFinalDir(url);
            }

            if (com.uzmap.pkg.uzcore.external.l.a >= 11 && view.isPrivateBrowsingEnabled()) {
                return false;
            } else {
                return !URLUtil.isValidUrl(url) || this.e.b((a) view, url);
            }
        }
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        a target = (a) view;
        String aUrl = url;
        int index = url.indexOf("?");
        if (index > 0) {
            aUrl = url.substring(0, index);
        }

        target.c(aUrl);
        this.a = url;
        boolean networkUrl = url != null && url.startsWith("http");
        target.a(target, url, networkUrl);
        this.e.a(target, url, favicon);
    }

    public void onPageFinished(WebView view, String url) {
        a target = (a) view;
        String curUrl = view.getOriginalUrl();
        if (this.a.equals(url) && !target.H() && url.equals(curUrl)) {
            target.a(target, url);
            int type = target.m();
            if (type != 1) {
            }

            if (url.startsWith("http")) {
                com.uzmap.pkg.uzapp.e.a().b();
            }

            this.e.a(target, url);
        }
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (!failingUrl.startsWith("http") || errorCode != -1) {
            this.a(view, errorCode, description, failingUrl);
        }
    }

    public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
        continueMsg.sendToTarget();
    }

    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
    }

    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        String username = null;
        String password = null;
        boolean reuseHttpAuthUsernamePassword = handler.useHttpAuthUsernamePassword();
        if (reuseHttpAuthUsernamePassword && view != null) {
            String[] credentials = view.getHttpAuthUsernamePassword(host, realm);
            if (credentials != null && credentials.length == 2) {
                username = credentials[0];
                password = credentials[1];
            }
        }

        if (username != null && password != null) {
            handler.proceed(username, password);
        } else {
            handler.cancel();
        }

    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        UZCoreUtil.logd("onScaleChanged: oldScale = " + oldScale + " , newScale = " + newScale);
    }

    private void a(WebView view, int errorCode, String description, String failingUrl) {
        JSONObject json = new JSONObject();

        try {
            json.put("failedUrl", failingUrl);
            json.put("errorCode", errorCode);
            json.put("description", description);
        } catch (Exception var13) {
        }

        a errorView = (a) view;
        com.uzmap.pkg.uzcore.uzmodule.d p = new com.uzmap.pkg.uzcore.uzmodule.d(json.toString());
        errorView.a(p);
        UZCoreUtil.loge("onReceivedError: " + errorCode + "," + description + "," + failingUrl);
        String widgetPath = errorView.u().D;
        String errHtml = widgetPath + "error/error.html";
        InputStream input = null;

        try {
            input = UZUtility.guessInputStream(errHtml);
            if (input != null) {
                input.close();
                if (this.b) {
                    errHtml = AssetsUtil.b(errHtml);
                }

                errorView.loadUrl(errHtml);
                return;
            }
        } catch (Exception var12) {
        }

        errorView.b(com.uzmap.pkg.uzcore.external.d.a(failingUrl));
    }
}
