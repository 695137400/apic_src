package com.uzmap.pkg.ui.b;

import java.util.Collections;
import java.util.Map;

public interface a {
    com.uzmap.pkg.ui.b.a.a1 a(String var1);

    void a(String var1, com.uzmap.pkg.ui.b.a.a1 var2);

    void a();

    class a1 {
        public byte[] a;
        public String b;
        public long c;
        public long d;
        public long e;
        public long f;
        public Map<String, String> g = Collections.emptyMap();

        public boolean a() {
            return this.e < System.currentTimeMillis();
        }

        public boolean b() {
            return this.f < System.currentTimeMillis();
        }
    }
}
