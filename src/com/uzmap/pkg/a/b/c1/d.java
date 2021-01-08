//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.uzmap.pkg.a.b.j;
import com.uzmap.pkg.a.b.k;
import com.uzmap.pkg.a.b.o;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class d {
    private final k a;
    private int b = 100;
    private  com.uzmap.pkg.a.b.c1.d.b c;
    private  HashMap<String, com.uzmap.pkg.a.b.c1.d.a> d = new HashMap();
    private final HashMap<String, com.uzmap.pkg.a.b.c1.d.a> e = new HashMap();
    private final Handler f = new Handler(Looper.getMainLooper());
    private Runnable g;

    public d(k queue, com.uzmap.pkg.a.b.c1.d.b cache) {
        this.a = queue;
        this.c = cache;
    }

    public  com.uzmap.pkg.a.b.c1.d.d1 a(final ImageView view, final int defaultImageResId, final int errorImageResId) {
        return new com.uzmap.pkg.a.b.c1.d.d1() {
            public void onErrorResponse(o error) {
                if (errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                }

            }

            public void onResponse(com.uzmap.pkg.a.b.c1.d.c response, boolean isImmediate) {
                if (response.a() != null) {
                    view.setImageBitmap(response.a());
                } else if (defaultImageResId != 0) {
                    view.setImageResource(defaultImageResId);
                }

            }
        };
    }

    public com.uzmap.pkg.a.b.c1.d.c a(String requestUrl, com.uzmap.pkg.a.b.c1.d.d1 imageListener, int maxWidth, int maxHeight, ScaleType scaleType) {
        this.a();
        String cacheKey = a(requestUrl, maxWidth, maxHeight, scaleType);
        Bitmap cachedBitmap = this.c.a(cacheKey);
        com.uzmap.pkg.a.b.c1.d.c imageContainer;
        if (cachedBitmap != null) {
            imageContainer = new com.uzmap.pkg.a.b.c1.d.c(cachedBitmap, requestUrl, (String)null, (com.uzmap.pkg.a.b.c1.d.d1)null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        } else {
            imageContainer = new com.uzmap.pkg.a.b.c1.d.c((Bitmap)null, requestUrl, cacheKey, imageListener);
            imageListener.onResponse(imageContainer, true);
            com.uzmap.pkg.a.b.c1.d.a request = (com.uzmap.pkg.a.b.c1.d.a)this.d.get(cacheKey);
            if (request != null) {
                request.a(imageContainer);
                return imageContainer;
            } else {
                j<Bitmap> newRequest = this.a(requestUrl, maxWidth, maxHeight, scaleType, cacheKey);
                this.a.a(newRequest);
                this.d.put(cacheKey, new com.uzmap.pkg.a.b.c1.d.a(newRequest, imageContainer));
                return imageContainer;
            }
        }
    }

    protected j<Bitmap> a(String requestUrl, int maxWidth, int maxHeight, ScaleType scaleType, final String cacheKey) {
        return new h(requestUrl, new com.uzmap.pkg.a.b.l.b<Bitmap>() {
            public void a(Bitmap response) {
                d.this.a(cacheKey, response);
            }
        }, maxWidth, maxHeight, scaleType, Config.RGB_565, new com.uzmap.pkg.a.b.l.a() {
            public void onErrorResponse(o error) {
                d.this.a(cacheKey, error);
            }
        });
    }

    protected void a(String cacheKey, Bitmap response) {
        this.c.a(cacheKey, response);
        com.uzmap.pkg.a.b.c1.d.a request = (com.uzmap.pkg.a.b.c1.d.a)this.d.remove(cacheKey);
        if (request != null) {
            request.c = response;
            this.a(cacheKey, request);
        }

    }

    protected void a(String cacheKey, o error) {
        com.uzmap.pkg.a.b.c1.d.a request = (com.uzmap.pkg.a.b.c1.d.a)this.d.remove(cacheKey);
        if (request != null) {
            request.a(error);
            this.a(cacheKey, request);
        }

    }

    private void a(String cacheKey, com.uzmap.pkg.a.b.c1.d.a request) {
        this.e.put(cacheKey, request);
        if (this.g == null) {
            this.g = new Runnable() {
                public void run() {
                    Iterator var2 = d.this.e.values().iterator();

                    while(var2.hasNext()) {
                        com.uzmap.pkg.a.b.c1.d.a bir = (com.uzmap.pkg.a.b.c1.d.a)var2.next();
                        Iterator var4 = bir.e.iterator();

                        while(var4.hasNext()) {
                            com.uzmap.pkg.a.b.c1.d.c container = (com.uzmap.pkg.a.b.c1.d.c)var4.next();
                            if (container.c != null) {
                                if (bir.a() == null) {
                                    container.b = bir.c;
                                    if (container.c != null) {
                                        container.c.onResponse(container, false);
                                    }
                                } else if (container.c != null) {
                                    container.c.onErrorResponse(bir.a());
                                }
                            }
                        }
                    }

                    d.this.e.clear();
                    d.this.g = null;
                }
            };
            this.f.postDelayed(this.g, (long)this.b);
        }

    }

    private void a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    private static String a(String url, int maxWidth, int maxHeight, ScaleType scaleType) {
        return (new StringBuilder(url.length() + 12)).append("#W").append(maxWidth).append("#H").append(maxHeight).append("#S").append(scaleType.ordinal()).append(url).toString();
    }

    public void a(j<?> req) {
        this.a.a(req);
    }

    public final com.uzmap.pkg.a.b.d1.c.aa a(String url) {
        return this.c.b(url);
    }

    public void a(long timeThreshold) {
        this.c.a(timeThreshold);
    }

    private class a {
        private final j<?> b;
        private Bitmap c;
        private o d;
        private final LinkedList<com.uzmap.pkg.a.b.c1.d.c> e = new LinkedList();

        public a(j<?> request, com.uzmap.pkg.a.b.c1.d.c container) {
            this.b = request;
            this.e.add(container);
        }

        public void a(o error) {
            this.d = error;
        }

        public o a() {
            return this.d;
        }

        public void a(com.uzmap.pkg.a.b.c1.d.c container) {
            this.e.add(container);
        }
    }

    public interface b {
        Bitmap a(String var1);

        void a(String var1, Bitmap var2);

        com.uzmap.pkg.a.b.d1.c.aa b(String var1);

        void a(long var1);
    }

    public class c {
        private Bitmap b;
        private final com.uzmap.pkg.a.b.c1.d.d1 c;
        private final String d;
        private final String e;

        public c(Bitmap bitmap, String requestUrl, String cacheKey, com.uzmap.pkg.a.b.c1.d.d1 listener) {
            this.b = bitmap;
            this.e = requestUrl;
            this.d = cacheKey;
            this.c = listener;
        }

        public Bitmap a() {
            return this.b;
        }
    }

    public interface d1 extends com.uzmap.pkg.a.b.l.a {
        void onResponse(com.uzmap.pkg.a.b.c1.d.c var1, boolean var2);
    }
}
