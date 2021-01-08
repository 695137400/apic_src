package com.uzmap.pkg.a.b.dd.aa;

import java.io.*;

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
            this.a1("text/plain; charset=" + charset);
        }
    }

    public boolean e() {
        return true;
    }

    public long f() {
        return this.d.length;
    }

    public InputStream a1() throws IOException {
        return new ByteArrayInputStream(this.d);
    }

    public void a1(OutputStream outstream) throws IOException {
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
