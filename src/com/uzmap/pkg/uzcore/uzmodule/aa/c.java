package com.uzmap.pkg.uzcore.uzmodule.aa;

import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONArray;

import java.util.Hashtable;

public class c extends UZModuleContext {
    static final Hashtable<String, Integer> a = new Hashtable();
    public String b;
    public String c;
    public String d;
    public String[] e;
    public int f;
    public int g = 1;

    static {
        a.put("text", 1);
        a.put("password", 129);
        a.put("email", 33);
        a.put("url", 17);
        a.put("number", 2);
    }

    public c(String json, UZWebView webView, int type) {
        super(json, webView);
        this.f = type;
        this.a();
    }

    static int a(String type) {
        if (TextUtils.isEmpty(type)) {
            return 1;
        } else {
            Integer o = a.get(type);
            return o != null ? o : 1;
        }
    }

    private void b() {
        String[] ay;
        if (this.f == 0) {
            ay = new String[]{"确定"};
            this.e = ay;
        } else if (this.f == 1) {
            ay = new String[]{"取消", "确定"};
            this.e = ay;
        } else if (this.f == 2) {
            ay = new String[]{"取消", "确定"};
            this.e = ay;
        }

    }

    protected void a() {
        if (this.empty()) {
            this.b = "提示消息";
            this.d = " ";
            this.b();
        } else {
            this.b = this.optString("title", "提示消息");
            this.d = this.optString("msg", " ");
            this.c = this.optString("text", "");
            this.g = a(this.optString("type", null));
            JSONArray btns = this.optJSONArray("buttons");
            if (btns != null) {
                int length = btns.length();
                if (length == 0) {
                    this.b();
                } else if (this.f == 0) {
                    this.e = new String[1];
                    this.e[0] = btns.optString(0);
                } else if (length < 2) {
                    this.b();
                } else {
                    this.e = new String[length];

                    for (int i = 0; i < length; ++i) {
                        this.e[i] = btns.optString(i);
                    }
                }
            } else {
                this.b();
            }

        }
    }
}
