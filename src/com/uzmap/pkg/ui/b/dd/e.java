package com.uzmap.pkg.ui.b.dd;

import java.util.HashMap;
import java.util.Map;

public class e {
    private int a;
    private String b;
    private com.uzmap.pkg.ui.b.dd.aa.a c;
    private final Map<String, String> d = new HashMap();

    public e(int responseCode, String responseMessage) {
        this.a(responseCode);
        this.a(responseMessage);
    }

    public void a(com.uzmap.pkg.ui.b.dd.aa.a entityFromConnection) {
        this.c = entityFromConnection;
    }

    public void a(String key, String value) {
        this.d.put(key, value);
    }

    public Map<String, String> a() {
        return this.d;
    }

    public com.uzmap.pkg.ui.b.dd.aa.a b() {
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
