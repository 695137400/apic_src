//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.text.TextUtils;
import android.widget.RelativeLayout.LayoutParams;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.external.l;
import org.json.JSONObject;

public class f extends s {
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public JSONObject l;
    public boolean m;
    public String n;
    public String o;

    public f(String json) {
        super(json, (UZWebView)null, false);
        this.i();
    }

    public f(String json, UZWebView webView) {
        super(json, webView, false);
        this.i();
    }

    private void i() {
        if (!this.empty()) {
            this.n = this.optString("from");
            this.o = this.optString("to");
            this.B = this.optBoolean("bounces", true);
            this.m = this.optBoolean("hidden", false);
            this.l = this.optJSONObject("rect");
            String sx = null;
            String sy = null;
            String sw = null;
            String sh = null;
            String stm = null;
            String slm = null;
            String srm = null;
            String sbm = null;
            if (this.l != null) {
                sx = this.l.optString("x");
                sx = !TextUtils.isEmpty(sx) ? sx : "0";
                sy = this.l.optString("y");
                sy = !TextUtils.isEmpty(sy) ? sy : "0";
                sw = this.l.optString("w");
                sw = !TextUtils.isEmpty(sw) ? sw : "auto";
                sh = this.l.optString("h");
                sh = !TextUtils.isEmpty(sh) ? sh : "auto";
                stm = this.l.optString("marginTop");
                stm = !TextUtils.isEmpty(stm) ? stm : "0";
                slm = this.l.optString("marginLeft");
                slm = !TextUtils.isEmpty(slm) ? slm : "0";
                srm = this.l.optString("marginRight");
                srm = !TextUtils.isEmpty(srm) ? srm : "0";
                sbm = this.l.optString("marginBottom");
                sbm = !TextUtils.isEmpty(sbm) ? sbm : "0";
            }

            sw = !TextUtils.isEmpty(sw) ? sw : "auto";
            sh = !TextUtils.isEmpty(sh) ? sh : "auto";
            this.d = UZCoreUtil.parseCssPixel(sx);
            this.e = UZCoreUtil.parseCssPixel(sy);
            this.f = UZCoreUtil.parseCssPixel(sw);
            this.g = UZCoreUtil.parseCssPixel(sh);
            this.h = UZCoreUtil.parseCssPixel(stm);
            this.i = UZCoreUtil.parseCssPixel(slm);
            this.k = UZCoreUtil.parseCssPixel(srm);
            this.j = UZCoreUtil.parseCssPixel(sbm);
        }
    }

    public boolean b() {
        return this.l != null && !this.l.isNull("x");
    }

    public boolean c() {
        return this.l != null && !this.l.isNull("y");
    }

    public boolean d() {
        return this.l != null && !this.l.isNull("w");
    }

    public boolean e() {
        return this.l != null;
    }

    public boolean f() {
        return this.l != null && !this.l.isNull("h");
    }

    public boolean g() {
        return !this.isNull("animation");
    }

    public LayoutParams a(int winRight, int winBottom) {
        LayoutParams rlp = com.uzmap.pkg.uzcore.external.l.b(this.f, this.g);
        int frameNewRight = this.d + this.f;
        if (frameNewRight > winRight) {
            rlp.rightMargin = winRight - frameNewRight;
        } else {
            rlp.rightMargin = 0;
            rlp.rightMargin += this.k;
        }

        int frameNewBottom = this.e + this.g;
        if (frameNewBottom > winBottom) {
            rlp.bottomMargin = winBottom - frameNewBottom;
        } else if (frameNewBottom == 0) {
            ++this.e;
            rlp.bottomMargin = 0;
        } else {
            rlp.bottomMargin = 0;
            rlp.bottomMargin += this.j;
        }

        rlp.leftMargin = this.d;
        rlp.topMargin = this.e;
        rlp.leftMargin += this.i;
        rlp.topMargin += this.h;
        return rlp;
    }

    public LayoutParams a(LayoutParams oldlp, int winRight, int winBottom) {
        int newx = this.i == 0 && !this.b() ? oldlp.leftMargin : this.i + this.d;
        int newy = this.h == 0 && !this.c() ? oldlp.topMargin : this.h + this.e;
        int neww = this.d() ? this.f : oldlp.width;
        int newh = this.f() ? this.g : oldlp.height;
        LayoutParams rlp = com.uzmap.pkg.uzcore.external.l.b(neww, newh);
        int frameNewRight = newx + neww;
        if (frameNewRight > winRight) {
            rlp.rightMargin = winRight - frameNewRight;
        } else {
            rlp.rightMargin = 0;
            rlp.rightMargin += this.k;
        }

        int frameNewBottom = newy + newh;
        if (frameNewBottom > winBottom) {
            rlp.bottomMargin = winBottom - frameNewBottom;
        } else if (frameNewBottom == 0) {
            ++newy;
            rlp.bottomMargin = 0;
        } else {
            rlp.bottomMargin = 0;
            rlp.bottomMargin += this.j;
        }

        rlp.leftMargin = newx;
        rlp.topMargin = newy;
        return rlp;
    }
}
