//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.openapi;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.uzmap.pkg.uzcore.UZAppActivity;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONObject;

public class ExternalActivity extends UZAppActivity {
    public ExternalActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected final boolean isFromNativeSDK() {
        return true;
    }

    public final void evaluateJavascript(String script) {
        this.evaluateJavascript((String)null, script);
    }

    public final void evaluateJavascript(String winName, String script) {
        this.evaluateJavascript(winName, (String)null, script);
    }

    public final void evaluateJavascript(String winName, String frameName, String script) {
        this.evaluateScript(winName, frameName, script);
    }

    public final void addHtml5EventListener(Html5EventListener listener) {
        if (listener != null) {
            this.addH5EventListener(listener);
        }

    }

    public final void removeHtml5EventListener(Html5EventListener listener) {
        if (listener != null) {
            this.removeH5EventListener(listener);
        }
    }

    public final void removeAllHtml5EventListener() {
        this.removeH5EventListener((Html5EventListener)null);
    }

    public final void sendEventToHtml5(String eventName, JSONObject extra) {
        this.sendEventToH5(eventName, extra);
    }

    protected void onProgressChanged(WebViewProvider provider, int newProgress) {
    }

    protected void onReceivedTitle(WebViewProvider provider, String title) {
    }

    protected void onPageStarted(WebViewProvider provider, String url, Bitmap favicon) {
    }

    protected void onPageFinished(WebViewProvider provider, String url) {
    }

    protected boolean shouldOverrideUrlLoading(WebViewProvider provider, String url) {
        return false;
    }

    protected boolean shouldForbiddenAccess(String host, String module, String api) {
        return false;
    }

    protected boolean onHtml5AccessRequest(WebViewProvider provider, UZModuleContext moduleContext) {
        return false;
    }
}
