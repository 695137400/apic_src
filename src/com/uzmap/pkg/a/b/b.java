//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class b extends Thread {
    private final BlockingQueue<j<?>> a;
    private final BlockingQueue<j<?>> b;
    private final a c;
    private final m d;
    private volatile boolean e = false;

    public b(BlockingQueue<j<?>> cacheQueue, BlockingQueue<j<?>> networkQueue, a cache, m delivery) {
        this.a = cacheQueue;
        this.b = networkQueue;
        this.c = cache;
        this.d = delivery;
    }

    public void a() {
        this.e = true;
        this.interrupt();
    }

    public void run() {
        p.a("start new dispatcher", new Object[0]);
        Process.setThreadPriority(10);
        this.setName("##Thread-" + Thread.currentThread().getId() + "###");
        this.c.a();

        while(true) {
            while(true) {
                while(true) {
                    while(true) {
                        try {
                            while(true) {
                                final j<?> request = (j)this.a.take();
                                request.addMarker("cache-queue-take");
                                if (request.isCanceled()) {
                                    request.finish("cache-discard-canceled");
                                } else {
                                    com.uzmap.pkg.a.b.a.aa entry = this.c.a(request.getCacheKey());
                                    if (entry == null) {
                                        request.addMarker("cache-miss");
                                        this.b.put(request);
                                    } else if (entry.a()) {
                                        request.addMarker("cache-hit-expired");
                                        request.setCacheEntry(entry);
                                        this.b.put(request);
                                    } else {
                                        request.addMarker("cache-hit");
                                        l<?> response = request.parseNetworkResponse(new i(entry.a, entry.g));
                                        request.addMarker("cache-hit-parsed");
                                        if (entry.b()) {
                                            request.addMarker("cache-hit-refresh-needed");
                                            request.setCacheEntry(entry);
                                            response.d = true;
                                            this.d.a(request, response, new Runnable() {
                                                public void run() {
                                                    try {
                                                        b.this.b.put(request);
                                                    } catch (InterruptedException var2) {
                                                    }

                                                }
                                            });
                                        } else {
                                            this.d.a(request, response);
                                        }
                                    }
                                }
                            }
                        } catch (InterruptedException var4) {
                            if (this.e) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
