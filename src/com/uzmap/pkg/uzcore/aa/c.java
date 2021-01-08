package com.uzmap.pkg.uzcore.aa;

import java.io.IOException;
import java.io.InputStream;

public class c extends InputStream {
    private byte[] a;
    private int b;
    private int c;
    private String d;

    public c(byte[] buf, String f) {
        if (buf == null) {
            throw new NullPointerException("str == null");
        } else {
            this.d = f;
            this.a = buf;
            this.b = buf.length;
        }
    }

    public synchronized int available() {
        int available = this.b - this.c;
        return available;
    }

    public synchronized int read() {
        return this.c < this.b ? this.a[this.c++] & 255 : -1;
    }

    public synchronized int read(byte[] buffer, int byteOffset, int byteCount) {
        if (buffer == null) {
            throw new NullPointerException("buffer == null");
        } else {
            a(buffer.length, byteOffset, byteCount);
            if (byteCount == 0) {
                return 0;
            } else if (this.c == this.b) {
                return -1;
            } else {
                int copylen = this.b - this.c < byteCount ? this.b - this.c : byteCount;

                for (int i = 0; i < copylen; ++i) {
                    buffer[byteOffset + i] = this.a[this.c + i];
                }

                this.c += copylen;
                return copylen;
            }
        }
    }

    public synchronized int read(byte[] buffer) throws IOException {
        return this.read(buffer, 0, buffer.length);
    }

    public synchronized void close() throws IOException {
        super.close();
    }

    public synchronized void reset() {
        this.c = 0;
    }

    public synchronized long skip(long charCount) {
        if (charCount <= 0L) {
            return 0L;
        } else {
            int numskipped;
            if ((long) (this.b - this.c) < charCount) {
                numskipped = this.b - this.c;
                this.c = this.b;
            } else {
                numskipped = (int) charCount;
                this.c = (int) ((long) this.c + charCount);
            }

            return numskipped;
        }
    }

    public static void a(int arrayLength, int offset, int count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + arrayLength + "; regionStart=" + offset + "; regionLength=" + count);
        }
    }

    public String toString() {
        return this.a != null ? new String(this.a, 0, this.a.length) : "SecurityInputStream@" + this.d;
    }
}
