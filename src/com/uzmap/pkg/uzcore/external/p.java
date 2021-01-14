package com.uzmap.pkg.uzcore.external;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.uzmap.pkg.uzcore.ApplicationProcess;

public class p {
    private static p c;
    private final SharedPreferences a;
    private final Editor b;

    private p() {
        Context context = ApplicationProcess.initialize().b();
        this.a = context.getSharedPreferences("UzLocalStorage", l.b);
        this.b = this.a.edit();
    }

    public static p a() {
        if (c == null) {
            c = new p();
        }

        return c;
    }

    public void a(String key, String value) {
        this.b.putString(key, value);
        this.b.commit();
    }

    public String a(String key) {
        return this.a.getString(key, "");
    }

    public void b(String key) {
        this.b.remove(key);
        this.b.commit();
    }

    public void b() {
        this.b.clear();
        this.b.commit();
    }
}
