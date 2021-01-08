package com.uzmap.pkg.uzsocket.f;

import org.json.JSONObject;

public class d extends a {
    public String d = this.b("h");
    public String e = this.b("m");

    public d(JSONObject content) {
        super(content);
    }

    public String toString() {
        return "notice @ title: " + this.d + " , message: " + this.e;
    }
}
