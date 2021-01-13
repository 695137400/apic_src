package com.uzmap.pkg.ui.b.cc;

import android.os.SystemClock;
import com.uzmap.pkg.ui.b.n;
import com.uzmap.pkg.ui.b.o;
import com.uzmap.pkg.ui.b.p;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class a implements com.uzmap.pkg.ui.b.g {
    private static final int c = 3000;
    private static final int d = 4096;
    protected final f a;
    protected final b b;

    public a(f httpStack) {
        this(httpStack, new b(d));
    }

    public a(f httpStack, b pool) {
        this.a = httpStack;
        this.b = pool;
    }

    private static void a(String logPrefix, com.uzmap.pkg.ui.b.j<?> request, o exception) throws o {
        n retryPolicy = request.getRetryPolicy();
        int oldTimeout = request.getTimeoutMs();

        try {
            retryPolicy.a(exception);
        } catch (o var6) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", logPrefix, oldTimeout));
            throw var6;
        }

        request.addMarker(String.format("%s-retry [timeout=%s]", logPrefix, oldTimeout));
    }

    public com.uzmap.pkg.ui.b.i a(com.uzmap.pkg.ui.b.j<?> request) throws o {
        long requestStart = SystemClock.elapsedRealtime();

        while (true) {
            com.uzmap.pkg.ui.b.dd.e httpResponse = null;
            byte[] responseContents = null;
            HashMap responseHeaders = new HashMap();

            int statusCode;
            try {
                Map<String, String> headers = new HashMap();
                this.a(headers, request.getCacheEntry());
                httpResponse = this.a.a(request, headers);
                responseHeaders.putAll(httpResponse.a());
                statusCode = httpResponse.c();
                if (statusCode == 304) {
                    com.uzmap.pkg.ui.b.a.a1 entry = request.getCacheEntry();
                    if (entry == null) {
                        return new com.uzmap.pkg.ui.b.i(304, null, responseHeaders, true, SystemClock.elapsedRealtime() - requestStart);
                    }

                    entry.g.putAll(responseHeaders);
                    return new com.uzmap.pkg.ui.b.i(304, entry.a, entry.g, true, SystemClock.elapsedRealtime() - requestStart);
                }

                if (statusCode == 301 || statusCode == 302) {
                    String newUrl = (String) responseHeaders.get("Location");
                    request.setRedirectUrl(newUrl);
                }

                if (httpResponse.b() != null) {
                    com.uzmap.pkg.ui.b.dd.aa.a entity = httpResponse.b();
                    if (!request.handleResponse(entity)) {
                        responseContents = this.a(entity);
                    } else {
                        responseContents = new byte[0];
                    }
                } else {
                    responseContents = new byte[0];
                }

                long requestLifetime = SystemClock.elapsedRealtime() - requestStart;
                this.a(requestLifetime, request, responseContents, httpResponse);
                if (statusCode >= 200 && statusCode <= 299) {
                    return new com.uzmap.pkg.ui.b.i(statusCode, responseContents, responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                }

                throw new IOException();
            } catch (SocketTimeoutException var11) {
                this.a(var11);
                a("socket", request, new com.uzmap.pkg.ui.b.aa.g());
            } catch (MalformedURLException var12) {
                throw new RuntimeException("Bad URL " + request.getUrl(), var12);
            } catch (IOException var13) {
                this.a(var13);
                com.uzmap.pkg.ui.b.i networkResponse = null;
                if (httpResponse == null) {
                    throw new com.uzmap.pkg.ui.b.aa.d(var13);
                }

                statusCode = httpResponse.c();
                if (statusCode != 301 && statusCode != 302) {
                    p.c("Unexpected response code %d for %s", statusCode, request.getUrl());
                } else {
                    p.c("Request at %s has been redirected to %s", request.getOriginUrl(), request.getUrl());
                }

                if (responseContents == null) {
                    throw new com.uzmap.pkg.ui.b.aa.c(networkResponse);
                }

                networkResponse = new com.uzmap.pkg.ui.b.i(statusCode, responseContents, responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                if (statusCode != 401 && statusCode != 403) {
                    if (statusCode != 301 && statusCode != 302) {
                        throw new com.uzmap.pkg.ui.b.aa.f(networkResponse);
                    }

                    a("redirect", request, new com.uzmap.pkg.ui.b.aa.a(networkResponse));
                } else {
                    a("auth", request, new com.uzmap.pkg.ui.b.aa.a(networkResponse));
                }
            }
        }
    }

    private void a(long requestLifetime, com.uzmap.pkg.ui.b.j<?> request, byte[] responseContents, com.uzmap.pkg.ui.b.dd.e httpResponse) {
        if (requestLifetime > (long) c) {
            p.b("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", request, requestLifetime, responseContents != null ? responseContents.length : "null", httpResponse.c(), request.getRetryPolicy().b());
        }

    }

    private void a(Map<String, String> headers, com.uzmap.pkg.ui.b.a.a1 entry) {
        if (entry != null) {
            if (entry.b != null) {
                headers.put("If-None-Match", entry.b);
            }

            if (entry.d > 0L) {
                Date refTime = new Date(entry.d);
                headers.put("If-Modified-Since", com.uzmap.pkg.ui.b.e.a(refTime));
            }

        }
    }

    protected void a(Exception e) {
        e.printStackTrace();
    }

    private byte[] a(com.uzmap.pkg.ui.b.dd.aa.a entity) throws IOException, com.uzmap.pkg.ui.b.aa.f {
        i bytes = new i(this.b, (int) entity.f());
        byte[] buffer = null;

        try {
            InputStream in = entity.a1();
            if (in == null) {
                throw new com.uzmap.pkg.ui.b.aa.f();
            } else {
                if ("gzip".equalsIgnoreCase(entity.d())) {
                    in = new GZIPInputStream(in, 2048);
                }

                buffer = this.b.a(1024);

                int count;
                while ((count = in.read(buffer)) != -1) {
                    bytes.write(buffer, 0, count);
                }

                byte[] var7 = bytes.toByteArray();
                return var7;
            }
        } finally {
            try {
                entity.b();
            } catch (Exception var12) {
                p.a("Error occured when calling consumingContent");
            }

            this.b.a(buffer);
            bytes.close();
        }
    }
}
