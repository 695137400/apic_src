//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

import com.uzmap.pkg.a.b.d;
import com.uzmap.pkg.a.b.o;
import com.uzmap.pkg.a.b.p;

public class g extends d {
    private int a;

    public g() {
    }

    public void a(o error) throws o {
        if (error != null && error.a != null) {
            int statusCode = error.a.a;
            if (statusCode == 301 || statusCode == 302) {
                p.a("onRedirectResponse", error);
                return;
            }
        }

        super.a(error);
    }

    public void a(int time) {
        this.a = time * 1000;
    }

    public int a() {
        return this.a > 0 ? this.a : 30000;
    }
}
