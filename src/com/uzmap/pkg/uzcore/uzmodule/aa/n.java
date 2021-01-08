package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;

public class n extends UZModuleContext {
    public boolean a;
    public String b;
    public int c;
    public int d;
    public String e;
    public String f;
    public String g;
    public String h;
    public boolean i;
    private String j;

    public n(String json, UZWebView webView) {
        super(json, webView);
        this.a();
    }

    protected void a() {
        this.a = this.optBoolean("visible", true);
        this.b = this.optString("loadingImg");
        String bcolor = this.optString("bgColor");
        if (TextUtils.isEmpty(bcolor)) {
            bcolor = "rgb(187, 236, 153, 255)";
        }

        this.d = UZCoreUtil.parseColor(bcolor);
        String tcolor = this.optString("textColor");
        if (TextUtils.isEmpty(tcolor)) {
            tcolor = "rgb(109, 128, 153)";
        }

        this.c = UZCoreUtil.parseColor(tcolor);
        this.e = this.optString("textDown");
        if (TextUtils.isEmpty(this.e)) {
            this.e = "下拉可以刷新...";
        }

        this.f = this.optString("textUp");
        if (TextUtils.isEmpty(this.f)) {
            this.f = "松开可以刷新...";
        }

        this.g = this.optString("textLoading");
        if (TextUtils.isEmpty(this.g)) {
            this.g = "刷新中";
        }

        this.h = this.optString("textTime", null);
        this.i = this.optBoolean("showTime", true);
    }

    public void a(String baseU) {
        this.j = baseU;
    }

    public void a(UZWidgetInfo info) {
        if (!TextUtils.isEmpty(this.b)) {
            if (!UZCoreUtil.isExtendScheme(this.b)) {
                this.b = UZUtility.makeAbsUrl(this.j, this.b);
            } else {
                this.b = UZUtility.makeRealPath(this.b, info);
            }
        }

    }
}
