//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit.fineHttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.b;
import com.uzmap.pkg.uzcore.external.UzResourceCache;

public class UZHttpClient {
    private k mAjaxPool;
    private l mDownloadPool;
    private static UZHttpClient instance;
    static boolean TransitionPhase = true;

    public UZHttpClient() {
    }

    public static synchronized UZHttpClient get() {
        if (instance == null) {
            if (!TransitionPhase) {
                instance = new UZHttpClient();
            } else {
                instance = new m(b.a().b(), (Object)null);
            }
        }

        return instance;
    }

    public static String makeKey(String anyStr) {
        String result = "";

        try {
            result = Integer.toHexString(anyStr.hashCode());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return result;
    }

    public String executeSynchronous(RequestParam argument) {
        if (argument == null) {
            return null;
        } else {
            u<?> request = null;
            switch(argument.method) {
                case 0:
                    request = new p(argument);
                    break;
                case 1:
                    request = new s(argument);
                    break;
                case 2:
                    request = new r(argument);
                    break;
                case 3:
                    request = new t(argument);
                    break;
                case 4:
                    request = new n(argument);
            }

            if (request != null) {
                String result = (String)((u)request).e();
                return !TextUtils.isEmpty(result) ? result : null;
            } else {
                return null;
            }
        }
    }

    public void execute(RequestParam argument, RequestListener listener) {
        if (argument != null) {
            u<?> request = null;
            switch(argument.method) {
                case 0:
                    request = new p(argument);
                    break;
                case 1:
                    request = new s(argument);
                    break;
                case 2:
                    request = new r(argument);
                    break;
                case 3:
                    request = new t(argument);
                    break;
                case 4:
                    request = new n(argument);
                    break;
                case 5:
                    request = new o(argument);
            }

            if (request != null) {
                ((u)request).a(listener);
                if (argument.method != 5) {
                    if (this.mAjaxPool == null) {
                        this.mAjaxPool = new k();
                    }

                    this.mAjaxPool.a((Runnable)request);
                } else {
                    if (this.mDownloadPool == null) {
                        this.mDownloadPool = new l();
                    }

                    if (this.mDownloadPool.a(argument.getTag())) {
                        return;
                    }

                    this.mDownloadPool.a((Runnable)request);
                }
            }

        }
    }

    public void executeBitmap(String path, BitmapListener callback, Options options) {
        q request = new q(path, callback);
        request.a(options);
        if (this.mDownloadPool == null) {
            this.mDownloadPool = new l();
        }

        if (!this.mDownloadPool.a(request.a_())) {
            this.mDownloadPool.a(request);
        }
    }

    public Bitmap sysExecuteBitmap(String path) {
        return UzResourceCache.get().getImage(path);
    }

    public void cancel(Object tag) {
        if (this.mAjaxPool != null) {
            this.mAjaxPool.a(tag);
        }

    }

    public void cancelDownload(Object tag) {
        if (this.mDownloadPool != null) {
            this.mDownloadPool.b(tag);
        }

    }
}
