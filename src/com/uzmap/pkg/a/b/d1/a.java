//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

public class a {
    private boolean b;
    private boolean c;
    protected final b a;
    private String d;
    private String e;

    public final void a() {
        this.c();
    }

    a(b proxy) {
        this.a = proxy;
    }

    final void b() {
        this.b = true;
        if (this.c) {
            this.c();
        }

    }

    protected final void c() {
        if (this.b) {
            synchronized(this.a) {
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
