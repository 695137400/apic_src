//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.a.j;
import com.uzmap.pkg.uzcore.external.UzResourceCache;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzcore.uzmodule.d;
import com.uzmap.pkg.uzcore.uzmodule.e;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;

public class s extends b {
    static final int x;
    public String y;
    public String z;
    public String A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public String G;
    public d H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public int M;

    static {
        x = l.a >= 21 ? 100 : (l.a >= 14 ? 0 : 10);
    }

    public s() {
    }

    public s(UZWebView webView) {
        super(webView);
    }

    public s(String json, UZWebView webView, boolean close) {
        super(json, webView, close);
        this.b();
    }

    private void b() {
        if (!this.empty()) {
            this.y = this.optString("name");
            this.z = this.optString("url");
            String param = this.optString("pageParam");
            this.H = new d(param);
            this.B = this.optBoolean("bounces", false);
            this.C = this.optBoolean("opaque", true);
            this.D = this.optBoolean("alone", false);
            this.L = this.optBoolean("reload");
            this.E = this.optBoolean("scaleEnabled", false);
            this.M = this.optInt("delay", x);
            this.G = this.optString("bg", (String)null);
            if (this.G == null) {
                this.G = this.optString("bgColor", (String)null);
            }

            this.I = this.optBoolean("vScrollBarEnabled", true);
            this.J = this.optBoolean("hScrollBarEnabled", true);
            this.K = this.optBoolean("showProgress", false);
            this.F = this.optBoolean("allowEdit", false);
        }
    }

    public boolean h() {
        return this.G != null;
    }

    public void a(String baseU) {
        this.A = baseU;
    }

    public void a(boolean r, String d, UZWidgetInfo wgtInfo) {
        if (!TextUtils.isEmpty(this.z)) {
            if (!r) {
                if (!UZCoreUtil.isExtendScheme(this.z)) {
                    this.z = UZUtility.makeAbsUrl(this.A, this.z);
                } else {
                    this.z = UZUtility.makeRealPath(this.z, wgtInfo);
                }

                this.c();
            } else if (this.z.startsWith(com.uzmap.pkg.uzcore.aa.b.f())) {
                if (this.z.contains(d)) {
                    try {
                        this.z = j.c(this.z);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }

                }
            } else if (UZCoreUtil.isExtendScheme(this.z)) {
                this.z = UZUtility.makeRealPath(this.z, wgtInfo);
                this.z = com.uzmap.pkg.uzcore.aa.b.b(this.z);
                this.c();
            } else {
                if (this.A.startsWith(com.uzmap.pkg.uzcore.aa.b.a())) {
                    this.A = com.uzmap.pkg.uzcore.aa.b.c(this.A);
                }

                this.z = UZUtility.makeAbsUrl(this.A, this.z);
                this.z = com.uzmap.pkg.uzcore.aa.b.b(this.z);
            }
        }
    }

    public Drawable a(e info) {
        Drawable drawable = null;
        if (!TextUtils.isEmpty(this.G)) {
            char first = this.G.charAt(0);
            if ('#' != first && 'r' != first && 'R' != first) {
                String bgUrl = null;
                if (!UZCoreUtil.isExtendScheme(this.G)) {
                    bgUrl = UZUtility.makeAbsUrl(this.A, this.G);
                } else {
                    bgUrl = UZUtility.makeRealPath(this.G, info.i());
                }

                Bitmap bitmap = UzResourceCache.get().getImage(bgUrl);
                if (bitmap != null) {
                    drawable = new BitmapDrawable(com.uzmap.pkg.uzcore.b.a().b().getResources(), bitmap);
                } else {
                    drawable = new ColorDrawable(l.c);
                }
            } else {
                int color = UZCoreUtil.parseColor(this.G);
                drawable = new ColorDrawable(color);
            }
        } else {
            drawable = new ColorDrawable(l.c);
        }

        return (Drawable)drawable;
    }

    private void c() {
        try {
            if (Uri.parse(this.z).getScheme() == null) {
                this.z = "file://" + this.z;
            }
        } catch (Exception var2) {
        }

    }
}
