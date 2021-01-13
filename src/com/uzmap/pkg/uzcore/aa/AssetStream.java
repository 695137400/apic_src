package com.uzmap.pkg.uzcore.aa;

import java.io.IOException;
import java.io.InputStream;

public class AssetStream extends InputStream {
    private byte[] buf;
    private int length;
    private int bufLength;
    private String file;

    public AssetStream(byte[] buf, String file) {
        if (buf == null) {
            throw new NullPointerException("str == null");
        } else {
            this.file = file;
            this.buf = buf;
            this.length = buf.length;
        }
    }

    public synchronized int available() {
        int available = this.length - this.bufLength;
        return available;
    }

    public synchronized int read() {
        return this.bufLength < this.length ? this.buf[this.bufLength++] & 255 : -1;
    }

    public synchronized int read(byte[] buffer, int byteOffset, int byteCount) {
        if (buffer == null) {
            throw new NullPointerException("buffer == null");
        } else {
            checkBuf(buffer.length, byteOffset, byteCount);
            if (byteCount == 0) {
                return 0;
            } else if (this.bufLength == this.length) {
                return -1;
            } else {
                int copylen = this.length - this.bufLength < byteCount ? this.length - this.bufLength : byteCount;

                for (int i = 0; i < copylen; ++i) {
                    buffer[byteOffset + i] = this.buf[this.bufLength + i];
                }

                this.bufLength += copylen;
                return copylen;
            }
        }
    }

    public synchronized int read(byte[] buffer) throws IOException {
        return this.read(buffer, 0, buffer.length);
    }

    public synchronized void reset() {
        this.bufLength = 0;
    }

    public synchronized long skip(long charCount) {
        if (charCount <= 0L) {
            return 0L;
        } else {
            int numskipped;
            if ((long) (this.length - this.bufLength) < charCount) {
                numskipped = this.length - this.bufLength;
                this.bufLength = this.length;
            } else {
                numskipped = (int) charCount;
                this.bufLength = (int) ((long) this.bufLength + charCount);
            }

            return numskipped;
        }
    }

    public static void checkBuf(int arrayLength, int offset, int count) {
        if ((offset | count) < 0 || offset > arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException("length=" + arrayLength + "; regionStart=" + offset + "; regionLength=" + count);
        }
    }

    public String toString() {
        return this.buf != null ? new String(this.buf, 0, this.buf.length) : "SecurityInputStream@" + this.file;
    }
}
