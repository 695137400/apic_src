package com.uzmap.pkg.uzkit.fineHttp;

public class g implements Cloneable {
    private final String a;
    private final Object b;

    public g(String name, Object value) {
        if (name == null) {
            throw new IllegalArgumentException("name may not be null");
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

    public long c() {
        return (this.a != null ? this.a.length() : 0) + (this.b != null ? this.b.toString().length() : 0);
    }

    public String toString() {
        return "{" + this.a + "=" + this.b + "}";
    }
}
