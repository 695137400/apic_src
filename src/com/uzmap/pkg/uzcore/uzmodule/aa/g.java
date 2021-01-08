//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.aa;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class g extends f {
    public String p;
    public boolean q;
    public int r;
    public int s;
    public boolean t;
    public boolean u;
    public String v;
    public List<f> w;

    public g(String json, UZWebView webView) {
        super(json, webView);
        this.i();
    }

    private void i() {
        if (!this.empty()) {
            this.p = this.optString("name");
            this.q = this.optBoolean("scroll");
            this.t = this.optBoolean("reload");
            this.r = this.optInt("index");
            this.u = this.optBoolean("scrollEnabled", true);
            this.v = this.optString("background");
            this.s = this.optInt("preload", 1);
            JSONArray fras = this.optJSONArray("frames");
            int len = fras != null ? fras.length() : 0;
            if (len > 0) {
                this.w = new ArrayList(len);

                for(int i = 0; i < len; ++i) {
                    JSONObject json = fras.optJSONObject(i);
                    if (json != null) {
                        try {
                            JSONObject args = new JSONObject();
                            args.put("arg", json);
                            f context = new f(args.toString());
                            this.w.add(context);
                        } catch (Exception var7) {
                            var7.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    public void a(boolean g, String domain, UZWidgetInfo wgtInfo) {
        this.z = UZUtility.makeAbsUrl(this.A, this.z);
        if (this.w != null) {
            Iterator var5 = this.w.iterator();

            while(var5.hasNext()) {
                f frame = (f)var5.next();
                frame.a(this.A);
                frame.a(g, domain, wgtInfo);
            }
        }

    }
}
