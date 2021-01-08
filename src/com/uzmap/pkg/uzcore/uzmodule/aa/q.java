package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class q extends UZModuleContext {
    public int a;
    public int b;
    public int c;
    public int d;
    public String e;

    public q(String json, UZWebView webView) {
        super(json, webView);
        this.a();
    }

    private void a() {
        if (!this.empty()) {
            this.e = this.optString("id");
            String sx = this.optString("x");
            String sy = this.optString("y");
            String sw = this.optString("w");
            String sh = this.optString("h");
            this.a = UZCoreUtil.parseCssPixel(sx);
            this.b = UZCoreUtil.parseCssPixel(sy);
            this.c = UZCoreUtil.parseCssPixel(sw);
            this.d = UZCoreUtil.parseCssPixel(sh);
        }
    }
}
