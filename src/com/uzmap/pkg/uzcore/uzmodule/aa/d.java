package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONObject;

public final class d extends UZModuleContext {
    public int a;
    public String b;
    public JSONObject c;

    public d(String json, UZWebView webView) {
        super(json, webView);
        this.a();
    }

    protected void a() {
        if (!this.empty()) {
            String name = this.optString("name");
            this.a = com.uzmap.pkg.uzcore.e.a(name);
            this.b = this.optString("extra");
            if (!TextUtils.isEmpty(this.b)) {
                try {
                    this.c = new JSONObject(this.b);
                } catch (Exception var3) {
                }
            }

        }
    }

    public boolean b() {
        return -1 == this.a;
    }

    public Object c() {
        return this.c != null ? this.c : this.b;
    }

    public int a(String key) {
        return this.c != null ? this.c.optInt(key) : 0;
    }
}
