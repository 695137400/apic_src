package com.uzmap.pkg.uzsocket.f;

import org.json.JSONObject;

public class b extends a {
    public Object d;
    public Object e;
    public Object f;
    public int g;
    public String h;
    public double i;
    public double j;
    public int k;

    public b() {
    }

    public b(JSONObject content) {
        super(content);
        this.b();
    }

    private void b() {
        this.g = this.a("t");
        this.h = this.b("v");
        this.i = this.c("x");
        this.j = this.c("y");
        this.k = this.a("d");
    }
}
