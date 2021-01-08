package com.uzmap.pkg.a.b.dd;

public class a {
    protected final b a;
    private boolean b;
    private boolean c;
    private String d;
    private String e;

    a(b proxy) {
        this.a = proxy;
    }

    public final void a() {
        this.c();
    }

    final void b() {
        this.b = true;
        if (this.c) {
            this.c();
        }

    }

    protected final void c() {
        if (this.b) {
            synchronized (this.a) {
                this.a.notify();
            }
        } else {
            this.c = true;
        }

    }

    String d() {
        return this.d;
    }

    String e() {
        return this.e;
    }
}
