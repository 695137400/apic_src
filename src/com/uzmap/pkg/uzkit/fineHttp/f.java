package com.uzmap.pkg.uzkit.fineHttp;

import org.apache.http.util.EncodingUtils;

import java.io.IOException;
import java.io.OutputStream;

public class f extends b {
    public static final String i = System.getProperty("file.encoding");
    private byte[] j;
    private final String k;

    public f(String name, String value, String charset) {
        super(name, "text/plain", charset == null ? i : charset, "8bit");
        if (value == null) {
            throw new IllegalArgumentException("Value may not be null");
        } else if (value.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        } else {
            this.k = value;
        }
    }

    public f(String name, String value) {
        this(name, value, null);
    }

    private byte[] i() {
        if (this.j == null) {
            this.j = EncodingUtils.getBytes(this.k, this.c());
        }

        return this.j;
    }

    protected void a(OutputStream out, h callback) throws IOException {
        out.write(this.i());
    }

    protected long g() {
        return this.i().length;
    }
}
