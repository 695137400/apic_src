package com.uzmap.pkg.uzkit.a;

import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;

public class d {
    public int a;
    public String b;
    public boolean c;
    public String d;
    public ApiConfig e;

    public d(ApiConfig info) {
        this.e = info;
    }

    public String toString() {
        return "version: " + this.a + "\n" + "silent: " + this.c + "\n" + "url: " + this.b;
    }
}
