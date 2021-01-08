//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import java.io.File;

public class c extends LruCache<String, c.aa> implements com.uzmap.pkg.a.b.c1.d.b {
    static final int a = (int)Runtime.getRuntime().maxMemory() / 8;
    private String b;
    private String c;
    private c.b d;
    private static c e;

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

    private boolean f(String rootCacheDir) {
        return this.c.equals(rootCacheDir);
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

    public Bitmap a(String url) {
        c.aa cache = (c.aa)this.get(url);
        return cache != null ? cache.e : null;
    }

    public void a(String url, Bitmap bitmap) {
        c.aa entity = new c.aa(0);
        entity.b = url;
        entity.e = bitmap;
        this.put(url, entity);
    }

    public c.aa b(String url) {
        return this.d.a(url);
    }

    public c.aa a(String url, String local, String thumbnail) {
        return this.d.a(url, local, thumbnail);
    }

    public void a(long timeThreshold) {
        this.evictAll();
        this.d.a(timeThreshold);
    }

    protected int a(String key, c.aa value) {
        return value == null ? 0 : value.sizeOf();
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

    public String e(String url) {
        return this.b + d(url);
    }

    public static File a(File image) {
        String name = image.getName();
        String path = image.getParent();
        String thumb = path + "/thumb/" + name;
        return new File(thumb);
    }

    public static class aa {
        public int a;
        public String b;
        public String c;
        public String d;
        public Bitmap e;
        public long f;

        public aa() {
            this(0);
        }

        public aa(int type) {
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

        public void copy(c.aa outEntity) {
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

    private class b extends LruCache<String, c.aa> {
        public b(String rootCacheDir) {
            super(2147483647);
            this.b(rootCacheDir);
        }

        public c.aa a(String url) {
            if (url != null) {
                String key = com.uzmap.pkg.a.b.d1.c.d(url);
                return (c.aa)this.get(key);
            } else {
                return null;
            }
        }

        public c.aa a(String url, c.aa entity) {
            String key = com.uzmap.pkg.a.b.d1.c.d(url);
            this.put(key, entity);
            return entity;
        }

        public c.aa a(String url, String local, String thumbnail) {
            c.aa entity = new c.aa(1);
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

        protected int b(String key, c.aa value) {
            return value == null ? 0 : value.sizeOf();
        }

        private void b(String rootCacheDir) {
            (new Thread() {
                public void run() {
                    synchronized(c.this) {
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

                    for(int var4 = 0; var4 < var5; ++var4) {
                        File file = var6[var4];
                        if (!file.isDirectory()) {
                            String name = file.getName();
                            if (!TextUtils.isEmpty(name)) {
                                c.aa entity = new c.aa(1);
                                String local = file.getAbsolutePath();
                                entity.c = local;
                                long lastModified = file.lastModified();
                                entity.f = lastModified;
                                File thumb = com.uzmap.pkg.a.b.d1.c.a(file);
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
    }
}
