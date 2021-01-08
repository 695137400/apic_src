package com.uzmap.pkg.uzsocket.f;

import org.json.JSONObject;

public abstract class a {
    public Object a;
    public int b;
    public long c;
    private JSONObject d;

    public a() {
    }

    public a(JSONObject content) {
        this.d = content;
        this.b = this.a("t");
    }

    public final int a(String key) {
        return this.d != null ? this.d.optInt(key) : 0;
    }

    public final String b(String key) {
        return this.d != null ? this.d.optString(key) : "";
    }

    public final double c(String key) {
        return this.d != null ? this.d.optDouble(key) : Double.NaN;
    }

    public String a() {
        JSONObject json = new JSONObject();

        try {
            json.put("id", this.a);
            json.put("j", 2);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return json.toString();
    }

    public String toString() {
        return this.d != null ? this.d.toString() : super.toString();
    }
}
