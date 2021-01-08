//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

public class f {
    private final String a;
    private final Object b;

    public f(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        } else {
            this.a = name;
            this.b = value;
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b != null ? this.b.toString() : "";
    }

    static int a(int seed, Object obj) {
        int hashcode = obj != null ? obj.hashCode() : 0;
        return seed * 37 + hashcode;
    }

    static boolean a(Object obj1, Object obj2) {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }
}
