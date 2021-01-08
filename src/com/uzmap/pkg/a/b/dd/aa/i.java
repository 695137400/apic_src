package com.uzmap.pkg.a.b.dd.aa;

import java.io.IOException;
import java.io.OutputStream;

public class i extends e {
    public static final String i = System.getProperty("file.encoding");
    private byte[] j;
    private final String k;

    public i(String name, String value, String charset) {
        super(name, "text/plain", charset == null ? i : charset, "8bit");
        if (value == null) {
            throw new IllegalArgumentException("Value may not be null");
        } else if (value.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        } else {
            this.k = value;
        }
    }

    public i(String name, String value) {
        this(name, value, null);
    }

    private byte[] i() {
        if (this.j == null) {
            this.j = com.uzmap.pkg.a.b.e.a(this.k, this.c());
        }

        return this.j;
    }

    protected void f(OutputStream out) throws IOException {
        out.write(this.i());
    }

    protected long g() {
        return this.i().length;
    }
}
