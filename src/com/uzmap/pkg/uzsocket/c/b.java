package com.uzmap.pkg.uzsocket.c;

public class b extends Exception {
    private static final long serialVersionUID = 3731842424390998726L;
    private final int a;

    public b(int closecode) {
        this.a = closecode;
    }

    public b(int closecode, String s) {
        super(s);
        this.a = closecode;
    }

    public b(int closecode, Throwable t) {
        super(t);
        this.a = closecode;
    }

    public int a() {
        return this.a;
    }
}
