//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import android.os.SystemClock;
import com.uzmap.pkg.a.b.p;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class c implements com.uzmap.pkg.a.b.a {
    private final Map<String, c.as> a;
    private long b;
    private final File file;
    private final int d;

    public c(File rootDirectory) {
        this(rootDirectory, 5242880);
    }

    public c(File rootDirectory, int maxCacheSizeInBytes) {
        this.a = new LinkedHashMap(16, 0.75F, true);
        this.b = 0L;
        this.file = rootDirectory;
        this.d = maxCacheSizeInBytes;
    }

    public synchronized com.uzmap.pkg.a.b.a.aa a(String key) {
        c.as entry = (c.as)this.a.get(key);
        if (entry == null) {
            return null;
        } else {
            File file = this.c(key);
            c.bb cis = null;

            try {
                cis = new c.bb(new BufferedInputStream(new FileInputStream(file)));
                c.as.a((InputStream)cis);
                byte[] data = a((InputStream)cis, (int)(file.length() - (long)cis.a));
                com.uzmap.pkg.a.b.a.aa var7 = entry.a(data);
                return var7;
            } catch (IOException var17) {
                p.b("%s: %s", new Object[]{file.getAbsolutePath(), var17.toString()});
                this.b(key);
            } catch (NegativeArraySizeException var18) {
                p.b("%s: %s", new Object[]{file.getAbsolutePath(), var18.toString()});
                this.b(key);
                return null;
            } finally {
                if (cis != null) {
                    try {
                        cis.close();
                    } catch (IOException var16) {
                        return null;
                    }
                }

            }

            return null;
        }
    }


    public synchronized void a() {
        if (!this.file.exists()) {
            if (!this.file.mkdirs()) {
                p.c("Unable to create cache dir %s", new Object[]{this.file.getAbsolutePath()});
            }

        } else {
            File[] files = this.file.listFiles();
            if (files != null) {
                File[] var5 = files;
                int var4 = files.length;

                for(int var3 = 0; var3 < var4; ++var3) {
                    File file = var5[var3];
                    BufferedInputStream fis = null;

                    try {
                        fis = new BufferedInputStream(new FileInputStream(file));
                        c.as entry = c.as.a((InputStream)fis);
                        entry.a = file.length();
                        this.a(entry.b, entry);
                    } catch (IOException var16) {
                        if (file != null) {
                            file.delete();
                        }
                    } finally {
                        try {
                            if (fis != null) {
                                fis.close();
                            }
                        } catch (IOException var15) {
                        }

                    }
                }

            }
        }
    }

    public synchronized void a(String key, com.uzmap.pkg.a.b.a.aa entry) {
        this.a(entry.a.length);
        File file = this.c(key);

        try {
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
            c.as e = new c.as(key, entry);
            boolean success = e.a((OutputStream)fos);
            if (!success) {
                fos.close();
                p.b("Failed to write header for %s", new Object[]{file.getAbsolutePath()});
                throw new IOException();
            } else {
                fos.write(entry.a);
                fos.close();
                this.a(key, e);
            }
        } catch (IOException var7) {
            boolean deleted = file.delete();
            if (!deleted) {
                p.b("Could not clean up file %s", new Object[]{file.getAbsolutePath()});
            }

        }
    }

    public synchronized void b(String key) {
        boolean deleted = this.c(key).delete();
        this.e(key);
        if (!deleted) {
            p.b("Could not delete cache entry for key=%s, filename=%s", new Object[]{key, this.d(key)});
        }

    }

    private String d(String key) {
        int firstHalfLength = key.length() / 2;
        String localFilename = String.valueOf(key.substring(0, firstHalfLength).hashCode());
        localFilename = localFilename + String.valueOf(key.substring(firstHalfLength).hashCode());
        return localFilename;
    }

    public File c(String key) {
        return new File(this.file, this.d(key));
    }

    private void a(int neededSpace) {
        if (this.b + (long)neededSpace >= (long)this.d) {
            long before = this.b;
            int prunedFiles = 0;
            long startTime = SystemClock.elapsedRealtime();
            Iterator iterator = this.a.entrySet().iterator();

            while(iterator.hasNext()) {
                Entry<String, c.as> entry = (Entry)iterator.next();
                c.as e = (c.as)entry.getValue();
                boolean deleted = this.c(e.b).delete();
                if (deleted) {
                    this.b -= e.a;
                } else {
                    p.b("Could not delete cache entry for key=%s, filename=%s", new Object[]{e.b, this.d(e.b)});
                }

                iterator.remove();
                ++prunedFiles;
                if ((float)(this.b + (long)neededSpace) < (float)this.d * 0.9F) {
                    break;
                }
            }

            p.a("pruned %d files, %d bytes, %d ms", new Object[]{prunedFiles, this.b - before, SystemClock.elapsedRealtime() - startTime});
        }
    }

    private void a(String key, c.as entry) {
        if (!this.a.containsKey(key)) {
            this.b += entry.a;
        } else {
            c.as oldEntry = (c.as)this.a.get(key);
            this.b += entry.a - oldEntry.a;
        }

        this.a.put(key, entry);
    }

    private void e(String key) {
        c.as entry = (c.as)this.a.get(key);
        if (entry != null) {
            this.b -= entry.a;
            this.a.remove(key);
        }

    }

    private static byte[] a(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];

        int count;
        int pos;
        for(pos = 0; pos < length && (count = in.read(bytes, pos, length - pos)) != -1; pos += count) {
        }

        if (pos != length) {
            throw new IOException("Expected " + length + " bytes, read " + pos + " bytes");
        } else {
            return bytes;
        }
    }

    private static int e(InputStream is) throws IOException {
        int b = is.read();
        if (b == -1) {
            throw new EOFException();
        } else {
            return b;
        }
    }

    static void a(OutputStream os, int n) throws IOException {
        os.write(n >> 0 & 255);
        os.write(n >> 8 & 255);
        os.write(n >> 16 & 255);
        os.write(n >> 24 & 255);
    }

    static int a(InputStream is) throws IOException {
        int n = 0;
        n = n | e(is) << 0;
        n |= e(is) << 8;
        n |= e(is) << 16;
        n |= e(is) << 24;
        return n;
    }

    static void a(OutputStream os, long n) throws IOException {
        os.write((byte)((int)(n >>> 0)));
        os.write((byte)((int)(n >>> 8)));
        os.write((byte)((int)(n >>> 16)));
        os.write((byte)((int)(n >>> 24)));
        os.write((byte)((int)(n >>> 32)));
        os.write((byte)((int)(n >>> 40)));
        os.write((byte)((int)(n >>> 48)));
        os.write((byte)((int)(n >>> 56)));
    }

    static long b(InputStream is) throws IOException {
        long n = 0L;
        n |= ((long)e(is) & 255L) << 0;
        n |= ((long)e(is) & 255L) << 8;
        n |= ((long)e(is) & 255L) << 16;
        n |= ((long)e(is) & 255L) << 24;
        n |= ((long)e(is) & 255L) << 32;
        n |= ((long)e(is) & 255L) << 40;
        n |= ((long)e(is) & 255L) << 48;
        n |= ((long)e(is) & 255L) << 56;
        return n;
    }

    static void a(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes("UTF-8");
        a(os, (long)b.length);
        os.write(b, 0, b.length);
    }

    static String c(InputStream is) throws IOException {
        int n = (int)b(is);
        byte[] b = a(is, n);
        return new String(b, "UTF-8");
    }

    static void a(Map<String, String> map, OutputStream os) throws IOException {
        if (map != null) {
            a(os, map.size());
            Iterator var3 = map.entrySet().iterator();

            while(var3.hasNext()) {
                Entry<String, String> entry = (Entry)var3.next();
                a(os, (String)entry.getKey());
                a(os, (String)entry.getValue());
            }
        } else {
            a((OutputStream)os, 0);
        }

    }

    static Map<String, String> d(InputStream is) throws IOException {
        int size = a(is);
        Map<String, String> result = size == 0 ? Collections.emptyMap() : new HashMap(size);

        for(int i = 0; i < size; ++i) {
            String key = c(is).intern();
            String value = c(is).intern();
            ((Map)result).put(key, value);
        }

        return (Map)result;
    }

    static class as {
        public long a;
        public String b;
        public String c;
        public long d;
        public long e;
        public long f;
        public long g;
        public Map<String, String> h;

        private as() {
        }

        public as(String key, com.uzmap.pkg.a.b.a.aa entry) {
            this.b = key;
            this.a = (long)entry.a.length;
            this.c = entry.b;
            this.d = entry.c;
            this.e = entry.d;
            this.f = entry.e;
            this.g = entry.f;
            this.h = entry.g;
        }

        public static c.as a(InputStream is) throws IOException {
            c.as entry = new c.as();
            int magic = com.uzmap.pkg.a.b.c1.c.a(is);
            if (magic != 538247942) {
                throw new IOException();
            } else {
                entry.b = com.uzmap.pkg.a.b.c1.c.c(is);
                entry.c = com.uzmap.pkg.a.b.c1.c.c(is);
                if (entry.c.equals("")) {
                    entry.c = null;
                }

                entry.d = com.uzmap.pkg.a.b.c1.c.b(is);
                entry.e = com.uzmap.pkg.a.b.c1.c.b(is);
                entry.f = com.uzmap.pkg.a.b.c1.c.b(is);
                entry.g = com.uzmap.pkg.a.b.c1.c.b(is);
                entry.h = com.uzmap.pkg.a.b.c1.c.d(is);
                return entry;
            }
        }

        public com.uzmap.pkg.a.b.a.aa a(byte[] data) {
            com.uzmap.pkg.a.b.a.aa e = new com.uzmap.pkg.a.b.a.aa();
            e.a = data;
            e.b = this.c;
            e.c = this.d;
            e.d = this.e;
            e.e = this.f;
            e.f = this.g;
            e.g = this.h;
            return e;
        }

        public boolean a(OutputStream os) {
            try {
                com.uzmap.pkg.a.b.c1.c.a(os, 538247942);
                com.uzmap.pkg.a.b.c1.c.a(os, this.b);
                com.uzmap.pkg.a.b.c1.c.a(os, this.c == null ? "" : this.c);
                com.uzmap.pkg.a.b.c1.c.a(os, this.d);
                com.uzmap.pkg.a.b.c1.c.a(os, this.e);
                com.uzmap.pkg.a.b.c1.c.a(os, this.f);
                com.uzmap.pkg.a.b.c1.c.a(os, this.g);
                com.uzmap.pkg.a.b.c1.c.a(this.h, os);
                os.flush();
                return true;
            } catch (IOException var3) {
                p.b("%s", new Object[]{var3.toString()});
                return false;
            }
        }
    }

    private static class bb extends FilterInputStream {
        private int a;

        private bb(InputStream in) {
            super(in);
            this.a = 0;
        }

        public int read() throws IOException {
            int result = super.read();
            if (result != -1) {
                ++this.a;
            }

            return result;
        }

        public int read(byte[] buffer, int offset, int count) throws IOException {
            int result = super.read(buffer, offset, count);
            if (result != -1) {
                this.a += result;
            }

            return result;
        }
    }
}
