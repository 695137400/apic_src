package com.uzmap.pkg.ui.b.dd;

import android.text.TextUtils;
import com.uzmap.pkg.ui.b.j;
import com.uzmap.pkg.ui.b.l;
import com.uzmap.pkg.ui.b.o;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.request.HttpResult;
import com.uzmap.pkg.uzkit.request.RequestCallback;

import javax.net.ssl.SSLSocketFactory;
import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Map;

public abstract class d extends j<com.uzmap.pkg.ui.b.i> implements com.uzmap.pkg.ui.b.l.a1 {
    protected RequestCallback a;
    protected l.b<com.uzmap.pkg.ui.b.i> b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private boolean g;
    private boolean h;
    private Hashtable<String, String> i;
    private final g j;

    public d(int method, String url) {
        super(method, url);
        this.setErrorListener(this);
        this.initDefault();
        this.j = new g();
        this.setRetryPolicy(this.j);
    }

    protected l<com.uzmap.pkg.ui.b.i> parseNetworkResponse(com.uzmap.pkg.ui.b.i response) {
        com.uzmap.pkg.ui.b.p.a(" >>>>>> parseNetworkResponse", (Object) null);
        return com.uzmap.pkg.ui.b.l.a(response, com.uzmap.pkg.ui.b.cc.e.a(response));
    }

    protected void deliverResponse(com.uzmap.pkg.ui.b.i response) {
        com.uzmap.pkg.ui.b.p.a("deliverResponse", response);
        if (this.a != null) {
            HttpResult result = new HttpResult(response.a);
            result.headers = response.c;
            result.data = response.a();
            this.a.onFinish(result);
        }

        if (this.b != null) {
            this.b.a(response);
        }

    }

    protected void deliverProgress(long contentlength, long finishlength) {
        if (this.needReport() && this.a != null) {
            double percent = 0.0D;
            if (contentlength > 0L) {
                float pro = (float) finishlength / (float) contentlength * 100.0F;
                if (pro > 99.99F && pro < 100.0F) {
                    return;
                }

                BigDecimal b = new BigDecimal(pro);
                percent = b.setScale(2, 4).doubleValue();
            }

            com.uzmap.pkg.ui.b.p.b("percent: " + finishlength + "," + contentlength + "," + percent);
            this.a.onProgress(contentlength, percent);
        }

    }

    public void onErrorResponse(o error) {
        com.uzmap.pkg.ui.b.p.a("onErrorResponse", error);
        if (this.shouldCache()) {
            com.uzmap.pkg.ui.b.a.a1 entry = this.getCacheEntry();
            if (entry != null) {
                com.uzmap.pkg.ui.b.p.c("Request Error, Return will from Cache <<<< ");
                com.uzmap.pkg.ui.b.i response = new com.uzmap.pkg.ui.b.i(304, entry.a, entry.g, true, error.a());
                this.deliverResponse(response);
                return;
            }
        }

        if (this.a != null) {
            int errorType = error.b();
            String des = error.c();
            int statusCode = 0;
            Map<String, String> headers = null;
            if (error.a != null) {
                statusCode = error.a.a;
                des = error.a.a();
                headers = error.a.c;
            }

            HttpResult result = new HttpResult(statusCode);
            result.data = des;
            result.headers = headers != null ? headers : new Hashtable();
            result.setErrorType(errorType);
            this.a.onFinish(result);
        }

        if (this.b != null) {
            this.b.a(error.a);
        }

    }

    public Map<String, String> getHeaders() throws com.uzmap.pkg.ui.b.aa.a {
        return this.i;
    }

    public void cancel() {
        super.cancel();
    }

    public final SSLSocketFactory getSslSocketFactory() {
        return !TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(this.e) ? com.uzmap.pkg.ui.b.bb.b.a(this.d, this.e) : com.uzmap.pkg.ui.b.bb.b.a();
    }

    public void addHeader(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            this.i.put(key, value);
        }

    }

    public void addHeader(Map<String, String> headers) {
        if (headers != null) {
            this.i.putAll(headers);
        }

    }

    public void setTimeout(int time) {
        this.j.a(time);
    }

    public void setCertificate(String certPath, String certPsw) {
        this.d = certPath;
        this.e = certPsw;
    }

    public String getCharset() {
        return this.c;
    }

    public void setCharset(String charset) {
        this.c = charset;
    }

    public void setNeedEscape(boolean flag) {
        this.f = flag;
    }

    public boolean needEscape() {
        return this.f;
    }

    public void setNeedErrorInfo(boolean flag) {
        this.g = flag;
    }

    public boolean needErrorInfo() {
        return this.g;
    }

    public void setNeedReport(boolean flag) {
        this.h = flag;
    }

    public boolean needReport() {
        return this.h;
    }

    public void setSyncListener(l.b<com.uzmap.pkg.ui.b.i> listener) {
        this.b = listener;
    }

    public void setCallback(RequestCallback callback) {
        this.a = callback;
    }

    private void initDefault() {
        this.setDeliverInThread(true);
        this.i = new Hashtable();
        this.i.put("Accept", "*/*");
        this.i.put("Charset", "UTF-8");
        this.i.put("User-Agent", UZCoreUtil.getDefaultUserAgent());
        this.i.put("Accept-Encoding", "gzip, deflate");
        this.i.put("Connection", "Keep-Alive");
    }
}
