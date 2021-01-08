package com.uzmap.pkg.uzkit.fineHttp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

final class l {
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final int b;
    private static final int c;
    private static final BlockingQueue<Runnable> d;
    private static final ThreadFactory e;

    static {
        b = a + 1;
        c = a * 10 + 1;
        d = new LinkedBlockingQueue(10);
        e = new ThreadFactory() {
            private final AtomicInteger a = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(r, "UZ-Download-AsyncTask #" + this.a.getAndIncrement());
            }
        };
    }

    private final l.a f;

    public l() {
        this.f = new l.a(b, c, 1L, TimeUnit.SECONDS, d);
    }

    public void a(Runnable request) {
        if (request != null) {
            this.f.execute(request);
        }

    }

    public boolean a(Object tag) {
        return this.f.b(tag);
    }

    public void b(Object tag) {
        this.f.a(tag);
    }

    class a extends ThreadPoolExecutor {
        private final ArrayList<Runnable> b = new ArrayList();

        public a(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, l.e);
        }

        protected void afterExecute(Runnable request, Throwable t) {
            synchronized (this.b) {
                this.b.remove(request);
            }
        }

        public void execute(Runnable request) {
            synchronized (this.b) {
                this.b.add(request);
                super.execute(request);
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

        public boolean b(Object tag) {
            synchronized (this.b) {
                if (tag == null) {
                    return false;
                } else {
                    Iterator var4 = this.b.iterator();

                    while (var4.hasNext()) {
                        Runnable runnable = (Runnable) var4.next();
                        u<?> request = (u) runnable;
                        if (tag.equals(request.a_())) {
                            return true;
                        }
                    }

                    return false;
                }
            }
        }
    }
}
