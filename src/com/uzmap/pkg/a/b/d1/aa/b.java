//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1.aa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class b extends a {
    private final InputStream d;
    private final long e;
    private boolean f = false;

    public b(InputStream instream, long length) {
        if (instream == null) {
            throw new IllegalArgumentException("Source input stream may not be null");
        } else {
            this.d = instream;
            this.e = length;
        }
    }

    public boolean e() {
        return false;
    }

    public long f() {
        return this.e;
    }

    public InputStream a() throws IOException {
        return this.d;
    }

    public void a(OutputStream outstream) throws IOException {
        if (outstream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        } else {
            InputStream instream = this.d;
            byte[] buffer = new byte[2048];
            int l;
            if (this.e < 0L) {
                while((l = instream.read(buffer)) != -1) {
                    outstream.write(buffer, 0, l);
                }
            } else {
                for(long remaining = this.e; remaining > 0L; remaining -= (long)l) {
                    l = instream.read(buffer, 0, (int)Math.min(2048L, remaining));
                    if (l == -1) {
                        break;
                    }

                    outstream.write(buffer, 0, l);
                }
            }

            this.f = true;
        }
    }

    public boolean h() {
        return !this.f;
    }

    public void b() throws IOException {
        this.f = true;
        this.d.close();
    }
}
