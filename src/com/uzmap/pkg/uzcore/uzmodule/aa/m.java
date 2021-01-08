//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZConstant;

public class m extends UZModuleContext {
    public int a;
    public int b;
    public String c;
    public String d;
    public boolean e;

    public m(String json, UZWebView webView) {
        super(json, webView);
        this.a();
    }

    protected void a() {
        if (this.empty()) {
            this.c = "加载中";
            this.d = "请稍候...";
            this.e = true;
            this.b = 2;
        } else {
            this.a = this.optInt("style");
            if (!this.isNull("title")) {
                this.c = this.optString("title");
            } else {
                this.c = "加载中";
            }

            if (!this.isNull("text")) {
                this.d = this.optString("text");
            } else {
                this.d = "请稍候...";
            }

            this.e = this.optBoolean("modal", true);
            this.b = UZConstant.mapInt(this.optString("animationType"), 2);
        }
    }
}
