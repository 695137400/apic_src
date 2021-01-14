package com.uzmap.pkg;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.uzmap.pkg.openapi.WebViewProvider;
import com.uzmap.pkg.uzcore.UZAppActivity;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public final class EntranceActivity extends UZAppActivity {
    static String a = "Decompile Is A Stupid Behavior";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected final boolean isFromNativeSDK() {
        return false;
    }

    protected final void onProgressChanged(WebViewProvider provider, int newProgress) {
    }

    protected final void onPageStarted(WebViewProvider provider, String url, Bitmap favicon) {
    }

    protected final void onPageFinished(WebViewProvider provider, String url) {
    }

    protected final boolean shouldOverrideUrlLoading(WebViewProvider provider, String url) {
        return false;
    }

    protected final void onReceivedTitle(WebViewProvider provider, String title) {
    }

    protected boolean shouldForbiddenAccess(String host, String module, String api) {
        return false;
    }

    protected boolean onHtml5AccessRequest(WebViewProvider provider, UZModuleContext moduleContext) {
        return false;
    }
}
