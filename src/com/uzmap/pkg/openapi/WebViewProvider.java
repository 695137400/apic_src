package com.uzmap.pkg.openapi;

import android.graphics.Bitmap;
import com.uzmap.pkg.uzcore.a;

import java.util.Map;

public final class WebViewProvider {
    private a a;
    private String b;

    public WebViewProvider(a impl) {
        this.a = impl;
        this.b = impl.p();
    }

    public String getName() {
        return this.a.B();
    }

    public String getWinName() {
        return this.b;
    }

    public void evaluateJavascript(String script) {
        this.a.e(script);
    }

    public void loadUrl(String url) {
        if (url.startsWith("javascript:")) {
            this.evaluateJavascript(url.substring("javascript:".length()));
        } else {
            this.a.loadUrl(url);
        }
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (url.startsWith("javascript:")) {
            this.evaluateJavascript(url.substring("javascript:".length()));
        } else {
            this.a.loadUrl(url, additionalHttpHeaders);
        }
    }

    public void loadData(String data, String mimeType, String encoding) {
        this.a.loadData(data, mimeType, encoding);
    }

    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String failUrl) {
        this.a.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, failUrl);
    }

    public void stopLoading() {
        this.a.stopLoading();
    }

    public void reload() {
        this.a.reload();
    }

    public boolean canGoBack() {
        return this.a.canGoBack();
    }

    public void goBack() {
        this.a.goBack();
    }

    public boolean canGoForward() {
        return this.a.canGoForward();
    }

    public void goForward() {
        this.a.goForward();
    }

    public boolean canGoBackOrForward(int steps) {
        return this.a.canGoBackOrForward(steps);
    }

    public void goBackOrForward(int steps) {
        this.a.goBackOrForward(steps);
    }

    public boolean pageUp(boolean top) {
        return this.a.pageUp(top);
    }

    public boolean pageDown(boolean bottom) {
        return this.a.pageDown(bottom);
    }

    public String getUrl() {
        return this.a.getUrl();
    }

    public String getTitle() {
        return this.a.getTitle();
    }

    public Bitmap getFavicon() {
        return this.a.getFavicon();
    }

    public int getProgress() {
        return this.a.getProgress();
    }

    public int getContentHeight() {
        return this.a.getContentHeight();
    }

    public void pauseTimers() {
        this.a.pauseTimers();
    }

    public void resumeTimers() {
        this.a.resumeTimers();
    }
}
