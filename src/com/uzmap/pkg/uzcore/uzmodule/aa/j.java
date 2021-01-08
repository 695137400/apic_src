package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZConstant;

public class j extends UZModuleContext {
    public int a;
    public int b;
    public int c;
    public int d;
    public boolean e;
    public int f;
    public boolean g;
    public int h;
    public int i;

    public j(String json, UZWebView webView) {
        super(json, webView);
        this.e();
    }

    private void e() {
        if (this.empty()) {
            this.f = -1;
        } else {
            this.a = UZConstant.mapInt(this.optString("sourceType"), 0);
            this.b = UZConstant.mapInt(this.optString("encodingType"), 0);
            this.c = UZConstant.mapInt(this.optString("mediaValue"), 0);
            this.d = UZConstant.mapInt(this.optString("destinationType"), 1);
            this.e = this.optBoolean("allowEdit");
            this.f = this.optInt("quality", -1);
            this.g = this.optBoolean("saveToPhotoAlbum");
            this.h = this.optInt("targetWidth", 0);
            this.i = this.optInt("targetHeight", 0);
            this.h = this.h != 0 ? this.h : this.i;
            this.i = this.i != 0 ? this.i : this.h;
        }
    }

    public boolean a() {
        return this.f > 0 || this.h * this.i > 0;
    }

    public boolean b() {
        return this.c == 0 && this.d == 0;
    }

    public boolean c() {
        return this.h * this.i > 0;
    }

    public boolean d() {
        return !this.g && this.a();
    }
}
