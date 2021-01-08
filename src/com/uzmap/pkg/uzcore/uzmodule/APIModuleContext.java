package com.uzmap.pkg.uzcore.uzmodule;

import com.uzmap.pkg.uzcore.UZWebView;

public class APIModuleContext extends UZModuleContext {
    public APIModuleContext() {
    }

    public APIModuleContext(UZWebView webView) {
        super(webView);
    }

    public APIModuleContext(String json, UZWebView webView) {
        super(json, webView);
    }
}
