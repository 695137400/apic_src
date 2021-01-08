package com.uzmap.pkg.a.b.cc;

import android.content.Context;

import java.io.File;

public class k {
    private static com.uzmap.pkg.a.b.k a(Context context, f stack, int maxDiskCacheBytes, String rootCacheDir) {
        File cacheDir = new File(rootCacheDir, "ajax");
        if (stack == null) {
            stack = new g(new com.uzmap.pkg.a.b.dd.i());
        }

        com.uzmap.pkg.a.b.g network = new a(stack);
        com.uzmap.pkg.a.b.k queue;
        if (maxDiskCacheBytes <= -1) {
            queue = new com.uzmap.pkg.a.b.k(new c(cacheDir), network);
        } else {
            queue = new com.uzmap.pkg.a.b.k(new c(cacheDir, maxDiskCacheBytes), network);
        }

        queue.a();
        return queue;
    }

    public static com.uzmap.pkg.a.b.k a(Context context, f stack, String rootCacheDir) {
        return a(context, stack, -1, rootCacheDir);
    }

    public static com.uzmap.pkg.a.b.k a(Context context, String rootCacheDir, g.a urlRewriter) {
        f stack = null;
        if (urlRewriter != null) {
            stack = new g(urlRewriter, new com.uzmap.pkg.a.b.dd.i());
        }

        return a(context, stack, rootCacheDir);
    }

    public static d b(Context context, String rootCacheDir, g.a urlRewriter) {
        f stack = null;
        if (urlRewriter != null) {
            stack = new g(urlRewriter, new com.uzmap.pkg.a.b.dd.i());
        }

        com.uzmap.pkg.a.b.k queue = a(context, stack, rootCacheDir);
        d loader = new d(queue, com.uzmap.pkg.a.b.dd.c.c(rootCacheDir));
        return loader;
    }
}
