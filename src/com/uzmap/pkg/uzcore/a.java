package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.*;
import android.webkit.DownloadListener;
import android.widget.RelativeLayout;
import com.uzmap.pkg.openapi.WebViewProvider;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import org.json.JSONObject;

import java.util.WeakHashMap;

public class a extends UZWebView implements DownloadListener, p.a {
   static float a;
   static int b;
   static boolean c;

   static {
      a = com.uzmap.pkg.uzcore.d.a().n;
      b = (int) (a * 1.5F);
      c = false;
   }

   private final int f;
   private String g;
   private com.uzmap.pkg.uzcore.uzmodule.d h;
   private Activity i;
   private com.uzmap.pkg.uzcore.uzmodule.b j;
   private r k;
   private m l;
   private s m;
   private p n;
   private com.uzmap.pkg.ui.a.f.a o;
   private long p;
   private long q = SystemClock.uptimeMillis();
   private Battery.a r;
   private Battery.a s;
   private VelocityTracker t;
   private final WeakHashMap<Integer, com.uzmap.pkg.uzcore.uzmodule.aa.d> u;
   private WebViewProvider v;
   private com.uzmap.pkg.uzcore.external.h w;
   private boolean x;
   private boolean y;
   private boolean z;
   private boolean A;
   private boolean B;
   private boolean C;
   private int D;
   private int E;
   private boolean F;
   private boolean G;
   private boolean H;

   public a(int type, boolean a, Context context, m inWin) {
      super(type, a, context, inWin);
      this.l = inWin;
      this.f = type;
      this.i = (Activity) context;
      this.u = new WeakHashMap(10);
      this.setDownloadListener(this);
   }

   protected void a() {
      this.setNetworkAvailable(true);
      ApiConfig info = this.u();
      boolean r = info.Q;
      String d = info.f();
      this.k = new r(this);
      this.k.a(r);
      this.n = com.uzmap.pkg.uzcore.p.a(this.i);
      this.n.a(this);
      this.a(this.n);
      this.m = com.uzmap.pkg.uzcore.s.a(this.i);
      this.m.a(r, d);
      boolean su = info.N;
      this.m.a(su);
      this.a(this.m);
      this.j = new com.uzmap.pkg.uzcore.uzmodule.b();
      this.j.a(this, r, d);
   }

   public void a(View child, boolean needVerScroll) {
      this.addView(child);
      if (needVerScroll) {
         this.H = needVerScroll;
      }

   }

   public WebViewProvider b() {
      if (this.v == null) {
         this.v = new WebViewProvider(this);
      }

      return this.v;
   }

   public boolean onInterceptTouchEvent(MotionEvent e) {
      if (!this.H) {
         return super.onInterceptTouchEvent(e);
      } else {
         boolean handle = super.onInterceptTouchEvent(e);
         if (!handle) {
            this.onTouchEvent(e);
            if (e.getAction() == 0) {
               this.G = true;
            }
         }

         return handle;
      }
   }

   public boolean onTouchEvent(MotionEvent ev) {
      if (this.H()) {
         return false;
      } else if (this.G) {
         this.G = false;
         return true;
      } else {
         boolean superDoSomething = super.onTouchEvent(ev);
         if (this.t == null) {
            this.t = VelocityTracker.obtain();
         }

         this.t.addMovement(ev);
         switch (ev.getAction()) {
            case 0:
               this.p = SystemClock.uptimeMillis();
               this.M();
               break;
            case 1:
            case 3:
               this.N();
            case 2:
         }

         return superDoSomething;
      }
   }

   private void M() {
      if (!this.isFocused()) {
         this.requestFocus();
      }
   }

   private void N() {
      if (this.g()) {
         int tap = ViewConfiguration.getTapTimeout();
         long now = SystemClock.uptimeMillis();
         long gap = now - this.p;
         if (gap <= (long) tap) {
            this.a(11, null, null);
         }
      }

      if (this.f()) {
         this.O();
      }

   }

   private boolean O() {
      VelocityTracker velocityTracker = this.t;
      velocityTracker.computeCurrentVelocity(1000);
      int velocityX = (int) (velocityTracker.getXVelocity() / a);
      int velocityY = (int) (velocityTracker.getYVelocity() / a);
      if (this.t != null) {
         this.t.recycle();
         this.t = null;
      }

      return this.c(velocityX, velocityY);
   }

   private boolean c(int velocityX, int velocityY) {
      int absY = velocityY < 0 ? -velocityY : velocityY;
      int absX = velocityX < 0 ? -velocityX : velocityX;
      int rate = 1000;
      if (velocityX > rate && absX > absY) {
         this.W();
      } else if (velocityX < -rate && absX > absY) {
         this.V();
      }

      if (velocityY > rate && absY > absX) {
         this.l();
      } else if (velocityY < -rate && absY > absX) {
         this.k();
      }

      return false;
   }

   public boolean a(int keyCode) {
      JSONObject json = new JSONObject();

      try {
         json.put("keyCode", keyCode);
      } catch (Exception var4) {
      }

      this.a(22, json, null);
      return true;
   }

   protected void b(View v, boolean hasFocus) {
      if (v == this) {
         JSONObject json = new JSONObject();

         try {
            json.put("focused", hasFocus);
         } catch (Exception var5) {
         }

         this.a(23, json, null);
      }
   }

   public boolean b(int inType) {
      return inType == this.f;
   }

   public void a(boolean flag) {
      this.k.b(flag);
   }

   public void b(boolean flag) {
      this.k.c(flag);
   }

   protected void a(com.uzmap.pkg.uzcore.a view, String url, boolean networkUrl) {
      if (!this.H()) {
         this.A = false;
         if (view.h() && networkUrl) {
            l.a1(-1, "加载中", null, false);
         }

         if (view.copyBackForwardList().getSize() > 0) {
            this.j.a();
         }

         this.u.clear();
         if (this.b(0)) {
            l.a1(view, url);
         }
      }
   }

   protected void a(com.uzmap.pkg.uzcore.a view, String url) {
      if (!this.A && !this.H()) {
         this.A = true;
         this.l.b(view, url);
      }
   }

   protected void a(com.uzmap.pkg.uzcore.a view, int newProgress) {
      if (!this.A && !this.H()) {
         l.a1(view, newProgress);
         if (100 == newProgress) {
            this.A = true;
            String url = view.getOriginalUrl();
            this.l.b(view, url);
         }

      }
   }

   public com.uzmap.pkg.uzcore.uzmodule.b c() {
      return this.j;
   }

   public int d() {
      return com.uzmap.pkg.uzcore.f.b(this.i).g(true);
   }

   public int e() {
      return com.uzmap.pkg.uzcore.f.b(this.i).f(true);
   }

   public boolean f() {
      return this.y;
   }

   public void c(boolean flag) {
      this.y = flag;
   }

   public boolean g() {
      return this.z;
   }

   public void d(boolean flag) {
      this.z = flag;
   }

   public void e(boolean flag) {
      this.x = flag;
   }

   public boolean h() {
      return this.x;
   }

   public int i() {
      return UZCoreUtil.pixToDip(this.getWidth());
   }

   public int j() {
      return UZCoreUtil.pixToDip(this.getHeight());
   }

   public void a(com.uzmap.pkg.uzcore.uzmodule.aa.d args) {
      if (!args.b()) {
         this.u.put(args.a, args);
         switch (args.a) {
            case 4:
               this.T();
               break;
            case 5:
               this.R();
               break;
            case 6:
               this.d(args.a("threshold"));
               break;
            case 7:
               if (this.B && this.z() <= 4) {
                  this.a(7, null, null);
               }
               break;
            case 8:
               if (!this.b(0)) {
                  return;
               }

               this.l.d(true);
               break;
            case 9:
               if (!this.b(0)) {
                  return;
               }
            case 10:
            case 13:
            case 20:
            case 21:
            case 22:
            default:
               break;
            case 11:
               this.d(true);
               break;
            case 12:
               this.P();
               break;
            case 14:
            case 15:
            case 16:
            case 17:
               this.c(true);
               break;
            case 18:
               com.uzmap.pkg.uzcore.f.b(this.i).c(18);
               break;
            case 19:
               if (!c) {
                  com.uzmap.pkg.uzcore.f.b(this.i).c(19);
                  c = true;
               }
               break;
            case 23:
               if (this.C && this.z() <= 3) {
                  this.b(this, this.isFocused());
               }
         }

      }
   }

   public void c(int event) {
      com.uzmap.pkg.uzcore.uzmodule.aa.d listener = this.u.remove(event);
      if (listener != null) {
         switch (event) {
            case 4:
               this.U();
               break;
            case 5:
               this.S();
            case 6:
            case 7:
            case 9:
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            default:
               break;
            case 8:
               if (this.b(0)) {
                  this.l.d(false);
               }
               break;
            case 11:
               this.d(false);
               break;
            case 12:
               this.Q();
         }
      }

   }

   protected boolean a(int event, JSONObject jsonValue, String value) {
      com.uzmap.pkg.uzcore.uzmodule.aa.d listener = null;
      if (!this.u.isEmpty()) {
         listener = this.u.get(event);
      }

      if (listener != null) {
         if (jsonValue != null) {
            listener.success(jsonValue, false);
         } else {
            listener.success(value, false, false);
         }

         return true;
      } else {
         if (event == 7) {
            this.B = true;
         }

         if (event == 23) {
            this.C = true;
         }

         return false;
      }
   }

   private void P() {
      if (this.o == null) {
         this.o = new com.uzmap.pkg.ui.a.f.a() {
            public void a() {
               a.this.a(12, null, null);
            }
         };
         com.uzmap.pkg.ui.a.f.a(this.i).a(this.o);
      }
   }

   private void Q() {
      if (this.o != null) {
         com.uzmap.pkg.ui.a.f.a(this.i).b(this.o);
         this.o = null;
      }

   }

   private void R() {
      if (this.r == null) {
         this.r = new Battery.a() {
            public void a(boolean low, JSONObject json) {
               if (!low) {
                  a.this.a(5, json, "");
               }

            }
         };
      }

      Battery.a(this.i).a(this.r);
   }

   private void S() {
      if (this.r != null) {
         Battery.a(this.i).b(this.r);
         this.r = null;
      }

   }

   private void T() {
      if (this.s == null) {
         this.s = new Battery.a() {
            public void a(boolean low, JSONObject json) {
               if (low) {
                  a.this.a(4, json, "");
               }

            }
         };
      }

      Battery.a(this.i).a(this.s);
   }

   private void U() {
      if (this.s != null) {
         Battery.a(this.i).b(this.s);
         this.s = null;
      }

   }

   private void V() {
      this.a(14, null, null);
   }

   private void W() {
      this.a(15, null, null);
   }

   protected void k() {
      this.a(16, null, null);
   }

   protected void l() {
      this.a(17, null, null);
   }

   public void a(View child, RelativeLayout.LayoutParams parms, String fixedFrame, boolean fixed, boolean needVerScroll) {
      if (!this.H()) {
         l.a1(child, parms, fixedFrame, fixed, needVerScroll);
      }
   }

   public void a(View child) {
      if (!this.H()) {
         l.a1(child);
      }
   }

   public void a(String url) {
      if (!TextUtils.isEmpty(url)) {
         this.b(url.startsWith("http"));

         this.loadUrl(url);
      }
   }

   public void b(String data) {
      if (!TextUtils.isEmpty(data)) {
         this.loadData(data, "text/html", "UTF-8");
      }
   }

   public int m() {
      return this.f;
   }

   public m n() {
      return this.l;
   }

   public boolean o() {
      return this.f != 0;
   }

   public String p() {
      return this.H() ? null : this.l.m();
   }

   public String q() {
      return this.H() ? "" : this.g;
   }

   public void c(String url) {
      this.g = url;
   }

   public com.uzmap.pkg.uzcore.uzmodule.d r() {
      return this.H() ? null : this.l.p();
   }

   public void a(com.uzmap.pkg.uzcore.uzmodule.d pageParam) {
      this.h = pageParam;
   }

   public com.uzmap.pkg.uzcore.uzmodule.d s() {
      if (this.H()) {
         return null;
      } else {
         return this.h != null ? this.h : new com.uzmap.pkg.uzcore.uzmodule.d(null);
      }
   }

   public void a(com.uzmap.pkg.uzcore.external.h element) {
      this.w = element;
   }

   public com.uzmap.pkg.uzcore.external.h t() {
      return this.w;
   }

   public ApiConfig u() {
      return this.H() ? null : this.l.l();
   }

   public UZWidgetInfo getWidgetInfo() {
      return this.H() ? null : this.l.l().i();
   }

   public void a(UZModuleContext args) {
      g parent = (g) this.getParent();
      if (parent != null) {
         parent.a(args);
      }

   }

   public void v() {
      g parent = (g) this.getParent();
      if (parent != null) {
         parent.e();
      }

   }

   public void w() {
      g parent = (g) this.getParent();
      if (parent != null) {
         parent.f();
      }

   }

   public boolean f(boolean bottom) {
      return !this.H() && super.pageDown(bottom);
   }

   public boolean g(boolean top) {
      return !this.H() && super.pageUp(top);
   }

   public boolean a(int x, int y) {
      if (this.H()) {
         return false;
      } else {
         super.scrollBy(x, y);
         this.invalidate();
         return true;
      }
   }

   public boolean b(int x, int y) {
      if (this.H()) {
         return false;
      } else {
         super.scrollTo(x, y);
         this.invalidate();
         return true;
      }
   }

   public boolean a(Runnable action, int delayMillis) {
      if (this.isShown()) {
         this.postDelayed(action, delayMillis);
      } else {
         com.uzmap.pkg.uzcore.c.a(action, delayMillis);
      }

      return true;
   }

   protected void d(int threshold) {
      this.D = (int) ((float) threshold * a);
   }

   public void x() {
      if (com.uzmap.pkg.uzcore.external.l.a >= 11) {
         if (this.b(0) && this.getLayerType() != 0) {
            this.setLayerType(0, null);
         }

      }
   }

   public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
      if (this.h()) {
         this.l.o();
      }

      this.n.a(this.i, url, userAgent, contentDisposition, mimetype, contentLength);
   }

   protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
      super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
      this.F = clampedY && scrollY > 0;
      if (clampedY && scrollY > 0) {
         this.y();
      } else {
         int contentHeight = (int) ((float) this.getContentHeight() * a);
         int threshold = contentHeight - this.D;
         int scrollHeight = scrollY + this.getHeight();
         boolean isScrollDwon = scrollY - this.E > b || scrollHeight == contentHeight;
         this.E = scrollY;
         if (scrollHeight >= threshold && isScrollDwon) {
            this.y();
         }

      }
   }

   protected void y() {
      long timemap = SystemClock.uptimeMillis();
      long step = timemap - this.q;
      this.q = timemap;
      if (step > 260L) {
         this.a(6, null, "");
      }

   }

   public void destroy() {
      if (!this.H()) {
         this.I();
         this.Q();
         this.S();
         this.U();
         ViewGroup parent = (ViewGroup) this.getParent();
         if (parent != null) {
            parent.removeView(this);
         }

         this.j.a(this);
         if (com.uzmap.pkg.uzcore.external.l.a >= 18) {
            this.loadUrl("about:blank");
         }

         if (this.f != 0) {
            j(false);
         }

         this.u.clear();
         this.j = null;
         this.l = null;
         this.k = null;
         this.i = null;
         super.destroy();
      }
   }

   public String toString() {
      return "frame[" + this.d + "]" + "@" + Integer.toHexString(this.hashCode());
   }
}
