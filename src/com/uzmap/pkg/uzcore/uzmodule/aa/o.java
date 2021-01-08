//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class o extends UZModuleContext {
    public String a;
    public String b;
    public String c;

    public o(String json, UZWebView webView) {
        super(json, webView);
        this.a();
    }

    protected void a() {
        if (!this.empty()) {
            this.a = this.optString("name");
            this.b = this.optString("frameName");
            this.c = this.optString("script");
        }
    }
}
