package com.uzmap.pkg.uzkit.fineHttp;

import org.apache.http.util.EncodingUtils;

import java.io.*;

public class c extends b {
    public static final String i = System.getProperty("file.encoding");
    private static final byte[] j = EncodingUtils.getAsciiBytes("; filename=");
    private final e k;

    public c(String name, e partSource, String contentType, String charset) {
        super(name, contentType == null ? "application/octet-stream" : contentType, charset == null ? i : charset, "binary");
        if (partSource == null) {
            throw new IllegalArgumentException("Source may not be null");
        } else {
            this.k = partSource;
        }
    }

    public c(String name, File file, String contentType, String charset) throws FileNotFoundException {
        this(name, new d(file), contentType, charset);
    }

    protected void b(OutputStream out) throws IOException {
        super.b(out);
        String filename = this.k.b();
        if (filename != null) {
            out.write(j);
            out.write(c);
            out.write(EncodingUtils.getAsciiBytes(filename));
            out.write(c);
        }

    }

    protected void a(OutputStream out, h callback) throws IOException {
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
                while ((len = instream.read(tmp)) >= 0) {
                    out.write(tmp, 0, len);
                    callback.a(len);
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
