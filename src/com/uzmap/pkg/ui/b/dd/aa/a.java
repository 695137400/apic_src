package com.uzmap.pkg.ui.b.dd.aa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class a {
    protected String a;
    protected String b;
    protected int c;

    protected a() {
    }

    public String c() {
        return this.a;
    }

    public void a1(String contentType) {
        this.a = contentType;
    }

    public void b(String contentEncoding) {
        this.b = contentEncoding;
    }

    public String d() {
        return this.b;
    }

    public void b() throws IOException, UnsupportedOperationException {
        if (this.h()) {
            throw new UnsupportedOperationException("streaming entity does not implement consumeContent()");
        }
    }

    public boolean e() {
        return false;
    }

    public void a1(int contentLength) {
        this.c = contentLength;
    }

    public long f() {
        return this.c;
    }

    public InputStream a1() throws IOException, IllegalStateException {
        return null;
    }

    public void a1(OutputStream outstream) throws IOException {
    }

    public byte[] g() {
        return null;
    }

    public boolean h() {
        return false;
    }
}
