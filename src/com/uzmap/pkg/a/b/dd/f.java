package com.uzmap.pkg.a.b.dd;

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

    static int a(int seed, Object obj) {
        int hashcode = obj != null ? obj.hashCode() : 0;
        return seed * 37 + hashcode;
    }

    static boolean a(Object obj1, Object obj2) {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b != null ? this.b.toString() : "";
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (this == object) {
            return true;
        } else if (object instanceof f) {
            f that = (f) object;
            return this.a.equals(that.a) && a(this.b, that.b);
        } else {
            return false;
        }
    }

    public int hashCode() {
        int hash = 17;
        hash = a(hash, this.a);
        hash = a(hash, this.b);
        return hash;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
