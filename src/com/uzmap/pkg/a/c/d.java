//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.c;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;
import com.uzmap.pkg.uzcore.a.b;
import com.uzmap.pkg.uzcore.external.l;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class d {
    static final Pattern a = Pattern.compile("(?i)((?:http|https|file):\\/\\/|(?:inline|data|about|javascript):|(?:.*:.*@))(.*)");
    Activity b;

    public d() {
    }

    public void a(Activity activity) {
        this.b = activity;
    }

    public boolean a(WebView view, String url) {
        if (!URLUtil.isValidUrl(url) && !url.startsWith(com.uzmap.pkg.uzcore.aa.b.a())) {
            if (url.startsWith("wtai://wp/")) {
                if (url.startsWith("wtai://wp/mc;")) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + url.substring("wtai://wp/mc;".length())));
                    this.b.startActivity(intent);
                    return true;
                }

                if (url.startsWith("wtai://wp/sd;")) {
                    return false;
                }

                if (url.startsWith("wtai://wp/ap;")) {
                    return false;
                }
            }

            if (url.startsWith("about:")) {
                return false;
            } else {
                return this.a(url);
            }
        } else {
            return false;
        }
    }

    boolean a(String url) {
        Intent intent;
        try {
            intent = Intent.parseUri(url, 1);
        } catch (URISyntaxException var5) {
            var5.printStackTrace();
            return false;
        }

        if (this.b.getPackageManager().resolveActivity(intent, 0) == null) {
            String packagename = intent.getPackage();
            if (packagename != null) {
                intent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:" + packagename));
                intent.addCategory("android.intent.category.BROWSABLE");
                this.b.startActivity(intent);
                return true;
            } else {
                return false;
            }
        } else {
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setComponent((ComponentName)null);
            if (l.a >= 15) {
                Intent selector = intent.getSelector();
                if (selector != null) {
                    selector.addCategory("android.intent.category.BROWSABLE");
                    selector.setComponent((ComponentName)null);
                }
            }

            intent.putExtra("com.android.browser.application_id", this.b.getPackageName());

            try {
                intent.putExtra("disable_url_override", true);
                if (this.b.startActivityIfNeeded(intent, -1)) {
                    return true;
                }
            } catch (ActivityNotFoundException var4) {
            }

            return false;
        }
    }
}
