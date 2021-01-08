package com.uzmap.pkg.uzcore.aa;

public class f {
    public byte[] a;

    public f(byte[] buffer) {
        this.a = buffer;
    }

    public int a() {
        return this.a.length;
    }

    public String toString() {
        return this.a != null ? new String(this.a) : null;
    }
}
