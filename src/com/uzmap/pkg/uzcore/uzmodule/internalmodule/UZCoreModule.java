package com.uzmap.pkg.uzcore.uzmodule.internalmodule;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.Toast;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.aa.JSCore;
import com.uzmap.pkg.uzcore.f;
import com.uzmap.pkg.uzcore.m;
import com.uzmap.pkg.uzcore.*;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.aa.c;
import com.uzmap.pkg.uzcore.uzmodule.aa.g;
import com.uzmap.pkg.uzcore.uzmodule.aa.h;
import com.uzmap.pkg.uzcore.uzmodule.aa.i;
import com.uzmap.pkg.uzcore.uzmodule.aa.j;
import com.uzmap.pkg.uzcore.uzmodule.aa.k;
import com.uzmap.pkg.uzcore.uzmodule.aa.l;
import com.uzmap.pkg.uzcore.uzmodule.aa.n;
import com.uzmap.pkg.uzcore.uzmodule.aa.o;
import com.uzmap.pkg.uzcore.uzmodule.aa.p;
import com.uzmap.pkg.uzcore.uzmodule.aa.q;
import com.uzmap.pkg.uzcore.uzmodule.aa.r;
import com.uzmap.pkg.uzcore.uzmodule.aa.s;
import com.uzmap.pkg.uzcore.uzmodule.e;
import com.uzmap.pkg.uzkit.fineHttp.RequestParam;
import com.uzmap.pkg.uzkit.fineHttp.UZHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class UZCoreModule extends UZModule {
   protected String a;
   String b = null;
   private com.uzmap.pkg.uzcore.external.a c;
   private boolean d;
   private final b e;
   private boolean f = false;

   public UZCoreModule(UZWebView webView) {
      super(webView);
      this.setModuleName(JSCore.a);
      this.e = new b(this);
   }

   @JavascriptInterface
   public void installApp(String args) {
      k moduleContext = new k(args, this.mWebView);
      String appPath = moduleContext.optString("appUri");
      appPath = this.makeRealPath(appPath);
      UZCoreUtil.installApp(this.mContext, appPath);
   }

   @JavascriptInterface
   public void openApp(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.a(moduleContext);
   }

   @JavascriptInterface
   public void toLauncher(String args) {
      Intent intent = new Intent("android.intent.action.MAIN");
      intent.addCategory("android.intent.category.HOME");

      try {
         this.mContext.startActivity(intent);
      } catch (Exception var4) {
      }

   }

   @JavascriptInterface
   public void openWidget(String args) {
      if (!com.uzmap.pkg.uzcore.f.f()) {
         if (this.getWindow() != null) {
            r moduleContext = new r(args, this.mWebView, false);
            e wgtInfo = com.uzmap.pkg.uzkit.data.d.a(moduleContext, false);
            if (wgtInfo != null) {
               this.openWidgetWidthInfo(wgtInfo);
            } else {
               JSONObject json = new JSONObject();
               boolean status = false;
               String msg = "widget not exist!";

               try {
                  json.put("status", status);
                  json.put("msg", msg);
               } catch (Exception var8) {
                  var8.printStackTrace();
               }

               moduleContext.error(null, json, true);
            }

         }
      }
   }

   @JavascriptInterface
   public void closeWidget(String args) {
      if (!com.uzmap.pkg.uzcore.f.f()) {
         if (this.getWindow() != null) {
            r moduleContext = new r(args, this.mWebView, true);
            this.closeWidgetWidthInfo(moduleContext);
         }
      }
   }

   @JavascriptInterface
   public void getFsWidgets(String args) {
      UZModuleContext moduleContext = new k(args, this.mWebView);
      JSONObject jsonResult = com.uzmap.pkg.uzkit.data.d.a(moduleContext);
      moduleContext.success(jsonResult, true);
   }

   @JavascriptInterface
   public void openWin(String args) {
      if (!com.uzmap.pkg.uzcore.f.f()) {
         final s moduleContext = new s(args, this.mWebView, false);
         if (!TextUtils.isEmpty(moduleContext.y)) {
            moduleContext.a(this.mWebView.q());
            moduleContext.a(this.d, this.a, this.getWidgetInfo());
            Runnable action = new Runnable() {
               public void run() {
                  m win = UZCoreModule.this.getWindow();
                  if (win != null) {
                     win.a1(UZCoreModule.this.mWebView, moduleContext);
                  }
               }
            };
            this.runOnUiThread(action);
         }
      }
   }

   @JavascriptInterface
   public void openSlidLayout(String args) {
      if (!com.uzmap.pkg.uzcore.f.f()) {
         final p moduleContext = new p(args, this.mWebView);
         moduleContext.a(this.mWebView.q());
         moduleContext.a(this.d, this.a, this.getWidgetInfo());
         Runnable action = new Runnable() {
            public void run() {
               m win = UZCoreModule.this.getWindow();
               if (win != null) {
                  win.a1(UZCoreModule.this.mWebView, moduleContext);
               }
            }
         };
         this.runOnUiThread(action);
      }
   }

   @JavascriptInterface
   public void openSlidPane(String args) {
      final k moduleContext = new k(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void closeSlidPane(String args) {
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(UZCoreModule.this.mWebView);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void setWinAttr(String args) {
      final s moduleContext = new s(args, this.mWebView, false);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.b(UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void closeWin(String args) {
      if (!com.uzmap.pkg.uzcore.f.f()) {
         final s moduleContext = new s(args, this.mWebView, true);
         Runnable action = new Runnable() {
            public void run() {
               m win = UZCoreModule.this.getWindow();
               if (win != null) {
                  win.a1(moduleContext);
               }
            }
         };
         this.runOnUiThread(action);
      }
   }

   @JavascriptInterface
   public void closeToWin(String args) {
      if (!com.uzmap.pkg.uzcore.f.f()) {
         final s moduleContext = new s(args, this.mWebView, true);
         if (!TextUtils.isEmpty(moduleContext.y)) {
            Runnable action = new Runnable() {
               public void run() {
                  m win = UZCoreModule.this.getWindow();
                  if (win != null) {
                     win.b(moduleContext);
                  }
               }
            };
            this.runOnUiThread(action);
         }
      }
   }

   @JavascriptInterface
   public void execScript(String args) {
      final o moduleContext = new o(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a(UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void openFrame(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.f moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.f(args, this.mWebView);
      moduleContext.a(this.mWebView.q());
      moduleContext.a(this.d, this.a, this.getWidgetInfo());
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1((WebView) UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void setFrameAttr(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.f moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.f(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.c(UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void bringFrameToFront(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.f moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.f(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.d(UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void sendFrameToBack(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.f moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.f(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.e(UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void animation(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.e moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.e(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1((WebView) mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void closeFrame(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.f moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.f(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.b((WebView) UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void openFrameGroup(String args) {
      final g moduleContext = new g(args, this.mWebView);
      moduleContext.a(this.mWebView.q());
      moduleContext.a(this.d, this.a, this.getWidgetInfo());
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1((WebView) UZCoreModule.this.mWebView, moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void setFrameGroupAttr(String args) {
      final g context = new g(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.b((WebView) UZCoreModule.this.mWebView, context);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void setFrameGroupIndex(String args) {
      final g context = new g(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.c(UZCoreModule.this.mWebView, context);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void closeFrameGroup(String args) {
      final g context = new g(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.d(UZCoreModule.this.mWebView, context);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void addEventListener(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.d moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.d(args, this.mWebView);
      if (!moduleContext.b()) {
         Runnable action = new Runnable() {
            public void run() {
               if (UZCoreModule.this.valid()) {
                  UZCoreModule.this.mWebView.a(moduleContext);
               }
            }
         };
         this.runOnUiThread(action);
      }
   }

   @JavascriptInterface
   public void removeEventListener(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.d moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.d(args, this.mWebView);
      if (!moduleContext.b()) {
         Runnable action = new Runnable() {
            public void run() {
               UZCoreModule.this.mWebView.c(moduleContext.a);
            }
         };
         this.runOnUiThread(action);
      }
   }

   @JavascriptInterface
   public void setRefreshHeaderInfo(String args) {
      final n moduleContext = new n(args, this.mWebView);
      moduleContext.a(this.mWebView.q());
      moduleContext.a(this.getWidgetInfo());
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.valid()) {
               UZCoreModule.this.mWebView.a(moduleContext);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void refreshHeaderLoadDone(String args) {
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.valid()) {
               UZCoreModule.this.mWebView.v();
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void refreshHeaderLoading(String args) {
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.valid()) {
               UZCoreModule.this.mWebView.w();
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void log(String args) {
   }

   @JavascriptInterface
   public void alert(String args) {
      final c moduleContext = new c(args, this.mWebView, 0);
      Runnable action = new Runnable() {
         public void run() {
            (new com.uzmap.pkg.uzcore.external.o(moduleContext)).a();
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void confirm(String args) {
      final c moduleContext = new c(args, this.mWebView, 1);
      Runnable action = new Runnable() {
         public void run() {
            (new com.uzmap.pkg.uzcore.external.o(moduleContext)).b();
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void prompt(String args) {
      final c moduleContext = new c(args, this.mWebView, 2);
      Runnable action = new Runnable() {
         public void run() {
            (new com.uzmap.pkg.uzcore.external.o(moduleContext)).c();
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void showProgress(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.m moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.m(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void hideProgress(String args) {
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.o();
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void setPrefs(String args) {
      i moduleContext = new i(args, this.mWebView);
      com.uzmap.pkg.uzcore.external.m.a().a(moduleContext.a, moduleContext.b);
      moduleContext.success(null, true);
   }

   @JavascriptInterface
   public void getPrefs(String args) {
      i moduleContext = new i(args, this.mWebView);
      String value = com.uzmap.pkg.uzcore.external.m.a().b(moduleContext.a, "");
      JSONObject json = new JSONObject();

      try {
         json.put("value", value);
      } catch (JSONException var6) {
         var6.printStackTrace();
      }

      moduleContext.success(json, true);
   }

   @JavascriptInterface
   public void removePrefs(String args) {
      i moduleContext = new i(args, this.mWebView);
      com.uzmap.pkg.uzcore.external.m.a().a(moduleContext.a);
      moduleContext.success(null, true);
   }

   @JavascriptInterface
   public void loadSecureValue(String args) {
      i moduleContext = new i(args, this.mWebView);
      String value = this.getSecureValue(moduleContext.a);
      value = value != null ? value : "";
      JSONObject json = new JSONObject();

      try {
         json.put("value", value);
      } catch (JSONException var6) {
         var6.printStackTrace();
      }

      moduleContext.success(json, true);
   }

   @JavascriptInterface
   public void getPicture(String args) {
      j moduleContext = new j(args, this.mWebView);
      this.e.a(moduleContext);
   }

   @JavascriptInterface
   public void ajax(String args) {
      com.uzmap.pkg.uzcore.uzmodule.aa.a moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.a(args, this.mWebView);
      RequestParam ajaxArgument = new RequestParam(moduleContext.get());
      ajaxArgument.makeRealUrl(this.getWidgetInfo());
      ajaxArgument.setTag("ajax-" + moduleContext.getVid());
      UZHttpClient.get().execute(ajaxArgument, moduleContext);
      this.f = true;
   }

   @JavascriptInterface
   public void cancelAjax(String args) {
      k moduleContext = new k(args, this.mWebView);
      String tag = moduleContext.optString("tag", null);
      if (!TextUtils.isEmpty(tag)) {
         UZHttpClient.get().cancel(tag);
      }
   }

   @JavascriptInterface
   public void call(String args) {
      UZModuleContext moduleContext = new k(args, this.mWebView);
      int type = UZConstant.mapInt(moduleContext.optString("type"), 0);
      String number = moduleContext.optString("number");
      this.e.a(number, 1 == type);
   }

   @JavascriptInterface
   public void sms(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.b(moduleContext);
   }

   @JavascriptInterface
   public void mail(String args) {
      UZModuleContext moduleContext = new k(args, this.mWebView);
      this.e.a(moduleContext);
   }

   @JavascriptInterface
   public void readFile(String args) {
      k moduleContext = new k(args, this.mWebView);
      String path = moduleContext.optString("path");
      String charset = moduleContext.optString("charset", "UTF-8");
      if (!TextUtils.isEmpty(path)) {
         path = this.makeRealPath(path);
      } else {
         path = "";
      }

      JSONObject data = this.e.a(path, charset);
      moduleContext.success(data, true);
   }

   @JavascriptInterface
   public void writeFile(String args) {
      k moduleContext = new k(args, this.mWebView);
      String path = moduleContext.optString("path");
      path = this.makeRealPath(path);
      JSONObject json;
      if (TextUtils.isEmpty(path)) {
         json = new JSONObject();

         try {
            json.put("status", false);
            json.put("msg", "path can not be empty");
         } catch (Exception var7) {
         }

         moduleContext.success(json, true);
      } else if (path.contains("android_asset")) {
         json = new JSONObject();

         try {
            json.put("status", false);
            json.put("msg", "path is readonly");
         } catch (Exception var8) {
         }

         moduleContext.success(json, true);
      } else {
         String data = moduleContext.optString("data");
         boolean append = moduleContext.optBoolean("append", false);
         json = this.e.a(path, data, append);
         moduleContext.success(json, true);
      }
   }

   @JavascriptInterface
   public void startRecord(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.e(moduleContext);
   }

   @JavascriptInterface
   public void stopRecord(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.f(moduleContext);
   }

   @JavascriptInterface
   public void startPlay(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.g(moduleContext);
   }

   @JavascriptInterface
   public void stopPlay(String args) {
      this.e.a();
   }

   @JavascriptInterface
   public void startLocation(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.h(moduleContext);
   }

   @JavascriptInterface
   public void stopLocation(String args) {
      this.e.b();
   }

   @JavascriptInterface
   public void getLocation(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.i(moduleContext);
   }

   @JavascriptInterface
   public void getAddress(String args) {
      this.e.a(args);
   }

   @JavascriptInterface
   public void startSensor(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.j(moduleContext);
   }

   @JavascriptInterface
   public void stopSensor(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.k(moduleContext);
   }

   @JavascriptInterface
   public void setStatusBarStyle(String args) {
      k moduleContext = new k(args, this.mWebView);
      if (!moduleContext.isNull("color")) {
         String c = moduleContext.optString("color", "#000");
         int color = UZCoreUtil.parseColor(c);
         this.e.a(this.mContext, color);
      }
   }

   @JavascriptInterface
   public void setFullScreen(String args) {
      k moduleContext = new k(args, this.mWebView);
      final boolean fullScreen = moduleContext.optBoolean("fullScreen");
      Runnable action = new Runnable() {
         public void run() {
            f engine = com.uzmap.pkg.uzcore.f.b(UZCoreModule.this.mContext);
            if (engine != null) {
               engine.c(fullScreen);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void openContacts(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.d(moduleContext);
   }

   @JavascriptInterface
   public void openVideo(String args) {
      k moduleContext = new k(args, this.mWebView);
      String url = moduleContext.optString("url");
      if (!TextUtils.isEmpty(url)) {
         url = this.makeRealPath(url);
         Intent intent = new Intent("android.intent.action.VIEW");
         if (!URLUtil.isValidUrl(url)) {
            url = "file://" + url;
         }

         String type = "video/*";
         Uri data = Uri.parse(url);
         intent.setDataAndType(data, type);
         if (!UZCoreUtil.appExist(intent)) {
            Toast.makeText(this.mContext, "未找到播放程序!", 0).show();
         } else {
            this.startActivity(intent);
         }
      }
   }

   @JavascriptInterface
   public void removeLaunchView(String args) {
      com.uzmap.pkg.uzcore.uzmodule.aa.b moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.b(args, this.mWebView, false);
      final v animPair;
      if (moduleContext.isNull("animation")) {
         animPair = null;
      } else {
         animPair = moduleContext.a();
      }

      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.valid()) {
               f engine = com.uzmap.pkg.uzcore.f.b(UZCoreModule.this.mContext);
               if (engine != null) {
                  engine.a(true, animPair);
               }
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void openPicker(String args) {
      final k moduleContext = new k(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            UZCoreModule.this.e.c(moduleContext);
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void download(String args) {
      if (this.e.c()) {
         com.uzmap.pkg.uzcore.uzmodule.aa.a moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.a(args, this.mWebView);
         RequestParam downloadParam = new RequestParam(moduleContext.get());
         downloadParam.setMethod(5);
         downloadParam.setDefaultSavePath(this.comUzDownloadPath());
         downloadParam.makeRealUrl(this.getWidgetInfo());
         UZHttpClient.get().execute(downloadParam, moduleContext);
      }
   }

   @JavascriptInterface
   public void cancelDownload(String args) {
      k moduleContext = new k(args, this.mWebView);
      String url = moduleContext.optString("url");
      final String tag = UZCoreUtil.random(url);
      Runnable action = new Runnable() {
         public void run() {
            UZHttpClient.get().cancelDownload(tag);
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void actionSheet(String args) {
      if (this.c == null) {
         final k moduleContext = new k(args, this.mWebView);
         Runnable action = new Runnable() {
            public void run() {
               UZCoreModule.this.c = new com.uzmap.pkg.uzcore.external.a(UZCoreModule.this.mContext, null) {
                  public void a(JSONObject json) {
                     if (json != null) {
                        moduleContext.success(json, true);
                     }

                     UZCoreModule.this.c = null;
                  }
               };
               JSONObject style = moduleContext.optJSONObject("style");
               UZCoreModule.this.c.a(style, moduleContext.get());
               UZCoreModule.this.c.show();
            }
         };
         this.runOnUiThread(action);
      }
   }

   @JavascriptInterface
   public void clearCache(String args) {
      k moduleContext = new k(args, this.mWebView);
      String type = moduleContext.optString("type", null);
      float timeThreshold = (float) moduleContext.optDouble("timeThreshold", -1.0D);
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.mWebView != null) {
               UZCoreModule.this.mWebView.clearCache(true);
            }

         }
      };
      this.runOnUiThread(action);
      String id = this.getWidgetInfo() != null ? this.getWidgetInfo().id : null;
      a clearaAtion = new a(id);
      clearaAtion.a(type, timeThreshold);
      clearaAtion.a();
      moduleContext.success(null, true);
   }

   @JavascriptInterface
   public void toast(String args) {
      k moduleContext = new k(args, this.mWebView);
      final String msg = moduleContext.optString("msg");
      final int duration = moduleContext.optInt("duration", 2000);
      final int location = UZConstant.mapInt(moduleContext.optString("location"), 0);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(msg, location, duration);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void showFloatBox(String args) {
      final k moduleContext = new k(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(moduleContext);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void notification(String args) {
      l moduleContext = new l(args, this.mWebView);
      this.e.a(moduleContext);
   }

   @JavascriptInterface
   public void cancelNotification(String args) {
      l moduleContext = new l(args, this.mWebView);
      this.e.b(moduleContext);
   }

   @JavascriptInterface
   public void setScreenOrientation(String args) {
      k moduleContext = new k(args, this.mWebView);
      String orien = moduleContext.optString("orientation");
      final int orientation = UZConstant.mapInt(orien, 9);
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.valid()) {
               f engine = com.uzmap.pkg.uzcore.f.b(UZCoreModule.this.mContext);
               if (engine != null) {
                  engine.b(orientation);
               }
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void lockSlidPane(String args) {
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(UZCoreModule.this.mWebView, true);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void unlockSlidPane(String args) {
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(UZCoreModule.this.mWebView, false);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void setKeepScreenOn(String args) {
      k moduleContext = new k(args, this.mWebView);
      final boolean keepOn = moduleContext.optBoolean("keepOn");
      Runnable action = new Runnable() {
         public void run() {
            f engine = com.uzmap.pkg.uzcore.f.b(UZCoreModule.this.mContext);
            if (engine != null) {
               engine.d(keepOn);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void historyBack(String args) {
      final k context = new k(args, this.mWebView);
      final String frameName = context.optString("frameName");
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               boolean status = win.a1(frameName, UZCoreModule.this.mWebView);
               JSONObject json = new JSONObject();

               try {
                  json.put("status", status);
               } catch (Exception var5) {
               }

               context.success(json, true);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void historyForward(String args) {
      final k context = new k(args, this.mWebView);
      final String frameName = context.optString("frameName");
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               boolean status = win.b(frameName, UZCoreModule.this.mWebView);
               JSONObject json = new JSONObject();

               try {
                  json.put("status", status);
               } catch (Exception var5) {
               }

               context.success(json, true);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void sendEvent(String args) {
      final com.uzmap.pkg.uzcore.uzmodule.aa.d moduleContext = new com.uzmap.pkg.uzcore.uzmodule.aa.d(args, this.mWebView);
      Runnable action = new Runnable() {
         public void run() {
            f engine = com.uzmap.pkg.uzcore.f.b(UZCoreModule.this.mContext);
            if (engine != null) {
               engine.a(UZCoreModule.this.mWebView, moduleContext);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void appInstalled(String args) {
      k moduleContext = new k(args, this.mWebView);
      String packageName = moduleContext.optString("appBundle", null);
      boolean exist = false;
      if (packageName != null) {
         exist = UZCoreUtil.appExist(packageName);
      }

      JSONObject json = new JSONObject();

      try {
         json.put("installed", exist ? 1 : 0);
      } catch (Exception var7) {
      }

      moduleContext.success(json, true);
   }

   @JavascriptInterface
   public void requestFocus(String args) {
      k moduleContext = new k(args, this.mWebView);
      final String frameName = moduleContext.optString("name", null);
      Runnable action = new Runnable() {
         public void run() {
            m win = UZCoreModule.this.getWindow();
            if (win != null) {
               win.a1(frameName);
            }
         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public Object onTvPeak(String args) {
      if (!this.valid()) {
         return false;
      } else {
         k moduleContext = new k(args, this.mWebView);
         int keyCode = moduleContext.optInt("keyCode");
         m window = this.getWindow();
         if (window != null) {
            boolean handled = window.a1(this.mWebView, keyCode);
            return handled;
         }

         return false;
      }
   }

   @JavascriptInterface
   public Object setTvFocusElement(String args) {
      if (!this.valid()) {
         return false;
      } else {
         q moduleContext = new q(args, this.mWebView);
         m window = this.getWindow();
         if (window != null) {
            window.a1(this.mWebView, moduleContext);
         }

         return false;
      }
   }

   @JavascriptInterface
   public void pageDown(String args) {
      final k moduleContext = new k(args, this.mWebView);
      final boolean jumpToBottom = moduleContext.optBoolean("bottom");
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.mWebView != null) {
               boolean scrolled = UZCoreModule.this.mWebView.f(jumpToBottom);
               JSONObject json = new JSONObject();

               try {
                  json.put("scrolled", scrolled);
               } catch (Exception var4) {
               }

               moduleContext.success(json, true);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void pageUp(String args) {
      final k moduleContext = new k(args, this.mWebView);
      final boolean jumpToTop = moduleContext.optBoolean("top");
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.mWebView != null) {
               boolean scrolled = UZCoreModule.this.mWebView.g(jumpToTop);
               JSONObject json = new JSONObject();

               try {
                  json.put("scrolled", scrolled);
               } catch (Exception var4) {
               }

               moduleContext.success(json, true);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void imageCache(String args) {
      h moduleContext = new h(args, this.mWebView);
      if (moduleContext.b()) {
         this.e.a(moduleContext, this.getWidgetInfo());
      }
   }

   @JavascriptInterface
   public void pageScrollBy(String args) {
      final k moduleContext = new k(args, this.mWebView);
      final int x = UZCoreUtil.dipToPix(moduleContext.optInt("x"));
      final int y = UZCoreUtil.dipToPix(moduleContext.optInt("y"));
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.mWebView != null) {
               boolean scrolled = UZCoreModule.this.mWebView.a(x, y);
               JSONObject json = new JSONObject();

               try {
                  json.put("scrolled", scrolled);
               } catch (Exception var4) {
               }

               moduleContext.success(json, true);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void pageScrollTo(String args) {
      final k moduleContext = new k(args, this.mWebView);
      final int x = UZCoreUtil.dipToPix(moduleContext.optInt("x"));
      final int y = UZCoreUtil.dipToPix(moduleContext.optInt("y"));
      Runnable action = new Runnable() {
         public void run() {
            if (UZCoreModule.this.mWebView != null) {
               boolean scrolled = UZCoreModule.this.mWebView.b(x, y);
               JSONObject json = new JSONObject();

               try {
                  json.put("scrolled", scrolled);
               } catch (Exception var4) {
               }

               moduleContext.success(json, true);
            }

         }
      };
      this.runOnUiThread(action);
   }

   @JavascriptInterface
   public void saveMediaToAlbum(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.b((UZModuleContext) moduleContext);
   }

   @JavascriptInterface
   public void setScreenSecure(String args) {
      k moduleContext = new k(args, this.mWebView);
      boolean secure = moduleContext.optBoolean("secure", false);
      f engine = com.uzmap.pkg.uzcore.f.b(this.mContext);
      if (engine != null) {
         engine.e(secure);
      }

   }

   @JavascriptInterface
   public void setAppIconBadge(String args) {
      k moduleContext = new k(args, this.mWebView);
      final int total = moduleContext.optInt("badge", 0);
      this.runOnUiThread(new Runnable() {
         public void run() {
            if (total > 0) {
               com.uzmap.pkg.uzapp.a.a(UZCoreModule.this.mContext, total);
            } else {
               com.uzmap.pkg.uzapp.a.a(UZCoreModule.this.mContext);
            }

         }
      });
   }

   @JavascriptInterface
   public void getCacheSize(String args) {
      k moduleContext = new k(args, this.mWebView);
      this.e.a(moduleContext, this.getWidgetInfo().id);
   }

   @JavascriptInterface
   public void getFreeDiskSpace(String args) {
      k moduleContext = new k(args, this.mWebView);
      long availableSpace = UZCoreUtil.getAvailableSpace();
      JSONObject json = new JSONObject();

      try {
         json.put("size", availableSpace);
      } catch (Exception var7) {
      }

      moduleContext.success(json, true);
   }

   @JavascriptInterface
   public void accessNative(String args) {
      if (this.valid()) {
         UZModuleContext moduleContext = new UZModuleContext(args, this.mWebView);
         this.mEngine.a(this.mWebView, moduleContext);
      }
   }

   @JavascriptInterface
   public String R(final String args) {
      if (this.mContext != null) {
         Runnable action = new Runnable() {
            public void run() {
               if (UZCoreModule.this.mContext != null) {
                  String error = args + " 模块未绑定\n" + "如果您使用了apploader进行调试，请在网站控制台绑定后编译正式版使用；\n" + "如果您使用了自定义loader进行调试，请绑定模块后重新编译自定义loader即可";
                  f engine = com.uzmap.pkg.uzcore.f.b(UZCoreModule.this.mContext);
                  if (engine != null) {
                     engine.c(error);
                  }
               }

            }
         };
         this.mContext.runOnUiThread(action);
      }

      return "";
   }

   @JavascriptInterface
   public void ovrri() {
   }

   @JavascriptInterface
   public void G(int tag, String args) {
   }

   @JavascriptInterface
   public void E(final String jsobject, final String jsmethod, String args) {
      if (this.valid()) {
         final com.uzmap.pkg.uzcore.uzmodule.b moduleMgr = this.mWebView.c();
         final UZModuleContext mcontext = new UZModuleContext(args, this.mWebView);
         Runnable action = new Runnable() {
            public void run() {
               moduleMgr.a(jsobject, jsmethod, mcontext);
            }
         };
         this.runOnUiThread(action);
      }
   }

   @JavascriptInterface
   public String ES(String jsobject, String jsmethod) {
      if (!this.valid()) {
         return "";
      } else {
         com.uzmap.pkg.uzcore.uzmodule.b moduleMgr = this.mWebView.c();
         Object result = moduleMgr.a(jsobject, jsmethod, null);
         return result != null ? result.toString() : "";
      }
   }

   @JavascriptInterface
   public String A(int type) {
      String result = "";
      switch (type) {
         case 0:
            result = UZCoreUtil.getUzVersion();
            break;
         case 1:
            result = "android";
            break;
         case 2:
            result = VERSION.RELEASE;
            break;
         case 3:
            result = UZCoreUtil.getDeviceId();
            break;
         case 4:
            result = Build.DEVICE;
            break;
         case 5:
            result = Build.MANUFACTURER;
            break;
         case 6:
            result = this.mEngine.r();
            break;
         case 7:
            com.uzmap.pkg.uzcore.uzmodule.d wmp = this.mWebView.r();
            result = wmp.b();
            break;
         case 8:
            com.uzmap.pkg.uzcore.uzmodule.d pmp = this.mWebView.s();
            result = pmp.b();
            break;
         case 9:
            result = this.mEngine.p();
            break;
         case 10:
            result = this.getWidgetInfo().widgetPath();
            break;
         case 11:
            result = this.mWebView.p();
            break;
         case 12:
            if (!this.mWebView.o()) {
               result = "";
            } else {
               result = this.mWebView.B();
            }
            break;
         case 13:
            result = "" + this.mWebView.d();
            break;
         case 14:
            result = "" + this.mWebView.e();
            break;
         case 15:
            result = "" + this.mWebView.i();
            break;
         case 16:
            result = "" + this.mWebView.j();
            break;
         case 17:
            result = this.getWidgetInfo().id;
            break;
         case 18:
            result = UZCoreUtil.getAppName();
            break;
         case 19:
            result = UZFileSystem.get().getWidgetLoadPath();
            break;
         case 20:
            result = UZCoreUtil.getAppVersionName();
            break;
         case 21:
            result = "" + com.uzmap.pkg.uzcore.d.a().m;
            break;
         case 22:
            result = "" + com.uzmap.pkg.uzcore.d.a().k;
            break;
         case 23:
            if (this.b == null) {
               this.b = UZFileSystem.get().getWidgetRootPath(this.getWidgetInfo().id);
               this.b = this.b.substring(0, this.b.length() - 1);
            }

            result = this.b;
            break;
         case 24:
            if (this.b == null) {
               this.b = UZFileSystem.get().getWidgetRootPath(this.getWidgetInfo().id);
               this.b = this.b.substring(0, this.b.length() - 1);
            }

            result = this.b;
            break;
         case 25:
            result = this.mEngine.s();
            break;
         case 26:
            result = UZCoreUtil.getDeviceId();
            break;
         case 27:
            result = Build.FINGERPRINT;
      }

      return result;
   }

   public void onActivityResult(int requestCode, int resultCode, Intent data) {
      switch (requestCode) {
         case 2000001:
            this.e.d(resultCode, data);
            break;
         case 2000002:
         case 2000003:
         case 2000005:
            this.e.b(resultCode, data);
         case 2000004:
         default:
            break;
         case 2000006:
            this.e.c(resultCode, data);
            break;
         case 2000007:
            this.e.a(resultCode, data);
      }

   }

   private String comUzDownloadPath() {
      String appId = this.getWidgetInfo().id;
      return UZFileSystem.get().getWidgetDownloadPath(appId);
   }

   protected void onClean() {
      if (this.e != null) {
         this.e.d();
      }

      if (this.f) {
         UZHttpClient.get().cancel("ajax-" + this.mWebView.A());
      }

   }

   private m getWindow() {
      return !this.valid() ? null : this.mWebView.n();
   }

   public void setGp(String d) {
      this.d = true;
      this.a = d;
   }

   public String toString() {
      return "APIJsObject@" + Integer.toHexString(this.hashCode());
   }
}
