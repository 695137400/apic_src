//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.v;
import com.uzmap.pkg.uzcore.w;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZConstant;
import org.json.JSONObject;

public class b extends UZModuleContext {
    public int a;
    public int b;
    public long c;

    public b() {
    }

    public b(UZWebView webView) {
        super(webView);
    }

    public b(String json, UZWebView webView, boolean close) {
        super(json, webView);
        this.a(close);
    }

    private void a(boolean close) {
        if (this.empty()) {
            this.a = -1;
            this.b = -1;
            this.c = 300L;
        } else {
            JSONObject anim = this.optJSONObject("animation");
            if (anim != null) {
                this.a = UZConstant.mapInt(anim.optString("type"), 0);
                this.b = UZConstant.mapInt(anim.optString("subType"), 0);
                String animd = anim.optString("duration");
                double duration = UZCoreUtil.parseCssTime(animd);
                if (duration <= 10.0D) {
                    duration *= 1000.0D;
                }

                this.c = (long)duration;
                if (0L == this.c) {
                    this.c = 300L;
                }
            } else {
                if (close) {
                    this.a = -1;
                    this.b = -1;
                    this.c = 300L;
                    return;
                }

                this.a = 0;
                this.b = 0;
                this.c = 300L;
            }

        }
    }

    public v a() {
        return w.a(this.a, this.b, this.c);
    }
}
