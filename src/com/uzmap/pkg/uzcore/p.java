package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebStorage.QuotaUpdater;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import com.uzmap.pkg.ui.intent.b;
import com.uzmap.pkg.uzcore.uzmodule.UZActivityResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class p extends WebChromeClient implements UZActivityResult {
   // $FF: synthetic field
   private static int[] h;
   protected Activity a;
   protected com.uzmap.pkg.uzcore.external.c b;
   protected CustomViewCallback c;
   protected com.uzmap.pkg.ui.intent.b d;
   protected f e;
   private com.uzmap.pkg.uzcore.external.a f;
   private p.a g;

   p(Activity context, Object o) {
      this.a = context;
      this.e = com.uzmap.pkg.uzcore.f.b(this.a);
   }

   static p a(Activity context) {
      return com.uzmap.pkg.uzcore.external.l.a < 21 ? new p(context, null) : new q(context, null);
   }

   // $FF: synthetic method
   static int[] a() {
      int[] var10000 = h;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[MessageLevel.values().length];

         try {
            var0[MessageLevel.DEBUG.ordinal()] = 1;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[MessageLevel.ERROR.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[MessageLevel.LOG.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[MessageLevel.TIP.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[MessageLevel.WARNING.ordinal()] = 5;
         } catch (NoSuchFieldError var1) {
         }

         h = var0;
         return var0;
      }
   }

   public void a(p.a listener) {
      this.g = listener;
   }

   public void onProgressChanged(WebView view, int newProgress) {
      com.uzmap.pkg.uzcore.a target = (com.uzmap.pkg.uzcore.a) view;
      target.a(target, newProgress);
      this.e.a(target, newProgress);
   }

   public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
      Builder dia = new Builder(this.a);
      dia.setTitle("提示消息");
      dia.setMessage(message);
      dia.setCancelable(false);
      dia.setPositiveButton("确定", new OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
            result.confirm();
         }
      });
      dia.show();
      return true;
   }

   public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
      Builder dia = new Builder(this.a);
      dia.setMessage(message);
      dia.setTitle("确认消息");
      dia.setCancelable(false);
      dia.setPositiveButton("确定", new OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
            result.confirm();
         }
      });
      dia.setNegativeButton("取消", new OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
            result.cancel();
         }
      });
      dia.show();
      return true;
   }

   public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
      Builder dia = new Builder(this.a);
      dia.setTitle(null);
      dia.setMessage(message);
      final EditText input = new EditText(this.a);
      if (defaultValue != null) {
         input.setText(defaultValue);
      }

      input.setSelectAllOnFocus(true);
      dia.setView(input);
      dia.setCancelable(false);
      dia.setPositiveButton("确定", new OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
            result.confirm(input.getText().toString());
         }
      });
      dia.setNegativeButton("取消", new OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
            result.cancel();
         }
      });
      dia.show();
      return true;
   }

   public Bitmap getDefaultVideoPoster() {
      if (this.g != null) {
         this.g.x();
      }

      int videoImg = UZResourcesIDFinder.getResDrawableID("uz_video");
      if (videoImg == 0) {
         return null;
      } else {
         Bitmap map = BitmapFactory.decodeResource(this.a.getResources(), videoImg);
         return map;
      }
   }

   public View getVideoLoadingProgressView() {
      com.uzmap.pkg.uzcore.external.i progress = new com.uzmap.pkg.uzcore.external.i(this.a, null);
      progress.a("加载中...");
      progress.a();
      return progress;
   }

   public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
      com.uzmap.pkg.uzcore.external.bb.b container = new com.uzmap.pkg.uzcore.external.bb.b(this.a, null);
      LayoutParams parm = com.uzmap.pkg.uzcore.external.l.d(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
      container.setLayoutParams(parm);
      container.addView(view);
      container.a(new com.uzmap.pkg.uzcore.external.bb.b.a() {
         public void a() {
            p.this.b();
         }
      });
      boolean handler = this.e.a(container, requestedOrientation);
      if (!handler) {
         callback.onCustomViewHidden();
      } else {
         this.c = callback;
      }
   }

   public void onShowCustomView(View view, CustomViewCallback callback) {
      this.onShowCustomView(view, 1, callback);
   }

   public void onHideCustomView() {
      this.e.u();
   }

   private void b() {
      if (this.c != null) {
         this.c.onCustomViewHidden();
      }

      this.c = null;
   }

   public void onReceivedTitle(WebView view, String title) {
      this.e.c((com.uzmap.pkg.uzcore.a) view, title);
   }

   public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, QuotaUpdater quotaUpdater) {
      if (estimatedSize < 104857600L) {
         quotaUpdater.updateQuota(estimatedSize);
      } else {
         quotaUpdater.updateQuota(currentQuota);
      }

   }

   public void a(Context context, String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
      if (com.uzmap.pkg.uzcore.external.l.a > 12) {
         com.uzmap.pkg.ui.intent.a.a(this.a, url, userAgent, contentDisposition, mimetype);
      } else if (this.b == null) {
         Intent installIntent = new Intent("android.intent.action.VIEW");
         Uri path = Uri.parse(url);
         if (path.getScheme() == null) {
            path = Uri.fromFile(new File(url));
         }

         installIntent.setDataAndType(path, mimetype);
         installIntent.setFlags(268435456);
         if (UZCoreUtil.appExist(installIntent)) {
            try {
               context.startActivity(installIntent);
            } catch (Exception var12) {
               var12.printStackTrace();
               Toast.makeText(context, "未找到可执行的应用", 0).show();
            }

         } else {
            this.b = new com.uzmap.pkg.uzcore.external.c(context, null);
            this.b.a(url);
            this.b.b(mimetype);
            ValueCallback<Object> callback = new ValueCallback<Object>() {
               public void onReceiveValue(Object value) {
                  p.this.b = null;
               }
            };
            this.b.a(callback);
            this.b.show();
         }
      }
   }

   public void onGeolocationPermissionsShowPrompt(final String origin, final Callback callback) {
      if (this.f == null && this.c()) {
         JSONObject data = new JSONObject();

         try {
            Uri uri = Uri.parse(origin);
            String title = "http".equals(uri.getScheme()) ? origin.substring(7) : origin;
            title = title + "需要了解您的位置信息";
            String cancelTitle = "忽略";
            String destructiveTitle = "拒绝";
            JSONArray buttons = new JSONArray();
            buttons.put("共享位置信息");
            data.put("title", title);
            data.put("cancelTitle", cancelTitle);
            data.put("destructiveTitle", destructiveTitle);
            data.put("buttons", buttons);
         } catch (Exception var9) {
         }

         this.f = new com.uzmap.pkg.uzcore.external.a(this.a, null) {
            public void a(JSONObject json) {
               if (json != null) {
                  int index = json.optInt("buttonIndex", -1);
                  if (2 == index) {
                     callback.invoke(origin, true, true);
                  } else {
                     callback.invoke(origin, false, false);
                  }
               }

               p.this.f = null;
            }
         };
         this.f.a(null, data);
         this.f.show();
      }
   }

   public void onGeolocationPermissionsHidePrompt() {
      if (this.f != null) {
         this.f = null;
      }

   }

   public boolean onConsoleMessage(ConsoleMessage console) {
      String url = console.sourceId();
      String message;
      if (TextUtils.isEmpty(url)) {
         url = "JsRuntime";
      } else {
         message = URLUtil.guessFileName(url, null, "text/html");
         if (message.startsWith("download")) {
            Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            if ("http".equals(scheme)) {
               url = uri.getHost();
            } else if ("file".equals(scheme)) {
               url = uri.getPath();
            }
         } else {
            url = message;
         }
      }

      message = console.message() + " at " + url + " : " + console.lineNumber();
      MessageLevel level = console.messageLevel();
      switch (a()[level.ordinal()]) {
         case 1:
            Log.d("app3c", message);
            break;
         case 2:
            Log.e("app3c", message);
            break;
         case 3:
            Log.i("app3c", message);
            break;
         case 4:
            Log.i("app3c", message);
            break;
         case 5:
            Log.w("app3c", message);
      }

      if (level == MessageLevel.ERROR) {
         this.e.c(message);
      }

      return true;
   }

   public void openFileChooser(ValueCallback<Uri> uploadFile) {
      this.openFileChooser(uploadFile, "*/*");
   }

   public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType) {
      this.openFileChooser(uploadFile, acceptType, "");
   }

   public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String capture) {
      if (this.d != null) {
         uploadFile.onReceiveValue(null);
      } else {
         this.d = new b(this.a, this);
         this.d.openFileChooser(uploadFile, acceptType, capture);
      }
   }

   public void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (100001 == requestCode) {
         if (this.d != null) {
            this.d.av(resultCode, data);
            this.d = null;
         }

      }
   }

   private boolean c() {
      PackageManager pm = this.a.getPackageManager();
      String P1 = "android.permission.ACCESS_COARSE_LOCATION";
      String P2 = "android.permission.ACCESS_FINE_LOCATION";
      String packageName = this.a.getPackageName();
      int allow = 0;
      return allow == pm.checkPermission(P1, packageName) && allow == pm.checkPermission(P2, packageName);
   }

   public interface a {
      void x();
   }
}
