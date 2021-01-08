//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1.aa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class j extends a implements Cloneable {
    protected final byte[] d;

    public j(String s, String charset) throws UnsupportedEncodingException {
        if (s == null) {
            throw new IllegalArgumentException("Source string may not be null");
        } else {
            if (charset == null) {
                charset = "ISO-8859-1";
            }

            this.d = s.getBytes(charset);
            this.a("text/plain; charset=" + charset);
        }
    }

    public boolean e() {
        return true;
    }

    public long f() {
        return (long)this.d.length;
    }

    public InputStream a() throws IOException {
        return new ByteArrayInputStream(this.d);
    }

    public void a(OutputStream outstream) throws IOException {
        if (outstream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        } else {
            outstream.write(this.d);
        }
    }

    public byte[] g() {
        return this.d;
    }

    public boolean h() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
