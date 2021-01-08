//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONArray;
import org.json.JSONObject;

public final class l extends UZModuleContext {
    public JSONObject a;
    public long[] b;
    public String c;
    public boolean d;
    public JSONObject e;

    public l(String json, UZWebView webView) {
        super(json, webView);
        this.a();
    }

    protected void a() {
        if (!this.empty()) {
            this.a = this.optJSONObject("notify");
            this.e = this.optJSONObject("alarm");
            this.c = this.optString("sound", (String)null);
            this.d = this.optBoolean("light");
            JSONArray l = this.optJSONArray("vibrate");
            int s = l != null ? l.length() : 0;
            if (s > 0) {
                this.b = new long[s];

                for(int i = 0; i < s; ++i) {
                    this.b[i] = l.optLong(i);
                }
            } else if (!this.isNull("vibrate")) {
                this.b = new long[2];
                this.b[0] = 500L;
                this.b[1] = 500L;
            }

        }
    }

    public boolean b() {
        return this.a != null;
    }

    public boolean c() {
        return this.e != null;
    }

    public String d() {
        return this.a != null ? this.a.optString("title") : "";
    }

    public String e() {
        return this.a != null ? this.a.optString("content") : "";
    }

    public String f() {
        return this.a != null ? this.a.optString("extra") : "";
    }

    public boolean g() {
        return this.a != null ? this.a.optBoolean("updateCurrent") : false;
    }
}
