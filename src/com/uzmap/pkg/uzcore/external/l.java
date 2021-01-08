//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.external;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.webkit.WebView;
import com.uzmap.pkg.uzcore.external.bb.g.a;

import java.lang.reflect.Method;
import java.util.HashMap;

public class l {
   static final HashMap<Integer, Integer> h;
   public static int a;
   public static int b;
   public static int c;
   public static int d;
   public static int e;
   public static int f;
   static Paint g;
   static Method i;

   static {
      a = VERSION.SDK_INT;
      b = a < 11 ? 0 : 4;
      c = a < 16 ? 0 : 16777216;
      d = -1;
      e = -2;
      f = a >= 10 ? 3 : 3;
      g = new Paint();
      g.setAlpha(255);
      h = new HashMap();
      h.put(10, 48);
      h.put(12, 80);
      h.put(20, 8388611);
      h.put(21, 8388613);
      h.put(9, 3);
      h.put(11, 5);
      h.put(13, 17);
      h.put(14, 1);
      h.put(15, 16);

      try {
         i = View.class.getDeclaredMethod("getViewRootImpl", (Class[]) null);
         i.setAccessible(true);
      } catch (Exception var1) {
      }

   }

   public static LayoutParams a(int w, int h) {
      return new LayoutParams(w, h);
   }

   public static android.widget.RelativeLayout.LayoutParams b(int w, int h) {
      return new android.widget.RelativeLayout.LayoutParams(w, h);
   }

   public static android.widget.LinearLayout.LayoutParams c(int w, int h) {
      return new android.widget.LinearLayout.LayoutParams(w, h);
   }

   public static android.widget.FrameLayout.LayoutParams d(int w, int h) {
      return new android.widget.FrameLayout.LayoutParams(w, h);
   }

   public static a a(int w, int h, int x, int y) {
      return new a(w, h, x, y);
   }

   public static android.widget.AbsoluteLayout.LayoutParams b(int w, int h, int x, int y) {
      return new android.widget.AbsoluteLayout.LayoutParams(w, h, x, y);
   }

   public static void a(View view, Drawable background) {
      if (a >= 16) {
         view.setBackground(background);
      } else {
         view.setBackgroundDrawable(background);
      }

   }

   public static void a(LayoutParams outlp) {
      if (outlp instanceof MarginLayoutParams) {
         MarginLayoutParams outmlp = (MarginLayoutParams) outlp;
         int width = outmlp.width;
         int height = outmlp.height;
         int left = outmlp.leftMargin;
         int top = outmlp.topMargin;
         int right = outmlp.rightMargin;
         int bottom = outmlp.bottomMargin;
         float density = com.uzmap.pkg.uzcore.d.a().n;
         top = a(top, density);
         left = a(left, density);
         bottom = a(bottom, density);
         right = a(right, density);
         if (width >= 0) {
            width = a(width, density);
         }

         if (height >= 0) {
            height = a(height, density);
         }

         outmlp.topMargin = top;
         outmlp.leftMargin = left;
         outmlp.bottomMargin = bottom;
         outmlp.rightMargin = right;
         outmlp.width = width;
         outmlp.height = height;
         if (outlp instanceof android.widget.FrameLayout.LayoutParams) {
            android.widget.FrameLayout.LayoutParams outflp = (android.widget.FrameLayout.LayoutParams) outmlp;
            outflp.gravity = 48;
         }
      }

   }

   public static android.widget.FrameLayout.LayoutParams b(LayoutParams inlp) {
      android.widget.FrameLayout.LayoutParams outllp = d(inlp.width, inlp.height);
      if (inlp instanceof MarginLayoutParams) {
         MarginLayoutParams inmlp = (MarginLayoutParams) inlp;
         outllp.leftMargin = inmlp.leftMargin;
         outllp.topMargin = inmlp.topMargin;
         outllp.rightMargin = inmlp.rightMargin;
         outllp.bottomMargin = inmlp.bottomMargin;
      }

      int gravity = 0;
      if (inlp instanceof android.widget.RelativeLayout.LayoutParams) {
         android.widget.RelativeLayout.LayoutParams inrlp = (android.widget.RelativeLayout.LayoutParams) inlp;
         int[] rules = inrlp.getRules();

         for (int i = 0; i < rules.length; ++i) {
            if (rules[i] != 0) {
               Integer rule = h.get(i);
               if (rule != null) {
                  gravity |= rule;
               }
            }
         }
      }

      if (gravity > 0) {
         outllp.gravity = gravity;
      } else {
         outllp.gravity = 48;
      }

      return outllp;
   }

   public static android.widget.AbsoluteLayout.LayoutParams c(LayoutParams inlp) {
      android.widget.AbsoluteLayout.LayoutParams outalp = null;
      if (inlp instanceof MarginLayoutParams) {
         MarginLayoutParams outLpa = (MarginLayoutParams) inlp;
         int top = outLpa.topMargin;
         int left = outLpa.leftMargin;
         int width = outLpa.width;
         int height = outLpa.height;
         outalp = b(width, height, left, top);
      } else {
         outalp = b(inlp.width, inlp.height, 0, 0);
      }

      return outalp;
   }

   public static Intent a() {
      Intent intent = new Intent("android.intent.action.PICK");
      intent.setData(Phone.CONTENT_URI);
      if (a < 11) {
         intent.setData(Contacts.CONTENT_URI);
      }

      return intent;
   }

   private static int a(int dip, float density) {
      return (int) ((float) dip * density + 0.5F);
   }

   public static Bitmap a(View target) {
      Config quality = Config.RGB_565;
      int backgroundColor = c;
      Class[] paramTypes = new Class[]{Config.class, Integer.TYPE, Boolean.TYPE};

      try {
         Method createSnapshot = View.class.getDeclaredMethod("createSnapshot", paramTypes);
         createSnapshot.setAccessible(true);
         Bitmap bitmap = (Bitmap) createSnapshot.invoke(target, quality, backgroundColor, false);
         if (bitmap != null) {
            return bitmap;
         }
      } catch (Exception var12) {
         var12.printStackTrace();
      }

      int width = target.getRight() - target.getLeft();
      int height = target.getBottom() - target.getTop();
      float scale = 1.0F;
      width = (int) ((float) width * 1.0F + 0.5F);
      height = (int) ((float) height * 1.0F + 0.5F);
      Resources resources = target.getResources();
      Bitmap bitmap = null;

      try {
         bitmap = Bitmap.createBitmap(resources.getDisplayMetrics(), width > 0 ? width : 1, height > 0 ? height : 1, quality);
      } catch (Exception var11) {
      }

      if (bitmap == null) {
         return null;
      } else {
         if (resources != null) {
            bitmap.setDensity(resources.getDisplayMetrics().densityDpi);
         }

         Canvas canvas = new Canvas(bitmap);
         if ((backgroundColor & -16777216) != 0) {
            bitmap.eraseColor(backgroundColor);
         }

         target.computeScroll();
         int restoreCount = canvas.save();
         canvas.scale(1.0F, 1.0F);
         canvas.translate((float) (-target.getScrollX()), (float) (-target.getScrollY()));
         target.draw(canvas);
         canvas.restoreToCount(restoreCount);
         canvas.setBitmap(null);
         return bitmap;
      }
   }

   public static void a(Activity a) {
      Window w = a.getWindow();
      if (com.uzmap.pkg.uzcore.b.a().m()) {
         w.setBackgroundDrawableResource(17170445);
      }

      if (l.a >= 21) {
         w.setStatusBarColor(-16777216);
      }

   }

   public static void b(final Activity a) {
      if (l.a >= 19) {
         Runnable action = new Runnable() {
            public void run() {
               l.e(a);
            }
         };
         a.runOnUiThread(action);
      }
   }

   public static void a(final Activity a, final int color) {
      if (l.a >= 21) {
         Runnable action = new Runnable() {
            public void run() {
               l.c(a, color);
            }
         };
         a.runOnUiThread(action);
      }
   }

   public static void a(final Activity a, final boolean secure) {
      Runnable action = new Runnable() {
         public void run() {
            l.c(a, secure);
         }
      };
      a.runOnUiThread(action);
   }

   private static void c(Activity a, int color) {
      Window w = a.getWindow();
      int aflag = 0;
      aflag = aflag | -2147483648;
      w.addFlags(aflag);
      w.setStatusBarColor(color);
   }

   private static void e(Activity a) {
      Window w = a.getWindow();
      int cflag = 134217728;
      cflag |= 67108864;
      w.clearFlags(cflag);
      int visibility = 256;
      visibility = visibility | 1024;
      w.getDecorView().setSystemUiVisibility(visibility);
      int aflag = 0;
      if (l.a >= 21) {
         aflag |= -2147483648;
      }

      if (l.a < 21) {
         aflag |= 67108864;
      }

      w.addFlags(aflag);
      if (l.a >= 21) {
         w.setStatusBarColor(c);
      }

      com.uzmap.pkg.uzcore.external.b.a(a);
   }

   private static void c(Activity a, boolean secure) {
      Window w = a.getWindow();
      int flag = 8192;
      if (secure) {
         w.addFlags(flag);
      } else {
         w.clearFlags(flag);
      }

   }

   public static void a(View view, int type) {
      if (a >= 11) {
         view.setLayerType(type, g);
      }
   }

   public static void b(View view) {
      view.setBackgroundColor(c);
   }

   public static boolean c(Activity activity) {
      if (!activity.isTaskRoot()) {
         Intent mainIntent = activity.getIntent();
         String action = mainIntent.getAction();
         return !mainIntent.hasCategory("android.intent.category.LAUNCHER") || !"android.intent.action.MAIN".equals(action);
      }

      return true;
   }

   public static final void a(boolean enable) {
      String func = "enablePlatformNotifications";
      if (!enable) {
         func = "disablePlatformNotifications";
      }

      try {
         Method method = WebView.class.getDeclaredMethod(func);
         method.invoke(WebView.class);
      } catch (Exception var3) {
      }

   }
}
