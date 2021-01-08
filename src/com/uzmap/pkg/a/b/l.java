package com.uzmap.pkg.a.b;

public class l<T> {
    public final T a;
    public final com.uzmap.pkg.a.b.a.a1 b;
    public final o c;
    public boolean d = false;

    private l(T result, com.uzmap.pkg.a.b.a.a1 cacheEntry) {
        this.a = result;
        this.b = cacheEntry;
        this.c = null;
    }

    public static <T> l<T> a(o error) {
        return new l(error);
    }

    public boolean a() {
        return this.c == null;
    }

    public static <T> l<T> a(T result, com.uzmap.pkg.a.b.a.a1 cacheEntry) {
        return new l(result, cacheEntry);
    }

    private l(o error) {
        this.a = null;
        this.b = null;
        this.c = error;
    }

    public interface a1 {
        void onErrorResponse(o var1);
    }

    public interface b<T> {
        void a(T var1);
    }
}
