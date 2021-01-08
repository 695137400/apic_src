//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.d;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZConstant;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import org.json.JSONObject;

public class p extends s {
    public int d;
    public int e;
    public int f;
    public s g;
    public s h;

    public p() {
    }

    public p(String json, UZWebView webView) {
        super(json, webView, false);
        this.a(webView);
        this.a = -1;
        this.b = -1;
        this.c = 300L;
    }

    private void a(UZWebView webView) {
        if (!this.empty()) {
            this.e = UZCoreUtil.dipToPix(this.optInt("leftEdge", 60));
            this.f = UZCoreUtil.dipToPix(this.optInt("rightEdge", 60));
            JSONObject fixedPane = this.optJSONObject("fixedPane");
            JSONObject slidPane = this.optJSONObject("slidPane");
            this.d = UZConstant.mapInt(this.optString("type"), 0);
            String bg;
            if (fixedPane != null) {
                this.g = new s(webView);
                this.g.y = fixedPane.optString("name");
                this.g.z = fixedPane.optString("url");
                this.g.B = fixedPane.optBoolean("bounces", false);
                this.g.C = fixedPane.optBoolean("opaque", true);
                bg = fixedPane.optString("bg", (String)null);
                if (bg == null) {
                    bg = fixedPane.optString("bgColor", (String)null);
                }

                this.g.G = bg;
                this.g.I = fixedPane.optBoolean("vScrollBarEnabled", true);
                this.g.J = fixedPane.optBoolean("hScrollBarEnabled", true);
                this.g.H = new d(fixedPane.optString("pageParam"));
            }

            if (slidPane != null) {
                this.h = new s(webView);
                this.h.y = slidPane.optString("name");
                this.h.z = slidPane.optString("url");
                this.h.B = slidPane.optBoolean("bounces", false);
                this.h.C = slidPane.optBoolean("opaque", true);
                bg = fixedPane.optString("bg", (String)null);
                if (bg == null) {
                    bg = fixedPane.optString("bgColor", (String)null);
                }

                this.h.G = bg;
                this.h.I = slidPane.optBoolean("vScrollBarEnabled", true);
                this.h.J = slidPane.optBoolean("hScrollBarEnabled", true);
                this.h.H = new d(slidPane.optString("pageParam"));
            }

            JSONObject slidPaneStyle = this.optJSONObject("slidPaneStyle");
            if (slidPaneStyle != null) {
                this.e = UZCoreUtil.dipToPix(this.optInt("leftEdge", 60));
                this.f = UZCoreUtil.dipToPix(this.optInt("rightEdge", 60));
            }

        }
    }

    public void a(boolean reliable, String domain, UZWidgetInfo wgtInfo) {
        super.a(reliable, domain, wgtInfo);
        this.g.a(this.A);
        this.g.a(reliable, domain, wgtInfo);
        this.h.a(this.A);
        this.h.a(reliable, domain, wgtInfo);
    }
}
