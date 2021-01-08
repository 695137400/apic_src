package com.uzmap.pkg.uzkit.fineHttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.http.AndroidHttpClient;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.UzResourceCache;
import com.uzmap.pkg.uzkit.UZUtility;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class q extends u<Bitmap> {
    static final Handler a = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            q.a queue = (q.a) msg.obj;
            if (queue != null) {
                queue.a();
            }

        }
    };
    private final String b;
    private final BitmapListener c;
    private Options m;
    private String n;

    public q(String url, BitmapListener callback) {
        super(new RequestParam());
        this.b = url;
        this.c = callback;
    }

    public void a(Options options) {
        this.m = options;
    }

    public Object a_() {
        return this.n != null ? this.n : (this.n = UZCoreUtil.random(this.b));
    }

    protected Bitmap b() {
        if (TextUtils.isEmpty(this.b)) {
            q.a queue = new q.a(null, this.c);
            a.sendMessage(a.obtainMessage(0, queue));
            return null;
        } else {
            UzResourceCache resCache = UzResourceCache.get();
            Bitmap result = resCache.getImage(this.b);
            q.a queue;
            if (result != null) {
                queue = new q.a(result, this.c);
                a.sendMessage(a.obtainMessage(0, queue));
                return null;
            } else if (this.b.startsWith("http")) {
                this.a(this.b, this.c, this.m);
                return null;
            } else {
                try {
                    InputStream input = UZUtility.guessInputStream(this.b);
                    if (input != null) {
                        Bitmap value = BitmapFactory.decodeStream(input);
                        if (value != null) {
                            resCache.cacheImage(this.b, value);
                        }

                        queue = new q.a(result, this.c);
                        a.sendMessage(a.obtainMessage(0, queue));
                        input.close();
                        return null;
                    }
                } catch (Exception var6) {
                    var6.printStackTrace();
                }

                queue = new q.a(null, this.c);
                a.sendMessage(a.obtainMessage(0, queue));
                return null;
            }
        }
    }

    private void a(String url, BitmapListener callback, Options options) {
        Context context = UZUtility.getBaseContext();
        String cacheDir = context.getExternalCacheDir().getAbsolutePath();
        String fileName = UZCoreUtil.random(url) + ".tbm";
        String filePath = cacheDir + File.separator + fileName;
        File localFile = new File(filePath);
        if (localFile.exists()) {
            Bitmap result = null;

            try {
                if (options != null) {
                    result = BitmapFactory.decodeFile(filePath, options);
                } else {
                    result = BitmapFactory.decodeFile(filePath);
                }

                if (result != null) {
                    UzResourceCache.get().cacheImage(url, result);
                }
            } catch (Exception var27) {
                var27.printStackTrace();
            }

            q.a queue = new q.a(result, callback);
            a.sendMessage(a.obtainMessage(0, queue));
        } else {
            AndroidHttpClient client = null;
            HttpGet request = null;
            InputStream inPut = null;
            FileOutputStream outPut = null;

            try {
                client = AndroidHttpClient.newInstance("AndroidDownloadManager", context);
                request = new HttpGet(URI.create(url));
                HttpResponse response = client.execute(request);
                inPut = response.getEntity().getContent();
                outPut = new FileOutputStream(localFile);
                byte[] data = new byte[4096];

                while (true) {
                    int read = inPut.read(data);
                    if (read == -1) {
                        Bitmap result = null;

                        try {
                            if (options != null) {
                                result = BitmapFactory.decodeFile(filePath, options);
                            } else {
                                result = BitmapFactory.decodeFile(filePath);
                            }
                        } catch (Exception var28) {
                            var28.printStackTrace();
                        }

                        if (result != null) {
                            UzResourceCache.get().cacheImage(url, result);
                        }

                        q.a queue = new q.a(result, callback);
                        a.sendMessage(a.obtainMessage(0, queue));
                        return;
                    }

                    outPut.write(data, 0, read);
                }
            } catch (Exception var29) {
                var29.printStackTrace();
            } finally {
                if (request != null) {
                    request.abort();
                }

                if (client != null) {
                    client.close();
                }

                if (outPut != null) {
                    try {
                        outPut.close();
                    } catch (IOException var26) {
                        var26.printStackTrace();
                    }
                }

            }

            q.a queue = new q.a(null, callback);
            a.sendMessage(a.obtainMessage(0, queue));
        }
    }

    public void c() {
    }

    public Bitmap d() {
        return null;
    }

    // $FF: synthetic method
    public Bitmap e() {
        return this.d();
    }

    // $FF: synthetic method
    protected Bitmap f() {
        return this.b();
    }

    private class a {
        public Bitmap a;
        public BitmapListener b;

        public a(Bitmap bitmap, BitmapListener listener) {
            this.a = bitmap;
            this.b = listener;
        }

        public void a() {
            if (this.b != null) {
                int statusCode = this.a != null ? 200 : -1;
                this.b.onResult(statusCode, this.a);
            }

        }
    }
}
