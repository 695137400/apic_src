package com.uzmap.pkg.uzsocket.f;

import org.json.JSONObject;

public class c extends a {
    public int d = -1;
    public JSONObject e;

    public c(JSONObject content) {
        super(content);
        this.d = this.b;
        this.e = content;
    }

    public boolean b() {
        return this.b == 101 || this.b == 102 || this.b == 103;
    }

    public String c() {
        return this.d().toString();
    }

    protected JSONObject d() {
        JSONObject value = new JSONObject();

        try {
            value.put("opt", this.d - 100);
            if (this.b != 107) {
                return value;
            }

            JSONObject gps = this.e.optJSONObject("gps");
            if (gps != null) {
                int o = gps.optInt("o");
                String m = gps.optString("m");
                Object l = gps.opt("l");
                gps.put("use", o);
                gps.put("l", l);
                gps.put("msg", m);
                value.put("gps", gps);
            }

            JSONObject tm = this.e.optJSONObject("tm");
            if (tm != null) {
                int e = tm.optInt("e");
                e = e == 0 ? 1 : 0;
                String m = tm.optString("m");
                tm = new JSONObject();
                tm.put("use", e);
                tm.put("msg", m);
                value.put("tm", tm);
            }

            JSONObject nt = this.e.optJSONObject("nt");
            String m;
            if (nt != null) {
                int o = nt.optInt("o");
                m = nt.optString("m");
                m = nt.optString("n");
                nt = new JSONObject();
                nt.put("use", o);
                nt.put("nts", m);
                nt.put("msg", m);
                value.put("nt", nt);
            }

            JSONObject etc = this.e.optJSONObject("etc");
            if (etc != null) {
                int o = etc.optInt("o");
                m = etc.optString("m");
                etc = new JSONObject();
                etc.put("use", o);
                etc.put("msg", m);
                value.put("etc", etc);
            }

            value.put("type", this.b);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return value;
    }

    public String toString() {
        return "msm @ opt: " + this.d + " , control: " + this.e;
    }
}
