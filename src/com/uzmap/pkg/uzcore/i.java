package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.internalmodule.UZConstant;

import java.util.Iterator;
import java.util.List;

public class i extends com.uzmap.pkg.uzcore.external.bb.g {
   private int a;
   private boolean b;
   private final ApiConfig c;
   private x d;
   private final Activity e;
   private final l f;
   private y g;
   private final Runnable h = new Runnable() {
      public void run() {
         i.this.r();
      }
   };

   public i(Context context, ApiConfig inWidget) {
      super(context, null);
      this.c = inWidget;
      this.e = (Activity) context;
      this.f = new l();
      this.setFadingEdgeLength(0);
      this.setBackgroundColor(com.uzmap.pkg.uzcore.external.l.c);
   }

   public void a() {
      m rootWin = new m(this.e, this);
      rootWin.c(true);
      rootWin.b(0);
      com.uzmap.pkg.uzcore.external.bb.g.a parm = com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d, 0, 0);
      rootWin.setLayoutParams(parm);
      this.b(rootWin);
      rootWin.a((UZModuleContext) null);
      this.d = rootWin;
   }

   public boolean a(int flag) {
      return (this.a & flag) != 0;
   }

   public void b(int flag) {
      this.a |= flag;
   }

   public void b() {
      this.a &= 0;
   }

   public String c() {
      return this.c.r;
   }

   public void d() {
      this.d.a(this.c.z);
   }

   protected void onAnimationEnd() {
      super.onAnimationEnd();
      if (!this.post(this.h)) {
         this.r();
      }

   }

   private void r() {
      if (this.g != null) {
         this.g.a();
         this.a((y) null);
      }

   }

   public void a(y listener) {
      this.g = listener;
   }

   public void a(x preWin, com.uzmap.pkg.uzcore.a view, com.uzmap.pkg.uzcore.uzmodule.aa.s args) {
      UZCoreUtil.hideSoftKeyboard(this.e, view);
      com.uzmap.pkg.uzcore.f.d();
      if (!this.c(preWin, args)) {
         x newWin = new m(this.e, this);
         newWin.c(false);
         newWin.b(0);
         newWin.g(args.a);
         newWin.h(args.b);
         newWin.a(args.c);
         newWin.i(args.M);
         if (args.h()) {
            com.uzmap.pkg.uzcore.external.l.a((m) newWin, args.a(this.j()));
         }

         newWin.d(1);
         com.uzmap.pkg.uzcore.external.bb.g.a parm = com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d, 0, 0);
         ((m) newWin).setLayoutParams(parm);
         newWin.a(args);
         newWin.f(4);
         newWin.e(args.J);
         newWin.f(args.I);
         if (args.E) {
            ((m) newWin).a1(args.E);
         }

         this.b(newWin);
         String url = args.z;
         newWin.a(url);
         if (url.startsWith("http")) {
            this.b(newWin, url);
         }

         newWin.d(preWin.m());
      }
   }

   public void a(x trigger, com.uzmap.pkg.uzcore.uzmodule.aa.s moduleArgs) {
      String winName = moduleArgs != null ? moduleArgs.y : "";
      if ("root".equals(winName)) {
         com.uzmap.pkg.uzcore.f.b(this.e).a((com.uzmap.pkg.uzcore.uzmodule.aa.r) null);
      } else {
         x target = null;
         if (moduleArgs != null && !TextUtils.isEmpty(moduleArgs.y)) {
            target = this.a(moduleArgs.y);
         } else {
            target = trigger;
         }

         if (target != null) {
            if (this.d != target) {
               this.e(target);
            } else if (target.k()) {
               com.uzmap.pkg.uzcore.f.b(this.e).a((com.uzmap.pkg.uzcore.uzmodule.aa.r) null);
            } else {
               com.uzmap.pkg.uzcore.f.d();
               int oldAnim = target.s();
               int oldSubAnim = target.t();
               int newAnim = -1;
               int newSubAnim = -1;
               long animDuration = target.r();
               if (moduleArgs != null) {
                  newAnim = moduleArgs.a;
                  newSubAnim = moduleArgs.b;
                  animDuration = moduleArgs.c;
               }

               if (newAnim == -1) {
                  newAnim = oldAnim;
               }

               if (newSubAnim == -1) {
                  newSubAnim = w.a(oldSubAnim, newSubAnim);
               }

               target.g(newAnim);
               target.h(newSubAnim);
               target.a(animDuration);
               this.a(target, 1, moduleArgs);
            }
         }
      }
   }

   public void b(x window, com.uzmap.pkg.uzcore.uzmodule.aa.s moduleArgs) {
      com.uzmap.pkg.uzcore.f.d();
      int oldAnim = window.s();
      int oldSubAnim = window.t();
      int newAnim = -1;
      int newSubAnim = -1;
      long animDuration = window.r();
      if (moduleArgs != null) {
         newAnim = moduleArgs.a;
         newSubAnim = moduleArgs.b;
         animDuration = moduleArgs.c;
      }

      if (newAnim == -1) {
         newAnim = oldAnim;
      }

      if (newSubAnim == -1) {
         newSubAnim = w.a(oldSubAnim, newSubAnim);
      }

      window.g(newAnim);
      window.h(newSubAnim);
      window.a(animDuration);
      this.a(window, 2, moduleArgs);
   }

   public void a(x preWin, com.uzmap.pkg.uzcore.a view, com.uzmap.pkg.uzcore.uzmodule.aa.p args) {
      com.uzmap.pkg.uzcore.f.d();
      x slidLayout = new o(this.e, this, args);
      slidLayout.c(false);
      slidLayout.b(1);
      slidLayout.g(args.a);
      slidLayout.h(args.b);
      slidLayout.a(args.c);
      slidLayout.i(args.M);
      slidLayout.d(1);
      com.uzmap.pkg.uzcore.external.bb.g.a parm = com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d, 0, 0);
      ((o) slidLayout).setLayoutParams(parm);
      slidLayout.a(args);
      slidLayout.e(args.J);
      slidLayout.f(args.I);
      slidLayout.d(preWin.m());
      ((o) slidLayout).k(args.e);
      this.b(slidLayout);
      String[] urls = new String[]{args.h.z, args.g.z};
      slidLayout.a(urls);
      this.a(slidLayout, 0, null);
   }

   public void a(x preWin, com.uzmap.pkg.uzcore.a view, com.uzmap.pkg.uzcore.uzmodule.aa.k args) {
      x slidLayout = this.f.a("slidLayout");
      if (slidLayout != null) {
         ((o) slidLayout).j(UZConstant.mapInt(args.optString("type"), 0));
      }
   }

   public void a(x preWin, com.uzmap.pkg.uzcore.a view) {
      x slidLayout = this.f.a("slidLayout");
      if (slidLayout != null) {
         ((o) slidLayout).o();
      }
   }

   public void a(x preWin, com.uzmap.pkg.uzcore.a view, boolean lock) {
      x slidLayout = this.f.a("slidLayout");
      if (slidLayout != null) {
         ((o) slidLayout).a(lock);
      }
   }

   public void a(x window, String url) {
   }

   public void b(final x window, String url) {
      if (window.a(1)) {
         window.g();
         this.b();
      } else {
         if (window.c(1)) {
            int delay = window.u();
            if (delay > 0) {
               Runnable action = new Runnable() {
                  public void run() {
                     i.this.a(window, 0, null);
                  }
               };
               com.uzmap.pkg.uzcore.c.a(action, delay);
            } else {
               this.a(window, 0, null);
            }

            window.g();
         } else if (this.a(1)) {
            com.uzmap.pkg.uzcore.f.b(this.e).a(this);
         } else if (this.a(2)) {
            com.uzmap.pkg.uzcore.f.b(this.e).t();
         }

         this.b();
      }
   }

   public void a(com.uzmap.pkg.uzcore.uzmodule.aa.d event) {
      this.f.a(event);
   }

   public boolean e() {
      if (this.f()) {
         this.d.i();
         return true;
      } else if (!this.d.k()) {
         this.a(this.d, (com.uzmap.pkg.uzcore.uzmodule.aa.s) null);
         return true;
      } else {
         return false;
      }
   }

   public boolean f() {
      return this.d.h();
   }

   protected void g() {
      this.f.a();
   }

   protected void h() {
      this.f.b();
   }

   protected void a(String callingPackage, Intent intent) {
      this.f.a(callingPackage, intent);
   }

   protected void a(boolean connected, String type) {
      this.f.a(connected, type);
   }

   protected final void a(Intent intent) {
      this.f.a(intent);
   }

   protected final void a(boolean longPress) {
      this.f.a(longPress);
   }

   public void a(boolean success, com.uzmap.pkg.uzkit.a.d lastPackage) {
      this.f.a(success, lastPackage);
   }

   public final void a(com.uzmap.pkg.uzcore.uzmodule.aa.s args) {
      this.a(this.d, null, args);
   }

   protected void c(int keyCode) {
      this.d.e(keyCode);
   }

   public boolean i() {
      return this.d.n();
   }

   public ApiConfig j() {
      return this.c;
   }

   public long k() {
      return this.c.e();
   }

   public int l() {
      return this.c.c();
   }

   public int m() {
      return this.c.d();
   }

   public com.uzmap.pkg.uzcore.uzmodule.d n() {
      com.uzmap.pkg.uzcore.uzmodule.d param = null;
      com.uzmap.pkg.uzcore.uzmodule.aa.r moduleArgs = this.c.U;
      param = moduleArgs != null ? moduleArgs.e : new com.uzmap.pkg.uzcore.uzmodule.d(null);
      param = param != null ? param : new com.uzmap.pkg.uzcore.uzmodule.d(null);
      return param;
   }

   public void b(boolean flag) {
      this.b = flag;
   }

   public boolean o() {
      return this.b;
   }

   public void a(WebView view, com.uzmap.pkg.uzcore.uzmodule.aa.o moduleArgs) {
      x window = this.f.a(moduleArgs.a);
      if (window != null) {
         window.a(view, moduleArgs);
      }

   }

   public void a(com.uzmap.pkg.uzcore.uzmodule.aa.o moduleArgs) {
      x window = this.f.a(moduleArgs.a);
      if (window == null) {
         window = this.d;
      }

      window.a(null, moduleArgs);
   }

   private void a(final x operateWin, int operate, com.uzmap.pkg.uzcore.uzmodule.aa.s moduleArgs) {
      int animType = operateWin.s();
      int animSubType = operateWin.t();
      long duration = operateWin.r();
      v animPair = w.a(animType, animSubType, duration);
      final x showWin;
      switch (operate) {
         case 0:
            showWin = this.d;
            if (operateWin == this.d) {
               com.uzmap.pkg.uzcore.f.e();
               return;
            }

            operateWin.a(new y() {
               public void a() {
                  operateWin.b();
                  operateWin.setAnimation(null);
                  operateWin.e();
                  i.this.d = operateWin;
                  showWin.setAnimation(null);
                  showWin.f();
                  com.uzmap.pkg.uzcore.f.e();
               }
            });
            if (animPair.b()) {
               showWin.bringToFront();
            }

            operateWin.startAnimation(animPair.a);
            operateWin.f(0);
            showWin.startAnimation(animPair.b);
            showWin.f(8);
            break;
         case 1:
            showWin = this.f.c(operateWin);
            if (showWin == null) {
               com.uzmap.pkg.uzcore.f.e();
               return;
            }

            showWin.a(new y() {
               public void a() {
                  showWin.setAnimation(null);
                  showWin.e();
                  i.this.d = showWin;
                  i.this.e(operateWin);
                  com.uzmap.pkg.uzcore.f.e();
               }
            });
            if (animPair.b()) {
               operateWin.bringToFront();
            }

            showWin.startAnimation(animPair.a);
            showWin.f(0);
            operateWin.startAnimation(animPair.b);
            operateWin.f(4);
            break;
         case 2:
            String toWinName = moduleArgs.y;
            x tmpWin = this.a(toWinName);
            showWin = this.d(tmpWin);
            if (showWin == null) {
               com.uzmap.pkg.uzcore.f.e();
               return;
            }

            x hiddenWin = this.d;
            showWin.a(new y() {
               public void a() {
                  showWin.setAnimation(null);
                  showWin.e();
                  i.this.d = showWin;
                  List<x> closelist = i.this.f.e(showWin);
                  if (closelist != null) {
                     Iterator var3 = closelist.iterator();

                     while (var3.hasNext()) {
                        x operateWin = (x) var3.next();
                        i.this.e(operateWin);
                     }

                     closelist.clear();
                  }

                  com.uzmap.pkg.uzcore.f.e();
               }
            });
            if (animPair.b()) {
               hiddenWin.bringToFront();
            }

            showWin.startAnimation(animPair.a);
            showWin.f(0);
            hiddenWin.startAnimation(animPair.b);
            hiddenWin.f(8);
      }

      this.invalidate();
   }

   private boolean c(x preWin, com.uzmap.pkg.uzcore.uzmodule.aa.s context) {
      x window = this.a(context.y);
      if (window != null) {
         window.g(context.a);
         window.h(context.b);
         window.a(context.c);
         window.i(context.M);
         if (context.H != null && !context.H.a()) {
            window.a(context.H);
         }

         this.c(window);
         this.b(window);
         String url = context.z;
         String curUrl = window.j();
         if (!TextUtils.isEmpty(url) && !url.equals(curUrl) || context.L) {
            url = !TextUtils.isEmpty(url) ? url : curUrl;
            window.a(url);
         }

         this.a(window, 0, null);
         window.d(preWin.m());
         return true;
      } else {
         return false;
      }
   }

   protected x a(String winName) {
      return TextUtils.isEmpty(winName) ? null : this.f.a(winName);
   }

   private void b(x winImpl) {
      this.addView((View) winImpl);
      this.f.a(winImpl);
   }

   private void c(x winImpl) {
      this.f.d(winImpl);
      this.removeView((View) winImpl);
   }

   private x d(x win) {
      return win == null ? null : this.f.b(win);
   }

   private void e(x target) {
      if (((View) target).isShown()) {
         target.f(4);
      }

      this.f.d(target);
      target.w();
   }

   protected boolean a(x target) {
      return this.d == target;
   }

   public void p() {
      this.f.c();
      com.uzmap.pkg.uzcore.f.e();
   }

   public Bitmap q() {
      return com.uzmap.pkg.uzcore.external.l.a(this);
   }

   public String toString() {
      return "widget@" + Integer.toHexString(this.hashCode());
   }
}
