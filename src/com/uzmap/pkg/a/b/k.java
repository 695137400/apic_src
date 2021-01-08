package com.uzmap.pkg.a.b;

import android.os.Handler;
import android.os.Looper;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class k {
    private AtomicInteger a;
    private final Map<String, Queue<j<?>>> b;
    private final Set<j<?>> c;
    private final PriorityBlockingQueue<j<?>> d;
    private final PriorityBlockingQueue<j<?>> e;
    private static final int f = Runtime.getRuntime().availableProcessors();
    private static final int g;
    private static final int h;
    private final com.uzmap.pkg.a.b.a i;
    private final g j;
    private final m k;
    private h[] l;
    private com.uzmap.pkg.a.b.b m;
    private List<k.b> n;

    static {
        g = f;
        h = g;
    }

    public k(com.uzmap.pkg.a.b.a cache, g network, int threadPoolSize, m delivery) {
        this.a = new AtomicInteger();
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new PriorityBlockingQueue();
        this.e = new PriorityBlockingQueue();
        this.n = new ArrayList();
        this.i = cache;
        this.j = network;
        this.l = new h[threadPoolSize];
        this.k = delivery;
    }

    public k(com.uzmap.pkg.a.b.a cache, g network, int threadPoolSize) {
        this(cache, network, threadPoolSize, new f(new Handler(Looper.getMainLooper())));
    }

    public k(com.uzmap.pkg.a.b.a cache, g network) {
        this(cache, network, h);
    }

    public void a() {
        this.b();
        this.m = new com.uzmap.pkg.a.b.b(this.d, this.e, this.i, this.k);
        this.m.start();

        for (int i = 0; i < this.l.length; ++i) {
            h networkDispatcher = new h(this.e, this.j, this.i, this.k);
            this.l[i] = networkDispatcher;
            networkDispatcher.start();
        }

    }

    public void b() {
        if (this.m != null) {
            this.m.a();
        }

        for (int i = 0; i < this.l.length; ++i) {
            if (this.l[i] != null) {
                this.l[i].a();
            }
        }

    }

    public int c() {
        return this.a.incrementAndGet();
    }

    public void a(k.a filter) {
        synchronized (this.c) {
            Iterator var4 = this.c.iterator();

            while (var4.hasNext()) {
                j<?> request = (j) var4.next();
                if (filter.a(request)) {
                    request.cancel();
                }
            }

        }
    }

    public void a(final Object tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        } else {
            this.a(new k.a() {
                public boolean a(j<?> request) {
                    return tag.equals(request.getTag());
                }
            });
        }
    }

    public <T> j<T> a(j<T> request) {
        request.setRequestQueue(this);
        synchronized (this.c) {
            this.c.add(request);
        }

        request.setSequence(this.c());
        request.addMarker("add-to-queue");
        if (!request.shouldCache()) {
            this.e.add(request);
            return request;
        } else {
            synchronized (this.b) {
                String cacheKey = request.getCacheKey();
                if (this.b.containsKey(cacheKey)) {
                    Queue<j<?>> stagedRequests = this.b.get(cacheKey);
                    if (stagedRequests == null) {
                        stagedRequests = new LinkedList();
                    }

                    stagedRequests.add(request);
                    this.b.put(cacheKey, stagedRequests);
                } else {
                    this.b.put(cacheKey, null);
                    this.d.add(request);
                }

                return request;
            }
        }
    }

    <T> void b(j<T> request) {
        synchronized (this.c) {
            this.c.remove(request);
        }

        synchronized (this.n) {
            Iterator var4 = this.n.iterator();

            while (true) {
                if (!var4.hasNext()) {
                    break;
                }

                k.b<T> listener = (k.b) var4.next();
                listener.a(request);
            }
        }

        if (request.shouldCache()) {
            synchronized (this.b) {
                String cacheKey = request.getCacheKey();
                Queue<j<?>> waitingRequests = this.b.remove(cacheKey);
                if (waitingRequests != null) {
                    this.d.addAll(waitingRequests);
                }
            }
        }

    }

    public interface a {
        boolean a(j<?> var1);
    }

    public interface b<T> {
        void a(j<T> var1);
    }
}
