package com.uzmap.pkg.ui.b;

import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class j<T> implements Comparable<j<T>> {
    private final com.uzmap.pkg.ui.b.p.a2 a2;
    private final int b;
    private final String c;
    private String d;
    private String e;
    private final int f;
    private com.uzmap.pkg.ui.b.l.a1 g;
    private Integer h;
    private k i;
    private boolean j;
    private boolean k;
    private boolean l;
    private long m;
    private n n;
    private com.uzmap.pkg.ui.b.a.a1 o;
    private Object p;
    private boolean q;
    private HttpURLConnection r;
    private static long s;

    /**
     * @deprecated
     */
    @Deprecated
    public j(String url, com.uzmap.pkg.ui.b.l.a1 listener) {
        this(-1, url, listener);
    }

    public j(int method, String url) {
        this(method, url, null);
    }

    public j(int method, String url, com.uzmap.pkg.ui.b.l.a1 listener) {
        this.a2 = null;
        this.j = true;
        this.k = false;
        this.l = false;
        this.m = 0L;
        this.o = null;
        this.q = false;
        this.b = method;
        this.c = url;
        this.e = createIdentifier(method, url);
        this.g = listener;
        this.setRetryPolicy(new d());
        this.f = findDefaultTrafficStatsTag(url);
    }

    public int getMethod() {
        return this.b;
    }

    public j<?> setTag(Object tag) {
        this.p = tag;
        return this;
    }

    public Object getTag() {
        return this.p;
    }

    public com.uzmap.pkg.ui.b.l.a1 getErrorListener() {
        return this.g;
    }

    public void setErrorListener(com.uzmap.pkg.ui.b.l.a1 listener) {
        this.g = listener;
    }

    public int getTrafficStatsTag() {
        return this.f;
    }

    private static int findDefaultTrafficStatsTag(String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            if (uri != null) {
                String host = uri.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }

        return 0;
    }

    public j<?> setRetryPolicy(n retryPolicy) {
        this.n = retryPolicy;
        return this;
    }

    public void addMarker(String tag) {
        if (this.m == 0L) {
            this.m = SystemClock.elapsedRealtime();
        }

    }

    void finish(String tag) {
        if (this.i != null) {
            this.i.b(this);
        }

        long requestTime = SystemClock.elapsedRealtime() - this.m;
        if (requestTime >= 3000L) {
            com.uzmap.pkg.ui.b.p.b("%d ms: %s", requestTime, this.toString());
        }

    }

    public j<?> setRequestQueue(k requestQueue) {
        this.i = requestQueue;
        return this;
    }

    public final j<?> setSequence(int sequence) {
        this.h = sequence;
        return this;
    }

    public final int getSequence() {
        if (this.h == null) {
            throw new IllegalStateException("getSequence called before setSequence");
        } else {
            return this.h;
        }
    }

    public String getUrl() {
        return this.d != null ? this.d : this.c;
    }

    public String getOriginUrl() {
        return this.c;
    }

    public String getIdentifier() {
        return this.e;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.d = redirectUrl;
    }

    public String getCacheKey() {
        return this.getOriginUrl();
    }

    public com.uzmap.pkg.ui.b.a.a1 getCacheEntry() {
        return this.o;
    }

    public j<?> setCacheEntry(com.uzmap.pkg.ui.b.a.a1 entry) {
        this.o = entry;
        return this;
    }

    public void cancel() {
        this.k = true;
        if (this.r != null) {
            try {
                this.r.disconnect();
            } catch (Exception var2) {
            }

            this.r = null;
        }

    }

    public boolean isCanceled() {
        return this.k;
    }

    public Map<String, String> getHeaders() throws com.uzmap.pkg.ui.b.aa.a {
        return Collections.emptyMap();
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected Map<String, String> getPostParams() throws com.uzmap.pkg.ui.b.aa.a {
        return this.getParams();
    }

    /**
     * @deprecated
     */
    @Deprecated
    protected String getPostParamsEncoding() {
        return this.getParamsEncoding();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public String getPostBodyContentType() {
        return this.getBodyContentType();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public byte[] getPostBody() throws com.uzmap.pkg.ui.b.aa.a {
        Map<String, String> postParams = this.getPostParams();
        return postParams != null && postParams.size() > 0 ? this.encodeParameters(postParams, this.getPostParamsEncoding()) : null;
    }

    protected Map<String, String> getParams() throws com.uzmap.pkg.ui.b.aa.a {
        return null;
    }

    protected String getParamsEncoding() {
        return "UTF-8";
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + this.getParamsEncoding();
    }

    public byte[] getBody() throws com.uzmap.pkg.ui.b.aa.a {
        Map<String, String> params = this.getParams();
        return params != null && params.size() > 0 ? this.encodeParameters(params, this.getParamsEncoding()) : null;
    }

    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();

        try {
            Iterator var5 = params.entrySet().iterator();

            while (var5.hasNext()) {
                Entry<String, String> entry = (Entry) var5.next();
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }

            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, var6);
        }
    }

    public final j<?> setShouldCache(boolean shouldCache) {
        this.j = shouldCache;
        return this;
    }

    public final boolean shouldCache() {
        return this.j;
    }

    public aaemnu getPriority() {
        return aaemnu.b;
    }

    public final int getTimeoutMs() {
        return this.n.a();
    }

    public n getRetryPolicy() {
        return this.n;
    }

    public void markDelivered() {
        this.l = true;
    }

    public boolean hasHadResponseDelivered() {
        return this.l;
    }

    protected abstract l<T> parseNetworkResponse(i var1);

    protected o parseNetworkError(o volleyError) {
        return volleyError;
    }

    protected abstract void deliverResponse(T var1);

    public void deliverError(o error) {
        if (this.g != null) {
            this.g.onErrorResponse(error);
        }

    }

    public int compareTo(j<T> other) {
        aaemnu left = this.getPriority();
        aaemnu right = other.getPriority();
        return left == right ? this.h - other.h : right.ordinal() - left.ordinal();
    }

    public String toString() {
        String trafficStatsTag = "0x" + Integer.toHexString(this.getTrafficStatsTag());
        return (this.k ? "[X] " : "[ ] ") + this.getUrl() + " " + trafficStatsTag + " " + this.getPriority() + " " + this.h + " " + this.p;
    }

    private static String createIdentifier(int method, String url) {
        return com.uzmap.pkg.ui.b.e.a("Request:" + method + ":" + url + ":" + System.currentTimeMillis() + ":" + s++);
    }

    public boolean isEmpty() throws com.uzmap.pkg.ui.b.aa.a {
        return this.getParams() == null;
    }

    public void writeTo(OutputStream outstream) throws IOException, com.uzmap.pkg.ui.b.aa.a {
    }

    public boolean handleResponse(com.uzmap.pkg.ui.b.dd.aa.a response) throws IOException, com.uzmap.pkg.ui.b.aa.f {
        return false;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return null;
    }

    protected void deliverProgress(long total, long finish) {
    }

    public void onPreExecute() {
    }

    public final j<?> setDeliverInThread(boolean flag) {
        this.q = flag;
        return this;
    }

    public final boolean isDeliverInThread() {
        return this.q;
    }

    public final void setConnection(HttpURLConnection connection) {
        this.r = connection;
    }

    public enum aaemnu {
        a,
        b,
        c,
        d;
    }
}
