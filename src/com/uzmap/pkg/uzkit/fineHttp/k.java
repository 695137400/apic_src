package com.uzmap.pkg.uzkit.fineHttp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

final class k {
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final int b;
    private static final int c;
    private static final BlockingQueue<Runnable> d;
    private static final ThreadFactory e;

    static {
        b = a + 1;
        c = a * 10;
        d = new LinkedBlockingQueue(10);
        e = new ThreadFactory() {
            private final AtomicInteger a = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(r, "AJAX-Task #" + this.a.getAndIncrement());
            }
        };
    }

    private final k.a f;

    public k() {
        this.f = new k.a(b, c, 1L, TimeUnit.SECONDS);
    }

    public void a(Runnable request) {
        if (request != null) {
            this.f.execute(request);
        }

    }

    public void a(Object tag) {
        this.f.a(tag);
    }

    class a extends ThreadPoolExecutor {
        private final ArrayList<Runnable> b = new ArrayList();

        public a(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, k.d, k.e);
        }

        protected void afterExecute(Runnable request, Throwable t) {
            synchronized (this.b) {
                this.b.remove(request);
            }
        }

        public void execute(Runnable request) {
            synchronized (this.b) {
                this.b.add(request);

                try {
                    super.execute(request);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }

            }
        }

        public void a(Object tag) {
            synchronized (this.b) {
                if (tag != null) {
                    ArrayList<Runnable> remove = new ArrayList();
                    Iterator var5 = this.b.iterator();

                    while (var5.hasNext()) {
                        Runnable runnable = (Runnable) var5.next();
                        u<?> request = (u) runnable;
                        if (tag.equals(request.a_())) {
                            request.c();
                            remove.add(request);
                        }
                    }

                    this.b.removeAll(remove);
                    remove.clear();
                }
            }
        }
    }
}
