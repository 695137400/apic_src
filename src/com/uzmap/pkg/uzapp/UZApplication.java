//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzapp;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.b;
import java.util.Locale;

public class UZApplication extends Application {
    private b a;
    private Resources b;
    private static UZApplication c;

    public UZApplication() {
    }

    public void onCreate() {
        super.onCreate();
        c = this;
        this.a = com.uzmap.pkg.uzcore.b.a(UZCoreUtil.isMainProcess(this));
        this.a.a(this);
    }

    public Resources getResources() {
        if (this.b != null) {
            return this.b;
        } else {
            Resources resources = super.getResources();
            Configuration defaults = new Configuration();
            defaults.setToDefaults();
            if (defaults.locale == null) {
                defaults.locale = Locale.getDefault();
            }

            resources.updateConfiguration(defaults, resources.getDisplayMetrics());
            this.b = resources;
            return this.b;
        }
    }

    public static final UZApplication instance() {
        return c;
    }
}
