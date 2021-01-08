//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1.aa;

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

    public void a(String contentType) {
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

    public void a(int contentLength) {
        this.c = contentLength;
    }

    public long f() {
        return (long)this.c;
    }

    public InputStream a() throws IOException, IllegalStateException {
        return null;
    }

    public void a(OutputStream outstream) throws IOException {
    }

    public byte[] g() {
        return null;
    }

    public boolean h() {
        return false;
    }
}
