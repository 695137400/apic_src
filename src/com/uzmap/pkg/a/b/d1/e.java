//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

import com.uzmap.pkg.a.b.d.a.a;
import java.util.HashMap;
import java.util.Map;

public class e {
    private int a;
    private String b;
    private a c;
    private Map<String, String> d = new HashMap();

    public e(int responseCode, String responseMessage) {
        this.a(responseCode);
        this.a(responseMessage);
    }

    public void a(a entityFromConnection) {
        this.c = entityFromConnection;
    }

    public void a(String key, String value) {
        this.d.put(key, value);
    }

    public Map<String, String> a() {
        return this.d;
    }

    public a b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    public void a(int responseCode) {
        this.a = responseCode;
    }

    public void a(String responseMessage) {
        this.b = responseMessage;
    }
}
