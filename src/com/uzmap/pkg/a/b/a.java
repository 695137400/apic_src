//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b;

import java.util.Collections;
import java.util.Map;

public interface a {
    com.uzmap.pkg.a.b.a.aa a(String var1);

    void a(String var1, com.uzmap.pkg.a.b.a.aa var2);

    void a();

    public static class aa {
        public byte[] a;
        public String b;
        public long c;
        public long d;
        public long e;
        public long f;
        public Map<String, String> g = Collections.emptyMap();

        public aa() {
        }

        public boolean a() {
            return this.e < System.currentTimeMillis();
        }

        public boolean b() {
            return this.f < System.currentTimeMillis();
        }
    }
}
