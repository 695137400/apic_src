package com.uzmap.pkg.uzkit.data;

import android.text.TextUtils;

import java.util.Hashtable;

public class b {
    public String a;
    public Hashtable<String, c> b;

    public b(String name) {
        this.a = name;
        this.b = new Hashtable();
    }

    public void a(String name, String value) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value)) {
            c param = new c(name, value);
            this.b.put(name, param);
        }
    }

    public String a(String paramName) {
        c param = this.b.get(paramName);
        return param != null ? param.b : null;
    }
}
