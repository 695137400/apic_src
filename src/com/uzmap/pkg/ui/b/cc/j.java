package com.uzmap.pkg.ui.b.cc;

import com.uzmap.pkg.ui.b.l;
import com.uzmap.pkg.ui.b.o;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class j<T> implements l.a1, l.b<T>, Future<T> {
    private com.uzmap.pkg.ui.b.j<?> a;
    private boolean b = false;
    private T c;
    private o d;

    private j() {
    }

    public static <E> j<E> a() {
        return new j();
    }

    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        if (this.a == null) {
            return false;
        } else if (!this.isDone()) {
            this.a.cancel();
            return true;
        } else {
            return false;
        }
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return this.a((Long) null);
        } catch (TimeoutException var2) {
            throw new AssertionError(var2);
        }
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.a(TimeUnit.MILLISECONDS.convert(timeout, unit));
    }

    private synchronized T a(Long timeoutMs) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.d != null) {
            throw new ExecutionException(this.d);
        } else if (this.b) {
            return this.c;
        } else {
            if (timeoutMs == null) {
                this.wait(0L);
            } else if (timeoutMs > 0L) {
                this.wait(timeoutMs);
            }

            if (this.d != null) {
                throw new ExecutionException(this.d);
            } else if (!this.b) {
                throw new TimeoutException();
            } else {
                return this.c;
            }
        }
    }

    public boolean isCancelled() {
        return this.a != null && this.a.isCanceled();
    }

    public synchronized boolean isDone() {
        return this.b || this.d != null || this.isCancelled();
    }

    public synchronized void a(T response) {
        this.b = true;
        this.c = response;
        this.notifyAll();
    }

    public synchronized void onErrorResponse(o error) {
        this.d = error;
        this.notifyAll();
    }
}
