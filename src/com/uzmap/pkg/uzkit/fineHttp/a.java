package com.uzmap.pkg.uzkit.fineHttp;

import org.apache.http.util.EncodingUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public abstract class a {
    protected static final byte[] a = EncodingUtils.getAsciiBytes("------314159265358979323846");
    protected static final byte[] b;
    protected static final byte[] c;
    protected static final byte[] d;
    protected static final byte[] e;
    protected static final byte[] f;
    protected static final byte[] g;
    protected static final byte[] h;
    private static final byte[] i;

    static {
        i = a;
        b = EncodingUtils.getAsciiBytes("\r\n");
        c = EncodingUtils.getAsciiBytes("\"");
        d = EncodingUtils.getAsciiBytes("--");
        e = EncodingUtils.getAsciiBytes("Content-Disposition: form-data; name=");
        f = EncodingUtils.getAsciiBytes("Content-Type: ");
        g = EncodingUtils.getAsciiBytes("; charset=");
        h = EncodingUtils.getAsciiBytes("Content-Transfer-Encoding: ");
    }

    private byte[] j;

    public static void a(OutputStream out, List<a> bodys, byte[] partBoundary, h callback) throws IOException {
        if (bodys == null) {
            throw new IllegalArgumentException("Parts may not be null");
        } else if (partBoundary != null && partBoundary.length != 0) {
            Iterator var5 = bodys.iterator();

            while (var5.hasNext()) {
                a body = (a) var5.next();
                body.a(partBoundary);
                body.b(out, callback);
            }

            out.write(d);
            out.write(partBoundary);
            out.write(d);
            out.write(b);
        } else {
            throw new IllegalArgumentException("partBoundary may not be empty");
        }
    }

    public static long a(List<a> bodys, byte[] partBoundary) throws IOException {
        if (bodys == null) {
            throw new IllegalArgumentException("Parts may not be null");
        } else {
            long total = 0L;

            long l;
            for (Iterator var5 = bodys.iterator(); var5.hasNext(); total += l) {
                a body = (a) var5.next();
                body.a(partBoundary);
                l = body.h();
                if (l < 0L) {
                    return -1L;
                }
            }

            total += d.length;
            total += partBoundary.length;
            total += d.length;
            total += b.length;
            return total;
        }
    }

    public abstract String a();

    public abstract String b();

    public abstract String c();

    public abstract String d();

    protected byte[] e() {
        return this.j == null ? i : this.j;
    }

    void a(byte[] boundaryBytes) {
        this.j = boundaryBytes;
    }

    public boolean f() {
        return true;
    }

    protected void a(OutputStream out) throws IOException {
        out.write(d);
        out.write(this.e());
        out.write(b);
    }

    protected void b(OutputStream out) throws IOException {
        out.write(e);
        out.write(c);
        out.write(EncodingUtils.getAsciiBytes(this.a()));
        out.write(c);
    }

    protected void c(OutputStream out) throws IOException {
        String contentType = this.b();
        if (contentType != null) {
            out.write(b);
            out.write(f);
            out.write(EncodingUtils.getAsciiBytes(contentType));
            String charSet = this.c();
            if (charSet != null) {
                out.write(g);
                out.write(EncodingUtils.getAsciiBytes(charSet));
            }
        }

    }

    protected void d(OutputStream out) throws IOException {
        String transferEncoding = this.d();
        if (transferEncoding != null) {
            out.write(b);
            out.write(h);
            out.write(EncodingUtils.getAsciiBytes(transferEncoding));
        }

    }

    protected void e(OutputStream out) throws IOException {
        out.write(b);
        out.write(b);
    }

    protected abstract void a(OutputStream var1, h var2) throws IOException;

    protected abstract long g() throws IOException;

    protected void f(OutputStream out) throws IOException {
        out.write(b);
    }

    public void b(OutputStream out, h callback) throws IOException {
        this.a(out);
        this.b(out);
        this.c(out);
        this.d(out);
        this.e(out);
        this.a(out, callback);
        this.f(out);
    }

    public long h() throws IOException {
        if (this.g() < 0L) {
            return -1L;
        } else {
            ByteArrayOutputStream overhead = new ByteArrayOutputStream();
            this.a(overhead);
            this.b(overhead);
            this.c(overhead);
            this.d(overhead);
            this.e(overhead);
            this.f(overhead);
            return (long) overhead.size() + this.g();
        }
    }

    public String toString() {
        return this.a();
    }
}
