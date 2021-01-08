//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import android.os.SystemClock;
import com.uzmap.pkg.a.b.g;
import com.uzmap.pkg.a.b.i;
import com.uzmap.pkg.a.b.j;
import com.uzmap.pkg.a.b.n;
import com.uzmap.pkg.a.b.o;
import com.uzmap.pkg.a.b.p;
import com.uzmap.pkg.a.b.a1.c;
import com.uzmap.pkg.a.b.a1.d;
import com.uzmap.pkg.a.b.d.e;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class a implements g {
    private static int c = 3000;
    private static int d = 4096;
    protected final f a;
    protected final b b;

    public a(f httpStack) {
        this(httpStack, new b(d));
    }

    public a(f httpStack, b pool) {
        this.a = httpStack;
        this.b = pool;
    }

    public i a(j<?> request) throws o {
        long requestStart = SystemClock.elapsedRealtime();

        while(true) {
            e httpResponse = null;
            HashMap responseHeaders = new HashMap();

            int statusCode;
            byte[] responseContents = new byte[0];
            try {
                Map<String, String> headers = new HashMap();
                this.a(headers, request.getCacheEntry());
                httpResponse = this.a.a(request, headers);
                responseHeaders.putAll(httpResponse.a());
                statusCode = httpResponse.c();
                if (statusCode == 304) {
                    com.uzmap.pkg.a.b.a.a entry = request.getCacheEntry();
                    if (entry == null) {
                        return new i(304, (byte[])null, responseHeaders, true, SystemClock.elapsedRealtime() - requestStart);
                    }

                    entry.g.putAll(responseHeaders);
                    return new i(304, entry.a, entry.g, true, SystemClock.elapsedRealtime() - requestStart);
                }

                if (statusCode == 301 || statusCode == 302) {
                    String newUrl = (String)responseHeaders.get("Location");
                    request.setRedirectUrl(newUrl);
                }


                if (httpResponse.b() != null) {
                    com.uzmap.pkg.a.b.d.a.a entity = httpResponse.b();
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
                    return new i(statusCode, responseContents, responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                }

                throw new IOException();
            } catch (SocketTimeoutException var11) {
                this.a((Exception)var11);
                a("socket", request, new com.uzmap.pkg.a.b.a1.g());
            } catch (MalformedURLException var12) {
                throw new RuntimeException("Bad URL " + request.getUrl(), var12);
            } catch (IOException var13) {
                this.a((Exception)var13);
                i networkResponse = null;
                if (httpResponse == null) {
                    throw new d(var13);
                }

                statusCode = httpResponse.c();
                if (statusCode != 301 && statusCode != 302) {
                    p.c("Unexpected response code %d for %s", new Object[]{statusCode, request.getUrl()});
                } else {
                    p.c("Request at %s has been redirected to %s", new Object[]{request.getOriginUrl(), request.getUrl()});
                }

                if (responseContents == null) {
                    throw new c(networkResponse);
                }

                networkResponse = new i(statusCode, (byte[])responseContents, responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                if (statusCode != 401 && statusCode != 403) {
                    if (statusCode != 301 && statusCode != 302) {
                        throw new com.uzmap.pkg.a.b.a1.f(networkResponse);
                    }

                    a("redirect", request, new com.uzmap.pkg.a.b.a1.a(networkResponse));
                } else {
                    a("auth", request, new com.uzmap.pkg.a.b.a1.a(networkResponse));
                }
            }
        }
    }

    private void a(long requestLifetime, j<?> request, byte[] responseContents, e httpResponse) {
        if (requestLifetime > (long)c) {
            p.b("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[]{request, requestLifetime, responseContents != null ? responseContents.length : "null", httpResponse.c(), request.getRetryPolicy().b()});
        }

    }

    private static void a(String logPrefix, j<?> request, o exception) throws o {
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

    private void a(Map<String, String> headers, com.uzmap.pkg.a.b.a.a entry) {
        if (entry != null) {
            if (entry.b != null) {
                headers.put("If-None-Match", entry.b);
            }

            if (entry.d > 0L) {
                Date refTime = new Date(entry.d);
                headers.put("If-Modified-Since", com.uzmap.pkg.a.b.e.a(refTime));
            }

        }
    }

    protected void a(Exception e) {
        e.printStackTrace();
    }

    private byte[] a(com.uzmap.pkg.a.b.d.a.a entity) throws IOException, com.uzmap.pkg.a.b.a1.f {
        com.uzmap.pkg.a.b.c1.i bytes = new com.uzmap.pkg.a.b.c1.i(this.b, (int)entity.f());
        byte[] buffer = null;

        try {
            InputStream in = entity.a();
            if (in == null) {
                throw new com.uzmap.pkg.a.b.a1.f();
            } else {
                if ("gzip".equalsIgnoreCase(entity.d())) {
                    in = new GZIPInputStream((InputStream)in, 2048);
                }

                buffer = this.b.a(1024);

                int count;
                while((count = ((InputStream)in).read(buffer)) != -1) {
                    bytes.write(buffer, 0, count);
                }

                byte[] var7 = bytes.toByteArray();
                return var7;
            }
        } finally {
            try {
                entity.b();
            } catch (Exception var12) {
                p.a("Error occured when calling consumingContent", new Object[0]);
            }

            this.b.a(buffer);
            bytes.close();
        }
    }
}
