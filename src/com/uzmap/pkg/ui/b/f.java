package com.uzmap.pkg.ui.b;

import android.os.Handler;

import java.util.concurrent.Executor;

public class f implements m {
    private final Executor a;

    public f(final Handler handler) {
        this.a = new Executor() {
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    public void a(j<?> request, l<?> response) {
        this.a(request, response, null);
    }

    public void a(j<?> request, l<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        f.a delivery = new f.a(request, response, runnable);
        if (request.isDeliverInThread()) {
            delivery.run();
        } else {
            this.a.execute(delivery);
        }

    }

    public void a(j<?> request, o error) {
        request.addMarker("post-error");
        l<?> response = l.a(error);
        f.a delivery = new f.a(request, response, null);
        if (request.isDeliverInThread()) {
            delivery.run();
        } else {
            this.a.execute(delivery);
        }

    }

    private class a implements Runnable {
        private final j b;
        private final l c;
        private final Runnable d;

        public a(j request, l response, Runnable runnable) {
            this.b = request;
            this.c = response;
            this.d = runnable;
        }

        public void run() {
            if (this.b.isCanceled()) {
                this.b.finish("canceled-at-delivery");
            } else {
                if (this.c.a()) {
                    this.b.deliverResponse(this.c.a);
                } else {
                    this.b.deliverError(this.c.c);
                }

                if (this.c.d) {
                    this.b.addMarker("intermediate-response");
                } else {
                    this.b.finish("done");
                }

                if (this.d != null) {
                    this.d.run();
                }

            }
        }
    }
}
