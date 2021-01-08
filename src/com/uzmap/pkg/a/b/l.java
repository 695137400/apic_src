//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b;

public class l<T> {
    public final T a;
    public final com.uzmap.pkg.a.b.a1.a b;
    public final o c;
    public boolean d = false;

    public static <T> l<T> a(T result, com.uzmap.pkg.a.b.a1.a cacheEntry) {
        return new l(result, cacheEntry);
    }

    public static <T> l<T> a(o error) {
        return new l(error);
    }

    public boolean a() {
        return this.c == null;
    }

    private l(T result, com.uzmap.pkg.a.b.a1.a cacheEntry) {
        this.a = result;
        this.b = cacheEntry;
        this.c = null;
    }

    private l(o error) {
        this.a = null;
        this.b = null;
        this.c = error;
    }

    public interface a {
        void onErrorResponse(o var1);
    }

    public interface b<T> {
        void a(T var1);
    }
}
