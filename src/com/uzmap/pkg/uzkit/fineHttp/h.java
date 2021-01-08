package com.uzmap.pkg.uzkit.fineHttp;

import org.apache.http.Header;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class h extends AbstractHttpEntity {
    private static final byte[] a = EncodingUtils.getAsciiBytes("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private final List<a> b = new ArrayList();
    private byte[] c;
    private HttpParams d;
    private boolean e;
    private j f;
    private long g;
    private long h;

    public h() {
        this.setContentType("multipart/form-data");
    }

    private static byte[] b() {
        Random rand = new Random();
        byte[] bytes = new byte[rand.nextInt(11) + 30];

        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = a[rand.nextInt(a.length)];
        }

        return bytes;
    }

    public void a(a body) {
        if (body != null) {
            this.b.add(body);
        }

    }

    protected byte[] a() {
        if (this.c == null) {
            String temp = null;
            if (this.d != null) {
                temp = (String) this.d.getParameter("http.method.multipart.boundary");
            }

            if (temp != null) {
                this.c = EncodingUtils.getAsciiBytes(temp);
            } else {
                this.c = b();
            }
        }

        return this.c;
    }

    public boolean isRepeatable() {
        for (int i = 0; i < this.b.size(); ++i) {
            if (!this.b.get(i).f()) {
                return false;
            }
        }

        return true;
    }

    public void writeTo(OutputStream out) throws IOException {
        this.g = this.getContentLength();
        com.uzmap.pkg.uzkit.fineHttp.a.a(out, this.b, this.a(), this);
        if (this.f != null) {
            this.f.a(100.0D);
        }

    }

    public Header getContentType() {
        StringBuffer buffer = new StringBuffer("multipart/form-data");
        buffer.append("; boundary=");
        buffer.append(EncodingUtils.getAsciiString(this.a()));
        return new BasicHeader("Content-Type", buffer.toString());
    }

    public long getContentLength() {
        try {
            return com.uzmap.pkg.uzkit.fineHttp.a.a(this.b, this.a());
        } catch (Exception var2) {
            return 0L;
        }
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        if (!this.isRepeatable() && this.e) {
            throw new IllegalStateException("Content has been consumed");
        } else {
            this.e = true;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            com.uzmap.pkg.uzkit.fineHttp.a.a(baos, this.b, this.c, this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            return bais;
        }
    }

    public boolean isStreaming() {
        return false;
    }

    public void a(j inList) {
        this.f = inList;
    }

    public void a(long size) {
        if (this.f != null) {
            this.h += size;
            double percent = 0.0D;
            double pro = (double) this.h / (double) this.g * 100.0D;
            BigDecimal b = new BigDecimal(pro);
            percent = b.setScale(2, 4).doubleValue();
            this.f.a(percent);
        }

    }
}
