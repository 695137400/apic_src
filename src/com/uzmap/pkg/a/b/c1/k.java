//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import android.content.Context;
import com.uzmap.pkg.a.b.d1.i;
import java.io.File;

public class k {
    private static com.uzmap.pkg.a.b.k a(Context context, f stack, int maxDiskCacheBytes, String rootCacheDir) {
        File cacheDir = new File(rootCacheDir, "ajax");
        if (stack == null) {
            stack = new g(new i());
        }

        com.uzmap.pkg.a.b.g network = new a((f)stack);
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

    public static com.uzmap.pkg.a.b.k a(Context context, String rootCacheDir, com.uzmap.pkg.a.b.c1.g.a urlRewriter) {
        f stack = null;
        if (urlRewriter != null) {
            stack = new g(urlRewriter, new i());
        }

        return a(context, (f)stack, (String)rootCacheDir);
    }

    public static d b(Context context, String rootCacheDir, com.uzmap.pkg.a.b.c1.g.a urlRewriter) {
        f stack = null;
        if (urlRewriter != null) {
            stack = new g(urlRewriter, new i());
        }

        com.uzmap.pkg.a.b.k queue = a(context, (f)stack, (String)rootCacheDir);
        d loader = new d(queue, com.uzmap.pkg.a.b.d1.c.c(rootCacheDir));
        return loader;
    }
}
