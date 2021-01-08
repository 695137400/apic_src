package com.uzmap.pkg.uzkit.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.uzmap.pkg.a.b.cc.d;
import com.uzmap.pkg.a.b.cc.g;
import com.uzmap.pkg.a.b.i;
import com.uzmap.pkg.a.b.j;
import com.uzmap.pkg.a.b.k;
import com.uzmap.pkg.a.b.o;

public final class APICloudHttpClient {
    private k a;
    private d b;
    private Context c;
    private String d;
    private static APICloudHttpClient e;

    private APICloudHttpClient(Context context) {
        this.c = context.getApplicationContext();
        this.d = com.uzmap.pkg.a.b.e.a(context);
        this.requestQueueInitialize();
        com.uzmap.pkg.a.b.dd.b.a(context);
    }

    public static APICloudHttpClient instance() {
        if (e == null) {
            throw new RuntimeException("not create instance, please call 'createInstance' at first");
        } else {
            return e;
        }
    }

    public static synchronized APICloudHttpClient createInstance(Context context) {
        if (e == null) {
            e = new APICloudHttpClient(context);
        }

        return e;
    }

    public void request(Request req) {
        this.request(req, "engine");
    }

    public void request(Request req, String tag) {
        if (req.getTag() == null) {
            req.setTag(TextUtils.isEmpty(tag) ? "engine" : tag);
        }

        this.a.a((j) req);
    }

    public HttpResult doRequest(Request req) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("Network can not run in ui thread");
        } else {
            com.uzmap.pkg.a.b.cc.j<i> future = com.uzmap.pkg.a.b.cc.j.a();
            req.setErrorListener(future);
            req.setSyncListener(future);
            this.a.a((j) req);

            try {
                i response = future.get();
                if (response != null) {
                    HttpResult result = new HttpResult(response.a);
                    result.headers = response.c;
                    result.data = response.a();
                    return result;
                }
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return null;
        }
    }

    public void cancelRequests(Object tag) {
        if (this.a != null) {
            this.a.a(tag);
        }

    }

    public Bitmap getImage(APICloudHttpClient.ImageOption option, final APICloudHttpClient.BitmapListener callback) {
        this.checkLoaderQueue();
        com.uzmap.pkg.a.b.cc.d.d1 listener = new com.uzmap.pkg.a.b.cc.d.d1() {
            public void onResponse(com.uzmap.pkg.a.b.cc.d.c1 response, boolean isImmediate) {
                if (callback != null) {
                    callback.onFinish(response.a(), isImmediate);
                }

            }

            public void onErrorResponse(o error) {
                if (callback != null) {
                    callback.onError(error.b());
                }

            }
        };
        com.uzmap.pkg.a.b.cc.d.c1 container = this.b.abm(option.a, listener, option.b, option.c, option.d);
        return container.a();
    }

    public void disPlayImage(APICloudHttpClient.ImageOption option, ImageView view) {
        this.checkLoaderQueue();
        com.uzmap.pkg.a.b.cc.d.d1 listener = com.uzmap.pkg.a.b.cc.d.abm(view, option.e, option.f);
        this.b.abm(option.a, listener, option.b, option.c, option.d);
    }

    public void download(HttpDownload request) {
        this.checkLoaderQueue();
        this.b.abm(request);
    }

    public void cancelDownload(Object tag) {
    }

    public final APICloudHttpClient.ImageEntity hasDiskImageCache(String url) {
        this.checkLoaderQueue();
        com.uzmap.pkg.a.b.dd.c.a1 entity = this.b.abm(url);
        if (entity != null) {
            APICloudHttpClient.ImageEntity iEntity = new APICloudHttpClient.ImageEntity();
            entity.copy(iEntity);
            return iEntity;
        } else {
            return null;
        }
    }

    public void clearDiskImageCache(long timeThreshold) {
        if (this.b != null) {
            this.b.abm(timeThreshold);
        }

    }

    public String getCacheRootDir() {
        return this.d;
    }

    private k requestQueueInitialize() {
        if (this.a == null) {
            this.a = com.uzmap.pkg.a.b.cc.k.a(this.c, this.d, new InUrlRewriter(null));
        }

        return this.a;
    }

    private void checkLoaderQueue() {
        if (this.b == null) {
            this.b = com.uzmap.pkg.a.b.cc.k.b(this.c, this.d, new APICloudHttpClient.InUrlRewriter(null));
        }

    }

    public static APICloudHttpClient.ImageOption builder(String url) {
        return new APICloudHttpClient.ImageOption(url);
    }

    public interface BitmapListener {
        void onFinish(Bitmap var1, boolean var2);

        void onError(int var1);
    }

    public static class ImageEntity extends com.uzmap.pkg.a.b.dd.c.a1 {
    }

    public static class ImageOption {
        public String a;
        public int b;
        public int c;
        public ScaleType d;
        public int e;
        public int f;

        public ImageOption(String url) {
            this.a = url;
        }

        public void setMaxSize(int maxWidth, int maxHeight) {
            this.b = maxWidth;
            this.c = maxHeight;
        }

        public void setScaleType(ScaleType type) {
            this.d = type;
        }

        public void setErrorImageResId(int errorImageResId) {
            this.f = errorImageResId;
        }

        public void setDefaultImageResId(int defaultImageResId) {
            this.e = defaultImageResId;
        }
    }

    private class InUrlRewriter implements g.a {
        private InUrlRewriter() {
        }

        public String rewriteUrl(String originalUrl) {
            return com.uzmap.pkg.a.b.e.f(originalUrl);
        }

        // $FF: synthetic method
        InUrlRewriter(APICloudHttpClient.InUrlRewriter var2) {
            this();
        }
    }
}
