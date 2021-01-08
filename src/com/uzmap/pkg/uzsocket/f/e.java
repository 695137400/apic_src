package com.uzmap.pkg.uzsocket.f;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class e extends a {
    public String d = this.b("h");
    public String e = this.b("m");

    public e(JSONObject content) {
        super(content);
    }

    public String b() {
        JSONObject json = new JSONObject();

        try {
            JSONArray value = new JSONArray();
            value.put(this.e);
            json.put("value", value);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return json.toString();
    }

    public String c() {
        return !TextUtils.isEmpty(this.d) ? this.d : "You Have A New Message";
    }

    public String toString() {
        return "push @ message: " + this.e;
    }
}
