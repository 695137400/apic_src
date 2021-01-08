//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1.aa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class f extends e {
    public static final String i = System.getProperty("file.encoding");
    private static final byte[] j = com.uzmap.pkg.a.b.e.c("; filename=");
    private h k;

    public f(String name, h partSource, String contentType, String charset) {
        super(name, contentType == null ? "application/octet-stream" : contentType, charset == null ? i : charset, "binary");
        if (partSource == null) {
            throw new IllegalArgumentException("Source may not be null");
        } else {
            this.k = partSource;
        }
    }

    public f(String name, File file, String contentType, String charset) throws FileNotFoundException {
        this(name, (h)(new g(file)), contentType, charset);
    }

    protected void b(OutputStream out) throws IOException {
        super.b(out);
        String filename = this.k.b();
        if (filename != null) {
            out.write(j);
            out.write(c);
            out.write(com.uzmap.pkg.a.b.e.c(filename));
            out.write(c);
        }

    }

    protected void f(OutputStream out) throws IOException {
        if (this.g() != 0L) {
            long dlen = this.g();
            byte[] tmp;
            if (dlen > 1048576L) {
                tmp = new byte[8192];
            } else {
                tmp = new byte[4096];
            }

            InputStream instream = this.k.c();

            int len;
            try {
                while((len = instream.read(tmp)) >= 0) {
                    out.write(tmp, 0, len);
                }
            } finally {
                instream.close();
            }

        }
    }

    public String c() {
        return null;
    }

    protected long g() {
        return this.k.a();
    }
}
