package com.uzmap.pkg.uzcore.external;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.uzmap.pkg.uzcore.ApplicationProcess;

public class m {
    private static m c;
    private final SharedPreferences a;
    private final Editor b;

    private m(Context context) {
        this.a = context.getSharedPreferences("UzSimpleStorage", l.b);
        this.b = this.a.edit();
    }

    public static m a() {
        if (c == null) {
            c = new m(ApplicationProcess.initialize().b());
        }

        return c;
    }

    public void a(String key, String value) {
        this.b.putString(key, value);
        this.b();
    }

    public String b(String key, String defValue) {
        return this.a.getString(key, defValue);
    }

    public void a(String key) {
        this.b.remove(key);
        this.b();
    }

    private void b() {
        this.b.commit();
    }
}
