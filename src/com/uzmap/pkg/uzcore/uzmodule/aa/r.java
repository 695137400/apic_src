//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.d;
import org.json.JSONObject;

public class r extends b {
    public String d;
    public d e;
    public String f;
    public boolean g;

    public r(String json, UZWebView webView, boolean close) {
        super(json, webView, close);
        this.b();
    }

    protected void b() {
        if (!this.empty()) {
            this.d = this.optString("id", (String)null);
            String param = this.optString("wgtParam");
            this.e = new d(param);
            this.f = this.optString("retData", (String)null);
            this.g = this.optBoolean("silent");
        }
    }

    public JSONObject c() {
        JSONObject o = null;
        if (this.f == null) {
            o = new JSONObject();
        } else {
            try {
                o = new JSONObject(this.f);
            } catch (Exception var3) {
            }
        }

        return o != null ? o : new JSONObject();
    }
}
