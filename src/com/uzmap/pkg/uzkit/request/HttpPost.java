//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit.request;

import android.os.SystemClock;
import com.uzmap.pkg.a.b.d.a.a;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpPost extends Request {
    private a c;
    private Params d;
    private long e;
    private long f;
    private long g;

    public HttpPost(String url, Params params) {
        this(1, url, params);
    }

    protected HttpPost(int method, String url, Params params) {
        super(method, url);
        if (params == null) {
            throw new RuntimeException("Params can not be empty");
        } else {
            this.d = params;
            if (params.getAdditionalHeaders() != null) {
                this.addHeader(params.getAdditionalHeaders());
            }

        }
    }

    public void setParams(Params params) {
        this.d = params;
        if (params != null && params.getAdditionalHeaders() != null) {
            this.addHeader(params.getAdditionalHeaders());
        }

    }

    public boolean isEmpty() throws com.uzmap.pkg.a.b.a.a {
        this.checkEntity();
        return this.c == null;
    }

    public long getContentLength() {
        this.checkEntity();
        return this.c != null ? this.c.f() : 0L;
    }

    public byte[] getBody() throws com.uzmap.pkg.a.b.a.a {
        this.checkEntity();
        return this.c != null ? this.c.g() : null;
    }

    public String getBodyContentType() {
        this.checkEntity();
        return this.c != null ? this.c.c() : super.getBodyContentType();
    }

    public void writeTo(OutputStream out) throws IOException, com.uzmap.pkg.a.b.a.a {
        this.checkEntity();
        if (this.c != null) {
            this.e = this.c.f();
            OutputStream out = new HttpPost.OutputStreamWrap(out);
            this.c.a(out);
        }

    }

    private void checkEntity() {
        if (this.c == null) {
            this.c = this.d.getHttpEntity();
        }

    }

    private void reportFinishLength(long readlen) {
        this.f += readlen;
        long now = SystemClock.elapsedRealtime();
        long step = now - this.g;
        if (step > 300L || this.f == this.e) {
            this.g = now;
            this.deliverProgress(this.e, this.f);
        }

    }

    private class OutputStreamWrap extends FilterOutputStream {
        public OutputStreamWrap(OutputStream out) {
            super(out);
        }

        public void write(byte[] buffer, int offset, int length) throws IOException {
            super.write(buffer, offset, length);
            HttpPost.this.reportFinishLength((long)length);
        }
    }
}
