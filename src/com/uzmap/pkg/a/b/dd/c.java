package com.uzmap.pkg.a.b.dd;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import java.io.File;

public class c extends LruCache<String, com.uzmap.pkg.a.b.dd.c.a1> implements com.uzmap.pkg.a.b.cc.d.b1 {
    static final int a = (int) Runtime.getRuntime().maxMemory() / 8;
    private static c e;
    private final String b;
    private final String c;
    private final c.b d;

    private c(String rootCacheDir) {
        super(a);
        this.c = rootCacheDir;
        this.b = (new File(rootCacheDir, "disk")).getAbsolutePath() + File.separator;
        this.d = new c.b(rootCacheDir);
        if (e != null) {
            e.a(100L);
            e = null;
        }

        e = this;
    }

    public static final synchronized c c(String rootCacheDir) {
        if (e != null) {
            if (e.f(rootCacheDir)) {
                return e;
            }

            e.a(100L);
        }

        e = new c(rootCacheDir);
        return e;
    }

    public static String d(String url) {
        String result = "";

        try {
            result = Integer.toHexString(url.hashCode());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return result;
    }

    public static File a(File image) {
        String name = image.getName();
        String path = image.getParent();
        String thumb = path + "/thumb/" + name;
        return new File(thumb);
    }

    private boolean f(String rootCacheDir) {
        return this.c.equals(rootCacheDir);
    }

    public Bitmap a(String url) {
        a1 cache = this.get(url);
        return cache != null ? cache.e : null;
    }

    public void a(String url, Bitmap bitmap) {
        a1 entity = new a1(0);
        entity.b = url;
        entity.e = bitmap;
        this.put(url, entity);
    }

    public a1 b(String url) {
        return this.d.a(url);
    }

    public a1 a(String url, String local, String thumbnail) {
        return this.d.a(url, local, thumbnail);
    }

    public void a(long timeThreshold) {
        this.evictAll();
        this.d.a(timeThreshold);
    }

    protected int a(String key, a1 value) {
        return value == null ? 0 : value.sizeOf();
    }

    public String e(String url) {
        return this.b + d(url);
    }

    // $FF: synthetic method
    protected int sizeOf(String var1, Object var2) {
        return this.a(var1, (a1) var2);
    }

    public static class a1 {
        public int a;
        public String b;
        public String c;
        public String d;
        public Bitmap e;
        public long f;

        public a1() {
            this(0);
        }

        public a1(int type) {
            this.a = type;
        }

        public int sizeOf() {
            if (this.a == 1) {
                return 1;
            } else {
                return this.e != null ? this.e.getRowBytes() * this.e.getHeight() : 1;
            }
        }

        public boolean hasThumbnail() {
            return !TextUtils.isEmpty(this.d);
        }

        public void copy(a1 outEntity) {
            outEntity.a = this.a;
            outEntity.b = this.b;
            outEntity.c = this.c;
            outEntity.d = this.d;
            outEntity.e = this.e;
            outEntity.f = this.f;
        }

        public String toString() {
            if (this.a == 0) {
                return this.b + "@" + this.e;
            } else {
                return this.c != null ? (new File(this.c)).getName() : super.toString();
            }
        }
    }

    private class b extends LruCache<String, a1> {
        public b(String rootCacheDir) {
            super(Integer.MAX_VALUE);
            this.b(rootCacheDir);
        }

        public a1 a(String url) {
            if (url != null) {
                String key = com.uzmap.pkg.a.b.dd.c.d(url);
                return this.get(key);
            } else {
                return null;
            }
        }

        public a1 a(String url, a1 entity) {
            String key = com.uzmap.pkg.a.b.dd.c.d(url);
            this.put(key, entity);
            return entity;
        }

        public a1 a(String url, String local, String thumbnail) {
            a1 entity = new a1(1);
            entity.b = url;
            entity.c = local;
            entity.d = thumbnail;
            return this.a(url, entity);
        }

        public void a(long timeThreshold) {
            this.evictAll();
            if (timeThreshold > 0L) {
                this.b(c.this.c);
            }

        }

        protected int b(String key, a1 value) {
            return value == null ? 0 : value.sizeOf();
        }

        private void b(String rootCacheDir) {
            (new Thread() {
                public void run() {
                    synchronized (c.this) {
                        b.this.a();
                    }
                }
            }).start();
        }

        private void a() {
            File cacheDir = new File(c.this.b);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            } else {
                File[] files = cacheDir.listFiles();
                if (files != null && files.length != 0) {
                    File[] var6 = files;
                    int var5 = files.length;

                    for (int var4 = 0; var4 < var5; ++var4) {
                        File file = var6[var4];
                        if (!file.isDirectory()) {
                            String name = file.getName();
                            if (!TextUtils.isEmpty(name)) {
                                a1 entity = new a1(1);
                                String local = file.getAbsolutePath();
                                entity.c = local;
                                long lastModified = file.lastModified();
                                entity.f = lastModified;
                                File thumb = com.uzmap.pkg.a.b.dd.c.a(file);
                                if (thumb.exists()) {
                                    entity.d = thumb.getAbsolutePath();
                                }

                                this.put(name, entity);
                            }
                        }
                    }

                }
            }
        }

        // $FF: synthetic method
        protected int sizeOf(String var1, a1 var2) {
            return this.b(var1, var2);
        }
    }
}
