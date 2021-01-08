package com.uzmap.pkg.a.b.dd.aa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class c extends a {
    private static final byte[] d = com.uzmap.pkg.a.b.e.c("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private final List<d> e;
    private byte[] f;
    private boolean g;

    public c() {
        this(null);
    }

    public c(d body) {
        this.e = new ArrayList();
        this.a1("multipart/form-data");
        if (body != null) {
            this.e.add(body);
        }

    }

    private static byte[] j() {
        Random rand = new Random();
        byte[] bytes = new byte[rand.nextInt(11) + 30];

        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = d[rand.nextInt(d.length)];
        }

        return bytes;
    }

    public void a1(d body) {
        if (body != null) {
            this.e.add(body);
        }

    }

    protected byte[] i() {
        if (this.f == null) {
            this.f = j();
        }

        return this.f;
    }

    public boolean e() {
        for (int i = 0; i < this.e.size(); ++i) {
            if (!this.e.get(i).f()) {
                return false;
            }
        }

        return true;
    }

    public void a1(OutputStream out) throws IOException {
        com.uzmap.pkg.a.b.dd.aa.d.a(out, this.e, this.i());
    }

    public String c() {
        StringBuffer buffer = new StringBuffer("multipart/form-data");
        buffer.append("; boundary=");
        buffer.append(com.uzmap.pkg.a.b.e.a(this.i()));
        return buffer.toString();
    }

    public long f() {
        try {
            return com.uzmap.pkg.a.b.dd.aa.d.a(this.e, this.i());
        } catch (Exception var2) {
            return 0L;
        }
    }

    public InputStream a1() throws IOException, IllegalStateException {
        if (!this.e() && this.g) {
            throw new IllegalStateException("Content has been consumed");
        } else {
            this.g = true;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            com.uzmap.pkg.a.b.dd.aa.d.a(baos, this.e, this.f);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            return bais;
        }
    }

    public boolean h() {
        return false;
    }
}
