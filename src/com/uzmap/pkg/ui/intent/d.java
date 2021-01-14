package com.uzmap.pkg.ui.intent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import com.uzmap.pkg.uzcore.aa.AssetsUtil;
import com.uzmap.pkg.uzcore.external.l;

import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class d {
   static final Pattern a = Pattern.compile("(?i)((?:http|https|file):\\/\\/|(?:inline|data|about|javascript):|(?:.*:.*@))(.*)");
   Activity b;

   public void init(Activity activity) {
      this.b = activity;
   }

   public boolean loadUrl(String url) {
      if (!URLUtil.isValidUrl(url) && !url.startsWith(AssetsUtil.a())) {
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
            return this.load(url);
         }
      } else {
         return false;
      }
   }

  private boolean load(String url) {
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
         intent.setComponent(null);
         if (l.SDK_INT >= 15) {
            Intent selector = intent.getSelector();
            if (selector != null) {
               selector.addCategory("android.intent.category.BROWSABLE");
               selector.setComponent(null);
            }
         }

         intent.putExtra("com.android.browser.application_id", this.b.getPackageName());

         try {
            intent.putExtra("disable_url_override", true);
            if (this.b.startActivityIfNeeded(intent, -1)) {
               return true;
            }
         } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
         }

         return false;
      }
   }
}
