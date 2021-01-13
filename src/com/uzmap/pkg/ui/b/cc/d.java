package com.uzmap.pkg.ui.b.cc;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.uzmap.pkg.ui.b.l;
import com.uzmap.pkg.ui.b.o;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class d {
    private final com.uzmap.pkg.ui.b.k a;
    private final b1 c;
    private final HashMap<String, a1> d = new HashMap();
    private final HashMap<String, a1> e = new HashMap();
    private final Handler f = new Handler(Looper.getMainLooper());
    private final int b = 100;
    private Runnable g;

    public d(com.uzmap.pkg.ui.b.k queue, b1 cache) {
        this.a = queue;
        this.c = cache;
    }

    public static d1 abm(final ImageView view, final int defaultImageResId, final int errorImageResId) {
        return new d1() {
            public void onErrorResponse(o error) {
                if (errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                }

            }

            public void onResponse(c1 response, boolean isImmediate) {
                if (response.a() != null) {
                    view.setImageBitmap(response.a());
                } else if (defaultImageResId != 0) {
                    view.setImageResource(defaultImageResId);
                }

            }
        };
    }

    private static String abm(String url, int maxWidth, int maxHeight, ScaleType scaleType) {
        return (new StringBuilder(url.length() + 12)).append("#W").append(maxWidth).append("#H").append(maxHeight).append("#S").append(scaleType.ordinal()).append(url).toString();
    }

    public c1 abm(String requestUrl, d1 imageListener, int maxWidth, int maxHeight, ScaleType scaleType) {
        this.abm();
        String cacheKey = abm(requestUrl, maxWidth, maxHeight, scaleType);
        Bitmap cachedBitmap = this.c.a(cacheKey);
        c1 imageContainer;
        if (cachedBitmap != null) {
            imageContainer = new c1(cachedBitmap, requestUrl, null, null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        } else {
            imageContainer = new c1(null, requestUrl, cacheKey, imageListener);
            imageListener.onResponse(imageContainer, true);
            a1 request = this.d.get(cacheKey);
            if (request != null) {
                request.a(imageContainer);
                return imageContainer;
            } else {
                com.uzmap.pkg.ui.b.j<Bitmap> newRequest = this.abm(requestUrl, maxWidth, maxHeight, scaleType, cacheKey);
                this.a.a(newRequest);
                this.d.put(cacheKey, new a1(newRequest, imageContainer));
                return imageContainer;
            }
        }
    }

    protected com.uzmap.pkg.ui.b.j<Bitmap> abm(String requestUrl, int maxWidth, int maxHeight, ScaleType scaleType, final String cacheKey) {
        return new h(requestUrl, new l.b<Bitmap>() {
            public void a(Bitmap response) {
                abm(cacheKey, response);
            }
        }, maxWidth, maxHeight, scaleType, Config.RGB_565, new l.a1() {
            public void onErrorResponse(o error) {
                abm(cacheKey, error);
            }
        });
    }

    protected void abm(String cacheKey, Bitmap response) {
        this.c.a(cacheKey, response);
        a1 request = this.d.remove(cacheKey);
        if (request != null) {
            request.c = response;
            this.abm(cacheKey, request);
        }

    }

    protected void abm(String cacheKey, o error) {
        a1 request = this.d.remove(cacheKey);
        if (request != null) {
            request.a(error);
            this.abm(cacheKey, request);
        }

    }

    private void abm(String cacheKey, a1 request) {
        this.e.put(cacheKey, request);
        if (this.g == null) {
            this.g = new Runnable() {
                public void run() {
                    Iterator var2 = e.values().iterator();

                    while (var2.hasNext()) {
                        a1 bir = (a1) var2.next();
                        Iterator var4 = bir.e.iterator();

                        while (var4.hasNext()) {
                            c1 container = (c1) var4.next();
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

                    e.clear();
                    g = null;
                }
            };
            this.f.postDelayed(this.g, this.b);
        }

    }

    private void abm() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    public void abm(com.uzmap.pkg.ui.b.j<?> req) {
        this.a.a(req);
    }

    public final com.uzmap.pkg.ui.b.dd.c.a1 abm(String url) {
        return this.c.b(url);
    }

    public void abm(long timeThreshold) {
        this.c.a(timeThreshold);
    }

    public interface b1 {
        Bitmap a(String var1);

        void a(String var1, Bitmap var2);

        com.uzmap.pkg.ui.b.dd.c.a1 b(String var1);

        void a(long var1);
    }

    public interface d1 extends l.a1 {
        void onResponse(c1 var1, boolean var2);
    }

    private class a1 {
        private final com.uzmap.pkg.ui.b.j<?> b;
        private final LinkedList<c1> e = new LinkedList();
        private Bitmap c;
        private o d;

        public a1(com.uzmap.pkg.ui.b.j<?> request, c1 container) {
            this.b = request;
            this.e.add(container);
        }

        public void a(o error) {
            this.d = error;
        }

        public o a() {
            return this.d;
        }

        public void a(c1 container) {
            this.e.add(container);
        }
    }

    public class c1 {
        private final d1 c;
        private final String d;
        private final String e;
        private Bitmap b;

        public c1(Bitmap bitmap, String requestUrl, String cacheKey, d1 listener) {
            this.b = bitmap;
            this.e = requestUrl;
            this.d = cacheKey;
            this.c = listener;
        }

        public Bitmap a() {
            return this.b;
        }
    }
}
