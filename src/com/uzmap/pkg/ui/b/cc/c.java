package com.uzmap.pkg.ui.b.cc;

import android.os.SystemClock;
import com.uzmap.pkg.ui.b.p;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

public class c implements com.uzmap.pkg.ui.b.a {
    private final Map<String, c.a1> a;
    private final File c;
    private final int d;
    private long b;

    public c(File rootDirectory) {
        this(rootDirectory, 5242880);
    }

    public c(File rootDirectory, int maxCacheSizeInBytes) {
        this.a = new LinkedHashMap(16, 0.75F, true);
        this.b = 0L;
        this.c = rootDirectory;
        this.d = maxCacheSizeInBytes;
    }

    private static byte[] a(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];

        int count;
        int pos;
        for (pos = 0; pos < length && (count = in.read(bytes, pos, length - pos)) != -1; pos += count) {
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
        os.write((byte) ((int) (n >>> 0)));
        os.write((byte) ((int) (n >>> 8)));
        os.write((byte) ((int) (n >>> 16)));
        os.write((byte) ((int) (n >>> 24)));
        os.write((byte) ((int) (n >>> 32)));
        os.write((byte) ((int) (n >>> 40)));
        os.write((byte) ((int) (n >>> 48)));
        os.write((byte) ((int) (n >>> 56)));
    }

    static long b(InputStream is) throws IOException {
        long n = 0L;
        n |= ((long) e(is) & 255L) << 0;
        n |= ((long) e(is) & 255L) << 8;
        n |= ((long) e(is) & 255L) << 16;
        n |= ((long) e(is) & 255L) << 24;
        n |= ((long) e(is) & 255L) << 32;
        n |= ((long) e(is) & 255L) << 40;
        n |= ((long) e(is) & 255L) << 48;
        n |= ((long) e(is) & 255L) << 56;
        return n;
    }

    static void a(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes(StandardCharsets.UTF_8);
        a(os, (long) b.length);
        os.write(b, 0, b.length);
    }

    static String c(InputStream is) throws IOException {
        int n = (int) b(is);
        byte[] b = a(is, n);
        return new String(b, StandardCharsets.UTF_8);
    }

    static void a(Map<String, String> map, OutputStream os) throws IOException {
        if (map != null) {
            a(os, map.size());
            Iterator var3 = map.entrySet().iterator();

            while (var3.hasNext()) {
                Entry<String, String> entry = (Entry) var3.next();
                a(os, entry.getKey());
                a(os, entry.getValue());
            }
        } else {
            a(os, 0);
        }

    }

    static Map<String, String> d(InputStream is) throws IOException {
        int size = a(is);
        Map<String, String> result = size == 0 ? Collections.emptyMap() : new HashMap(size);

        for (int i = 0; i < size; ++i) {
            String key = c(is).intern();
            String value = c(is).intern();
            ((Map) result).put(key, value);
        }

        return result;
    }

    public synchronized com.uzmap.pkg.ui.b.a.a1 a(String key) {
        c.a1 entry = this.a.get(key);
        if (entry == null) {
            return null;
        } else {
            File file = this.c(key);
            c.b cis = null;

            try {
                cis = new c.b(new BufferedInputStream(new FileInputStream(file)), null);
                a(cis);
                byte[] data = a(cis, (int) (file.length() - (long) cis.a));
                com.uzmap.pkg.ui.b.a.a1 var7 = entry.a(data);
                return var7;
            } catch (IOException var17) {
                p.b("%s: %s", file.getAbsolutePath(), var17.toString());
                this.b(key);
                return null;
            } catch (NegativeArraySizeException var18) {
                p.b("%s: %s", file.getAbsolutePath(), var18.toString());
                this.b(key);
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
        if (!this.c.exists()) {
            if (!this.c.mkdirs()) {
                p.c("Unable to create cache dir %s", this.c.getAbsolutePath());
            }

        } else {
            File[] files = this.c.listFiles();
            if (files != null) {
                File[] var5 = files;
                int var4 = files.length;

                for (int var3 = 0; var3 < var4; ++var3) {
                    File file = var5[var3];
                    BufferedInputStream fis = null;

                    try {
                        fis = new BufferedInputStream(new FileInputStream(file));
                        c.a1 entry = a1.a(fis);
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

    public synchronized void a(String key, com.uzmap.pkg.ui.b.a.a1 entry) {
        this.a(entry.a.length);
        File file = this.c(key);

        try {
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
            c.a1 e = new c.a1(key, entry);
            boolean success = e.a(fos);
            if (!success) {
                fos.close();
                p.b("Failed to write header for %s", file.getAbsolutePath());
                throw new IOException();
            } else {
                fos.write(entry.a);
                fos.close();
                this.a(key, e);
            }
        } catch (IOException var7) {
            boolean deleted = file.delete();
            if (!deleted) {
                p.b("Could not clean up file %s", file.getAbsolutePath());
            }

        }
    }

    public synchronized void b(String key) {
        boolean deleted = this.c(key).delete();
        this.e(key);
        if (!deleted) {
            p.b("Could not delete cache entry for key=%s, filename=%s", key, this.d(key));
        }

    }

    private String d(String key) {
        int firstHalfLength = key.length() / 2;
        String localFilename = String.valueOf(key.substring(0, firstHalfLength).hashCode());
        localFilename = localFilename + key.substring(firstHalfLength).hashCode();
        return localFilename;
    }

    public File c(String key) {
        return new File(this.c, this.d(key));
    }

    private void a(int neededSpace) {
        if (this.b + (long) neededSpace >= (long) this.d) {
            long before = this.b;
            int prunedFiles = 0;
            long startTime = SystemClock.elapsedRealtime();
            Iterator iterator = this.a.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<String, c.a1> entry = (Entry) iterator.next();
                c.a1 e = entry.getValue();
                boolean deleted = this.c(e.b).delete();
                if (deleted) {
                    this.b -= e.a;
                } else {
                    p.b("Could not delete cache entry for key=%s, filename=%s", e.b, this.d(e.b));
                }

                iterator.remove();
                ++prunedFiles;
                if ((float) (this.b + (long) neededSpace) < (float) this.d * 0.9F) {
                    break;
                }
            }

            p.a("pruned %d files, %d bytes, %d ms", prunedFiles, this.b - before, SystemClock.elapsedRealtime() - startTime);
        }
    }

    private void a(String key, c.a1 entry) {
        if (!this.a.containsKey(key)) {
            this.b += entry.a;
        } else {
            c.a1 oldEntry = this.a.get(key);
            this.b += entry.a - oldEntry.a;
        }

        this.a.put(key, entry);
    }

    private void e(String key) {
        c.a1 entry = this.a.get(key);
        if (entry != null) {
            this.b -= entry.a;
            this.a.remove(key);
        }

    }

    static class a1 {
        public long a;
        public String b;
        public String c;
        public long d;
        public long e;
        public long f;
        public long g;
        public Map<String, String> h;

        private a1() {
        }

        public a1(String key, com.uzmap.pkg.ui.b.a.a1 entry) {
            this.b = key;
            this.a = entry.a.length;
            this.c = entry.b;
            this.d = entry.c;
            this.e = entry.d;
            this.f = entry.e;
            this.g = entry.f;
            this.h = entry.g;
        }

        public static c.a1 a(InputStream is) throws IOException {
            c.a1 entry = new c.a1();
            int magic = com.uzmap.pkg.ui.b.cc.c.a(is);
            if (magic != 538247942) {
                throw new IOException();
            } else {
                entry.b = com.uzmap.pkg.ui.b.cc.c.c(is);
                entry.c = com.uzmap.pkg.ui.b.cc.c.c(is);
                if (entry.c.equals("")) {
                    entry.c = null;
                }

                entry.d = com.uzmap.pkg.ui.b.cc.c.b(is);
                entry.e = com.uzmap.pkg.ui.b.cc.c.b(is);
                entry.f = com.uzmap.pkg.ui.b.cc.c.b(is);
                entry.g = com.uzmap.pkg.ui.b.cc.c.b(is);
                entry.h = com.uzmap.pkg.ui.b.cc.c.d(is);
                return entry;
            }
        }

        public com.uzmap.pkg.ui.b.a.a1 a(byte[] data) {
            com.uzmap.pkg.ui.b.a.a1 e = new com.uzmap.pkg.ui.b.a.a1();
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
                com.uzmap.pkg.ui.b.cc.c.a(os, 538247942);
                com.uzmap.pkg.ui.b.cc.c.a(os, this.b);
                com.uzmap.pkg.ui.b.cc.c.a(os, this.c == null ? "" : this.c);
                com.uzmap.pkg.ui.b.cc.c.a(os, this.d);
                com.uzmap.pkg.ui.b.cc.c.a(os, this.e);
                com.uzmap.pkg.ui.b.cc.c.a(os, this.f);
                com.uzmap.pkg.ui.b.cc.c.a(os, this.g);
                com.uzmap.pkg.ui.b.cc.c.a(this.h, os);
                os.flush();
                return true;
            } catch (IOException var3) {
                p.b("%s", var3.toString());
                return false;
            }
        }
    }

    private static class b extends FilterInputStream {
        private int a;

        private b(InputStream in) {
            super(in);
            this.a = 0;
        }

        // $FF: synthetic method
        b(InputStream var1, c.b var2) {
            this(var1);
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
