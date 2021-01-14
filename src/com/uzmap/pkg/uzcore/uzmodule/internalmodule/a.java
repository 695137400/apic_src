package com.uzmap.pkg.uzcore.uzmodule.internalmodule;

import android.content.Context;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.ApplicationProcess;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.UzResourceCache;

import java.io.File;

public class a implements Runnable {
    private String a;
    private String b;
    private long c = -1L;

    public a(String id) {
        this.a = id;
    }

    public void a(String type, float threshold) {
        this.b = type;
        if (threshold > 0.0F) {
            long now = System.currentTimeMillis();
            long daymillis = 86400000L;
            long millis = (long) (threshold * (float) daymillis);
            this.c = now - millis;
        }

    }

    public void run() {
        Context context = ApplicationProcess.initialize().b();
        File boxcache = context.getCacheDir();
        if (boxcache != null) {
            if (this.c > 0L) {
                UZCoreUtil.delete(boxcache, this.c);
            } else {
                UZCoreUtil.delete(boxcache);
            }
        }

        File extcache = context.getExternalCacheDir();
        if (extcache != null) {
            if (this.c > 0L) {
                UZCoreUtil.delete(extcache, this.c);
            } else {
                UZCoreUtil.delete(extcache);
            }
        }

        UzResourceCache.get().clearDisk(this.c);
        if (this.a != null) {
            UZFileSystem.get().clearAllCache(this.a);
        }

    }

    public void a() {
        (new Thread(this)).start();
    }
}
