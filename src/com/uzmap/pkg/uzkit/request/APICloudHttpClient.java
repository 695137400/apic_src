//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.uzmap.pkg.a.b.e;
import com.uzmap.pkg.a.b.i;
import com.uzmap.pkg.a.b.k;
import com.uzmap.pkg.a.b.o;
import com.uzmap.pkg.a.b.c.d;
import com.uzmap.pkg.a.b.c.j;
import com.uzmap.pkg.a.b.c.d.c;
import com.uzmap.pkg.a.b.d.b;
import com.uzmap.pkg.a.b.d.c.a;

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
        com.uzmap.pkg.a.b.d.b.a(context);
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

        this.a.a(req);
    }

    public HttpResult doRequest(Request req) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("Network can not run in ui thread");
        } else {
            j<i> future = j.a();
            req.setErrorListener(future);
            req.setSyncListener(future);
            this.a.a(req);

            try {
                i response = (i)future.get();
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
        com.uzmap.pkg.a.b.c.d.d listener = new com.uzmap.pkg.a.b.c.d.d() {
            public void onResponse(c response, boolean isImmediate) {
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
        c container = this.b.a(option.a, listener, option.b, option.c, option.d);
        return container.a();
    }

    public void disPlayImage(APICloudHttpClient.ImageOption option, ImageView view) {
        this.checkLoaderQueue();
        com.uzmap.pkg.a.b.c.d.d listener = com.uzmap.pkg.a.b.c.d.a(view, option.e, option.f);
        this.b.a(option.a, listener, option.b, option.c, option.d);
    }

    public void download(HttpDownload request) {
        this.checkLoaderQueue();
        this.b.a(request);
    }

    public void cancelDownload(Object tag) {
    }

    public final APICloudHttpClient.ImageEntity hasDiskImageCache(String url) {
        this.checkLoaderQueue();
        a entity = this.b.a(url);
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
            this.b.a(timeThreshold);
        }

    }

    public String getCacheRootDir() {
        return this.d;
    }

    private k requestQueueInitialize() {
        if (this.a == null) {
            this.a = com.uzmap.pkg.a.b.c.k.a(this.c, this.d, new APICloudHttpClient.InUrlRewriter((APICloudHttpClient.InUrlRewriter)null));
        }

        return this.a;
    }

    private void checkLoaderQueue() {
        if (this.b == null) {
            this.b = com.uzmap.pkg.a.b.c.k.b(this.c, this.d, new APICloudHttpClient.InUrlRewriter((APICloudHttpClient.InUrlRewriter)null));
        }

    }

    public static APICloudHttpClient.ImageOption builder(String url) {
        return new APICloudHttpClient.ImageOption(url);
    }

    public interface BitmapListener {
        void onFinish(Bitmap var1, boolean var2);

        void onError(int var1);
    }

    public static class ImageEntity extends a {
        public ImageEntity() {
        }
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

    private class InUrlRewriter implements com.uzmap.pkg.a.b.c.g.a {
        private InUrlRewriter() {
        }

        public String rewriteUrl(String originalUrl) {
            return com.uzmap.pkg.a.b.e.f(originalUrl);
        }
    }
}
