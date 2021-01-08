//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.uzmap.pkg.openapi.WebViewProvider;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class InternalActivity extends UZAppActivity {
    public InternalActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected boolean isFromNativeSDK() {
        return false;
    }

    protected void onProgressChanged(WebViewProvider provider, int newProgress) {
    }

    protected void onPageStarted(WebViewProvider provider, String url, Bitmap favicon) {
    }

    protected void onPageFinished(WebViewProvider provider, String url) {
    }

    protected boolean shouldOverrideUrlLoading(WebViewProvider provider, String url) {
        return false;
    }

    protected void onReceivedTitle(WebViewProvider provider, String title) {
    }

    protected boolean shouldForbiddenAccess(String host, String module, String api) {
        return false;
    }

    protected boolean onHtml5AccessRequest(WebViewProvider provider, UZModuleContext moduleContext) {
        return false;
    }
}
