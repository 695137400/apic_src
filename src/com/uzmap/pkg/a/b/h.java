package com.uzmap.pkg.a.b;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;

import java.util.concurrent.BlockingQueue;

public class h extends Thread {
    private final BlockingQueue<j<?>> a;
    private final g b;
    private final a c;
    private final m d;
    private volatile boolean e = false;

    public h(BlockingQueue<j<?>> queue, g network, a cache, m delivery) {
        this.a = queue;
        this.b = network;
        this.c = cache;
        this.d = delivery;
    }

    public void a() {
        this.e = true;
        this.interrupt();
    }

    @TargetApi(14)
    private void a(j<?> request) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }

    }

    public void run() {
        Process.setThreadPriority(10);
        this.setName("##Thread-" + Thread.currentThread().getId() + "##");

        while (true) {
            long startTimeMs;
            j request;
            while (true) {
                startTimeMs = SystemClock.elapsedRealtime();

                try {
                    request = this.a.take();
                    break;
                } catch (InterruptedException var6) {
                    if (this.e) {
                        return;
                    }
                }
            }

            try {
                request.addMarker("network-queue-take");
                if (request.isCanceled()) {
                    request.finish("network-discard-cancelled");
                } else {
                    this.a(request);
                    i networkResponse = this.b.a(request);
                    request.addMarker("network-http-complete");
                    if (networkResponse.d && request.hasHadResponseDelivered()) {
                        request.finish("not-modified");
                    } else {
                        l<?> response = request.parseNetworkResponse(networkResponse);
                        request.addMarker("network-parse-complete");
                        if (request.shouldCache() && response.b != null) {
                            this.c.a(request.getCacheKey(), response.b);
                            request.addMarker("network-cache-written");
                        }

                        request.markDelivered();
                        this.d.a(request, response);
                    }
                }
            } catch (o var7) {
                var7.a(SystemClock.elapsedRealtime() - startTimeMs);
                this.a(request, var7);
            } catch (Exception var8) {
                p.a(var8, "Unhandled exception %s", var8.toString());
                o volleyError = new o(var8);
                volleyError.a(SystemClock.elapsedRealtime() - startTimeMs);
                this.d.a(request, volleyError);
            }
        }
    }

    private void a(j<?> request, o error) {
        error = request.parseNetworkError(error);
        this.d.a(request, error);
    }
}
