//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule;

import android.text.TextUtils;
import org.json.JSONObject;

public class d {
    private String a;
    private JSONObject b;

    public d(String jstr) {
        this.a = jstr;
        this.c();
    }

    private void c() {
        if (!TextUtils.isEmpty(this.a)) {
            try {
                this.b = new JSONObject(this.a);
            } catch (Exception var2) {
            }

        }
    }

    public boolean a() {
        return TextUtils.isEmpty(this.a);
    }

    public String b() {
        return !TextUtils.isEmpty(this.a) ? this.a : "{}";
    }
}
