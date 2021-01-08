package com.uzmap.pkg.uzkit.fineHttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import com.uzmap.pkg.uzkit.request.*;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

class m extends UZHttpClient {
    private final APICloudHttpClient a;

    public m(Context context, Object o) {
        this.a = APICloudHttpClient.createInstance(context.getApplicationContext());
    }

    public void execute(RequestParam argument, RequestListener listener) {
        if (argument != null && listener != null) {
            Request request = null;
            String url = argument.url;
            int method = argument.method;
            boolean forceDoNotCache = false;
            switch (method) {
                case 0:
                    request = new HttpGet(url);
                    break;
                case 1:
                case 3:
                    HttpParams params = new HttpParams();
                    this.a(argument, params);
                    if (method == 1) {
                        request = new HttpPost(url, params);
                    } else if (method == 3) {
                        request = new HttpPut(url, params);
                    }

                    forceDoNotCache = true;
                    break;
                case 2:
                    request = new HttpHead(url);
                    break;
                case 4:
                    request = new HttpDelete(url);
                    break;
                case 5:
                    HttpDownload download = new HttpDownload(url);
                    download.setAllowResume(argument.allowResume);
                    download.setSavePath(argument.savePath);
                    String defaultSavePath = argument.defaultSavePath;
                    if (TextUtils.isEmpty(defaultSavePath)) {
                        defaultSavePath = this.a.getCacheRootDir();
                    }

                    download.setDefaultSavePath(defaultSavePath);
                    request = download;
            }

            if (request != null) {
                request.addHeader(argument.heads);
                request.setCertificate(argument.capath, argument.capsw);
                request.setCharset(argument.charset);
                request.setNeedReport(argument.report);
                request.setNeedEscape(argument.needEscape);
                request.setNeedErrorInfo(argument.needErrorInfo);
                if (forceDoNotCache) {
                    ((Request) request).setShouldCache(false);
                } else {
                    ((Request) request).setShouldCache(argument.cache);
                }

                request.setTimeout(argument.timeout / 1000);
                ((Request) request).setTag(argument.getTag());
                if (method != 1 && method != 3) {
                    if (method == 5) {
                        this.c(request, listener);
                    } else {
                        this.a(request, listener);
                    }
                } else {
                    this.b(request, listener);
                }

                if (method != 5) {
                    this.a.request(request);
                } else {
                    this.a.download((HttpDownload) request);
                }
            }

        } else {
            throw new IllegalArgumentException("argument and listener can not be empty!");
        }
    }

    public void executeBitmap(String path, BitmapListener callback, Options options) {
    }

    public Bitmap sysExecuteBitmap(String path) {
        return this.a.getImage(APICloudHttpClient.builder(path), null);
    }

    public void cancel(Object tag) {
        this.a.cancelRequests(tag);
    }

    public void cancelDownload(Object tag) {
        this.a.cancelDownload(tag);
    }

    private void a(RequestParam argument, HttpParams params) {
        if (argument != null) {
            params.setBody(argument.body);
            params.setStream(argument.stream);
            List<g> values = argument.values;
            if (values != null) {
                Iterator var5 = values.iterator();

                while (var5.hasNext()) {
                    g item = (g) var5.next();
                    params.addValue(item.a(), item.b());
                }
            }

            List<g> files = argument.files;
            if (files != null) {
                Iterator var6 = files.iterator();

                while (var6.hasNext()) {
                    g item = (g) var6.next();
                    params.addFile(item.a(), item.b());
                }
            }

        }
    }

    private RequestCallback a(Request request, RequestListener listener) {
        RequestCallback callback = new m.c(request, listener);
        request.setCallback(callback);
        return callback;
    }

    private RequestCallback b(Request request, RequestListener listener) {
        RequestCallback callback = new m.d(request, listener);
        request.setCallback(callback);
        return callback;
    }

    private RequestCallback c(Request request, RequestListener listener) {
        RequestCallback callback = new m.a((HttpDownload) request, listener);
        request.setCallback(callback);
        return callback;
    }

    private m.b a(Request request, HttpResult result) {
        String des = result.data;
        int errorType = result.getErrorType();
        byte code;
        switch (errorType) {
            case 1:
                code = 0;
                des = "网络无法连接，请检查网络配置";
                break;
            case 2:
                code = 0;
                des = "连接错误，请检查网络或者请求配置是否正确";
                break;
            case 3:
                code = 3;
                des = "服务器返回数据格式错误";
                break;
            case 4:
                code = 0;
                des = "服务器错误";
                break;
            case 5:
                code = 1;
                des = "网络请求超时，请稍后重试";
                break;
            case 6:
                code = 2;
                if (!request.needErrorInfo()) {
                    des = "权限错误";
                }
                break;
            default:
                code = 0;
                des = "未知错误:" + des;
        }

        return new m.b(code, des);
    }

    private void a(Request request, HttpResult result, RequestListener listener) {
        if (listener != null) {
            int statusCode = result.statusCode;
            if (statusCode == 304) {
                statusCode = 200;
            }

            String content = result.data;
            int code = statusCode;
            String errdes = "";
            if (!result.success()) {
                m.b err = this.a(request, result);
                code = err.a;
                errdes = err.b;
                content = "";
            }

            Response response = new Response(code);
            response.content = content;
            response.statusCode = statusCode;
            response.setError(errdes);
            response.headers = new JSONObject(result.headers);
            listener.onResult(response);
        }
    }

    private class a extends RequestCallback {
        final RequestListener a;
        final JSONObject b;
        final HttpDownload c;
        boolean d;

        public a(HttpDownload request, RequestListener listener) {
            this.a = listener;
            this.c = request;
            this.d = this.a instanceof ProgressListener;
            this.b = new JSONObject();
        }

        public void onFinish(HttpResult result) {
            this.a(result, this.c.getSavePath());
        }

        public void onProgress(long total, double progress) {
            if (this.c.needReport() && this.d) {
                try {
                    this.b.put("fileSize", total);
                    this.b.put("percent", progress);
                    this.b.put("progress", progress);
                    this.b.put("state", 0);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }

                ((ProgressListener) this.a).onProgress(0, this.b);
            }
        }

        private void a(HttpResult result, String savePath) {
            if (this.d) {
                String body = result.data;
                if (!result.success()) {
                    m.b err = m.this.a(this.c, result);
                    body = err.b;
                }

                Object des = body;

                try {
                    des = new JSONObject(body);
                } catch (Exception var8) {
                }

                int status = result.success() ? 1 : 2;

                try {
                    this.b.put("state", status);
                    if (!result.success()) {
                        this.b.put("fileSize", 0);
                        this.b.put("percent", 0);
                        this.b.put("progress", 0);
                        this.b.put("msg", des);
                        this.b.put("statusCode", result.statusCode);
                    } else {
                        this.b.put("fileSize", result.fileSize);
                        this.b.put("percent", 100);
                        this.b.put("progress", 100);
                        this.b.put("savePath", savePath);
                        this.b.put("contentType", result.contentType);
                    }
                } catch (Exception var7) {
                }

                ((ProgressListener) this.a).onProgress(status, this.b);
            }

        }
    }

    private class b {
        final int a;
        final String b;

        public b(int errType, String errDes) {
            this.a = errType;
            this.b = errDes;
        }
    }

    private class c extends RequestCallback {
        final Request a;
        final RequestListener b;

        public c(Request request, RequestListener listener) {
            this.b = listener;
            this.a = request;
        }

        public void onFinish(HttpResult result) {
            m.this.a(this.a, result, this.b);
        }
    }

    private class d extends RequestCallback {
        final Request b;
        final RequestListener c;
        JSONObject a;
        boolean d;

        public d(Request request, RequestListener listener) {
            this.c = listener;
            this.b = request;
            this.d = this.c instanceof ProgressListener;
            if (this.d) {
                this.a = new JSONObject();
            }

        }

        public void onFinish(HttpResult result) {
            if (!this.b.needReport()) {
                m.this.a(this.b, result, this.c);
            } else {
                this.a(result);
            }
        }

        public void onProgress(long total, double progress) {
            if (this.b.needReport() && this.d) {
                try {
                    this.a.put("progress", progress);
                    this.a.put("status", 0);
                    this.a.put("state", 0);
                    this.a.put("body", "");
                } catch (Exception var6) {
                    var6.printStackTrace();
                }

                ((ProgressListener) this.c).onProgress(0, this.a);
            }
        }

        private void a(HttpResult result) {
            if (this.d) {
                String body = result.data;
                if (!result.success()) {
                    m.b err = m.this.a(this.b, result);
                    body = err.b;
                }

                Object b = body;

                try {
                    b = new JSONObject(body);
                } catch (Exception var7) {
                }

                int status = result.success() ? 1 : 2;

                try {
                    this.a.put("progress", 100);
                    this.a.put("status", status);
                    this.a.put("state", status);
                    this.a.put("body", b);
                    this.a.put("msg", b);
                    this.a.put("statusCode", result.statusCode);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }

                ((ProgressListener) this.c).onProgress(status, this.a);
            }
        }
    }
}
