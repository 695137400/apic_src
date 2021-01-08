//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.webkit.WebView;
import com.uzmap.pkg.uzcore.external.bb.i;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.aa.e;
import com.uzmap.pkg.uzcore.uzmodule.aa.f;
import com.uzmap.pkg.uzcore.uzmodule.aa.k;
import com.uzmap.pkg.uzcore.uzmodule.aa.o;
import com.uzmap.pkg.uzcore.uzmodule.aa.p;
import com.uzmap.pkg.uzcore.uzmodule.aa.q;
import com.uzmap.pkg.uzcore.uzmodule.aa.s;
import com.uzmap.pkg.uzkit.a.d;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class m extends i implements x {
   static int a = 0;
   private int b;
   private int c;
   private long d;
   private y e;
   private int f;
   private boolean g;
   private boolean h;
   private int i;
   private int j;
   private String k;
   private Activity l;
   private com.uzmap.pkg.uzcore.external.i m;
   private a n;
   private com.uzmap.pkg.uzcore.i o;
   private WeakHashMap<String, a> p;
   private WeakHashMap<String, h> q;
   private boolean r;
   private String s;
   private View t = null;
   private final Runnable u = new Runnable() {
      public void run() {
         m.this.x();
      }
   };

   protected m(Context context, com.uzmap.pkg.uzcore.i inWgt) {
      super(context, null);
      this.l = (Activity) context;
      this.o = inWgt;
      this.p = new WeakHashMap(3);
      this.q = new WeakHashMap(3);
      this.setFocusable(false);
      com.uzmap.pkg.uzcore.external.l.a(this, inWgt.j().a());
      ++a;
   }

   public void a(UZModuleContext args) {
      s wargs = (s) args;
      boolean alone = wargs != null && wargs.D;
      this.n = new a(0, alone, this.l, this);
      this.n.d("main");
      boolean show = wargs != null && wargs.K;
      this.n.e(show);
      g bounceView = new g(this.l, null);
      bounceView.a(this.n);
      bounceView.a(this.l().E);
      if (wargs != null) {
         if (!wargs.isNull("bounces")) {
            bounceView.a(wargs.B);
         }

         this.n.i(wargs.F);
      }

      this.addView(bounceView, com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d));
      this.n.a();
      if (wargs != null) {
         this.c(wargs.y);
         this.a(wargs.H);
      }

      this.t = this.n;
      if (wargs == null) {
         com.uzmap.pkg.uzkit.a.aa.a.a().a(this.k);
      }

   }

   protected void onVisibilityChanged(View v, int visibility) {
      super.onVisibilityChanged(v, visibility);
      if (v == this) {
         if (visibility != 0) {
            UZCoreUtil.hideSoftKeyboard(this.l, v);
         }

      }
   }

   public boolean a(int type) {
      return this.j == type;
   }

   public void b(int type) {
      this.j = type;
   }

   protected void onAnimationStart() {
      super.onAnimationStart();
      this.g(true);
   }

   protected void onAnimationEnd() {
      super.onAnimationEnd();
      this.g(false);
      if (!this.post(this.u)) {
         this.x();
      }

   }

   private void x() {
      if (this.e != null) {
         this.e.a();
         this.a((y) null);
      }

   }

   private void g(boolean flag) {
      this.r = flag;
   }

   public boolean a1() {
      return this.r;
   }

   public void a(y listener) {
      this.e = listener;
   }

   public boolean c(int flag) {
      return (this.f & flag) != 0;
   }

   public void d(int flag) {
      this.f |= flag;
   }

   public void b() {
      this.f &= 0;
   }

   public void a1(boolean flag) {
      this.n.a(flag);
   }

   public void a1(String frameName) {
      a frame = null;
      if (TextUtils.isEmpty(frameName)) {
         frame = this.n;
      } else {
         frame = this.b(frameName);
      }

      if (frame != null) {
         frame.requestFocus();
      }

   }

   protected a b(String frameName) {
      if (TextUtils.isEmpty(frameName)) {
         return null;
      } else {
         a child = this.p.get(frameName);
         if (child == null) {
            child = this.f(frameName);
         }

         return child;
      }
   }

   private a e(String frameName) {
      return TextUtils.isEmpty(frameName) ? null : this.p.get(frameName);
   }

   private a f(String frameName) {
      if (TextUtils.isEmpty(frameName)) {
         return null;
      } else {
         Iterator var3 = this.q.values().iterator();

         while (var3.hasNext()) {
            h group = (h) var3.next();
            a child = group.b(frameName);
            if (child != null) {
               return child;
            }
         }

         return null;
      }
   }

   private h a1(String groupName, String frameName) {
      if (!TextUtils.isEmpty(groupName)) {
         return this.q.get(groupName);
      } else {
         Iterator var4 = this.q.values().iterator();

         while (var4.hasNext()) {
            h group = (h) var4.next();
            a child = group.b(frameName);
            if (child != null) {
               return group;
            }
         }

         return null;
      }
   }

   public void a1(final View child, ViewGroup.LayoutParams parms, String fixedFrame, boolean fixed, boolean needVerScroll) {
      if (child != null) {
         if (parms != null) {
            com.uzmap.pkg.uzcore.external.l.a(parms);
         }

         if (TextUtils.isEmpty(fixedFrame)) {
            if (fixed) {
               if (parms != null) {
                  child.setLayoutParams(parms);
                  this.addView(child);
               }
            } else {
               g parent = (g) this.n.getParent();
               if (parms != null) {
                  parms = com.uzmap.pkg.uzcore.external.l.c(parms);
                  child.setLayoutParams(parms);
               }

               parent.a(child, fixed, needVerScroll);
            }
         } else {
            a frame = this.b(fixedFrame);
            if (frame == null) {
               return;
            }

            g parent = (g) frame.getParent();
            if (parms != null) {
               if (fixed) {
                  parms = com.uzmap.pkg.uzcore.external.l.b(parms);
               } else {
                  parms = com.uzmap.pkg.uzcore.external.l.c(parms);
               }

               child.setLayoutParams(parms);
            }

            parent.a(child, fixed, needVerScroll);
         }

         Animation addAnim = child.getAnimation();
         if (addAnim != null) {
            addAnim.setAnimationListener(new AnimationListener() {
               public void onAnimationStart(Animation animation) {
               }

               public void onAnimationRepeat(Animation animation) {
               }

               public void onAnimationEnd(Animation animation) {
                  Runnable action = new Runnable() {
                     public void run() {
                        child.setAnimation(null);
                     }
                  };
                  m.this.post(action);
               }
            });
            addAnim.start();
            this.invalidate();
         }

      }
   }

   public void a1(final View child) {
      if (child != null) {
         Animation removeAnim = child.getAnimation();
         if (removeAnim != null) {
            removeAnim.setAnimationListener(new AnimationListener() {
               public void onAnimationStart(Animation animation) {
               }

               public void onAnimationRepeat(Animation animation) {
               }

               public void onAnimationEnd(Animation animation) {
                  Runnable action = new Runnable() {
                     public void run() {
                        m.this.b(child);
                     }
                  };
                  m.this.post(action);
               }
            });
            removeAnim.start();
            this.invalidate();
         } else {
            this.b(child);
         }
      }
   }

   private void b(View child) {
      ViewGroup parent = (ViewGroup) child.getParent();
      if (parent != null) {
         parent.removeView(child);
      } else {
         this.removeView(child);
      }

      child.setAnimation(null);
   }

   public void a1(a view, s args) {
      this.o.a(this, view, args);
   }

   public void a1(a view, p args) {
      this.o.a(this, view, args);
   }

   public void a1(a view, k args) {
      this.o.a(this, view, args);
   }

   public void a1(a view) {
      this.o.a(this, view);
   }

   public void a1(a view, boolean lock) {
      this.o.a(this, view, lock);
   }

   public void b(a view, s args) {
      if (!args.isNull("bounces") && this.n != null) {
         g parent = (g) this.n.getParent();
         if (parent != null) {
            parent.a(args.B);
         }
      }

      if (args.h()) {
         com.uzmap.pkg.uzcore.external.l.a(this, args.a(this.l()));
      }

      if (!args.isNull("hScrollBarEnabled")) {
         this.e(args.J);
      }

      if (!args.isNull("vScrollBarEnabled")) {
         this.f(args.I);
      }

   }

   public void a1(s args) {
      this.o.a(this, args);
   }

   public void b(s args) {
      this.o.b(this, args);
   }

   public void a(WebView view, o args) {
      String name = args.a;
      if (!TextUtils.isEmpty(name) && !this.k.equals(name)) {
         this.o.a(view, args);
      } else {
         a target = null;
         String frameName = args.b;
         if (TextUtils.isEmpty(frameName)) {
            target = this.n;
         } else {
            target = this.e(frameName);
            if (target == null) {
               target = this.f(frameName);
            }
         }

         if (target != null) {
            target.e(args.c);
         }
      }

   }

   public void a1(WebView view, f moduleArgs) {
      if (!this.a1(moduleArgs)) {
         a frame = new a(1, false, this.l, this);
         frame.d(moduleArgs.y);
         frame.i(moduleArgs.F);
         frame.e(moduleArgs.K);
         frame.setHorizontalScrollBarEnabled(moduleArgs.J);
         frame.setVerticalScrollBarEnabled(moduleArgs.I);
         frame.a(moduleArgs.H);
         int winRight = this.getRight();
         int winBottom = this.getBottom();
         android.widget.RelativeLayout.LayoutParams rlp = moduleArgs.a(winRight, winBottom);
         g parent = new g(this.l, null);
         parent.setLayoutParams(rlp);
         parent.a(frame);
         if (!moduleArgs.isNull("bounces")) {
            parent.a(moduleArgs.B);
         } else {
            parent.a(this.l().E);
         }

         if (moduleArgs.g()) {
            parent.a(moduleArgs.a);
            parent.b(moduleArgs.b);
            parent.a(moduleArgs.c);
            parent.setVisibility(4);
         }

         if (moduleArgs.h()) {
            com.uzmap.pkg.uzcore.external.l.a(parent, moduleArgs.a(this.l()));
         }

         this.addView(parent);
         frame.a();
         if (moduleArgs.E) {
            frame.a(moduleArgs.E);
         }

         this.p.put(moduleArgs.y, frame);
         frame.a(moduleArgs.z);
      }
   }

   public void b(WebView view, final f moduleArgs) {
      a frame = null;
      String frameName = moduleArgs.optString("name");
      if (TextUtils.isEmpty(frameName)) {
         frame = (a) view;
         frameName = frame.B();
      } else {
         frame = this.e(frameName);
      }

      if (frame != null) {
         g parent = (g) frame.getParent();
         if (!parent.h() && !moduleArgs.g()) {
            this.a1(frame, moduleArgs);
         } else {
            int oldAnim = parent.i();
            int oldSubAnim = parent.j();
            int newAnim = -1;
            int newSubAnim = -1;
            long animDuration = parent.g();
            if (moduleArgs.g()) {
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

            parent.a(newAnim);
            parent.b(newSubAnim);
            parent.a(animDuration);
            com.uzmap.pkg.uzcore.a finalFrame = frame;
            parent.a(new y() {
               public void a() {
                  m.this.a1(finalFrame, moduleArgs);
               }
            });
            this.c(frame);
         }
      }
   }

   public void c(WebView view, f args) {
      a frame = null;
      String frameName = args.y;
      if (TextUtils.isEmpty(frameName)) {
         frame = (a) view;
      } else {
         frame = this.e(frameName);
      }

      if (frame != null) {
         g parent = (g) frame.getParent();
         if (!args.isNull("vScrollBarEnabled")) {
            frame.setVerticalScrollBarEnabled(args.I);
         }

         if (!args.isNull("hScrollBarEnabled")) {
            frame.setHorizontalScrollBarEnabled(args.J);
         }

         if (!args.isNull("bounces")) {
            parent.a(args.B);
         }

         if (args.h()) {
            com.uzmap.pkg.uzcore.external.l.a(parent, args.a(this.l()));
         }

         if (args.e()) {
            android.widget.RelativeLayout.LayoutParams oldlp = (android.widget.RelativeLayout.LayoutParams) parent.getLayoutParams();
            int winRight = this.getRight();
            int winBottom = this.getBottom();
            android.widget.RelativeLayout.LayoutParams newlp = args.a(oldlp, winRight, winBottom);
            parent.setLayoutParams(newlp);
            boolean outScreen = newlp.width * newlp.height == 0 || newlp.topMargin > winBottom || newlp.leftMargin > winRight;
            if (outScreen) {
               parent.setVisibility(8);
            } else {
               parent.setVisibility(0);
            }
         }

         if (!args.isNull("hidden")) {
            if (args.m) {
               parent.setVisibility(8);
               UZCoreUtil.hideSoftKeyboard(this.l, frame);
            } else if (!parent.isShown()) {
               parent.setVisibility(0);
            }
         }

      }
   }

   public void d(WebView view, f args) {
      String from = args.n;
      String to = args.o;
      a fromChild = this.e(from);
      if (fromChild != null) {
         View fromParent = (View) fromChild.getParent();
         if (TextUtils.isEmpty(to)) {
            this.bringChildToFront(fromParent);
         } else {
            a toChild = this.e(to);
            if (toChild != null) {
               View toParent = (View) toChild.getParent();
               int i = this.indexOfChild(toParent);
               if (i + 1 < this.getChildCount()) {
                  this.removeView(fromParent);
                  this.addView(fromParent, i + 1);
               }

            }
         }
      }
   }

   public void e(WebView view, f args) {
      String from = args.n;
      String to = args.o;
      a fromChild = this.e(from);
      if (fromChild != null) {
         View fromParent = (View) fromChild.getParent();
         if (TextUtils.isEmpty(to)) {
            this.removeView(fromParent);
            View mainParent = (View) this.n.getParent();
            int mi = this.indexOfChild(mainParent);
            this.addView(fromParent, mi + 1);
         } else {
            a toChild = this.e(to);
            if (toChild != null) {
               View toParent = (View) toChild.getParent();
               int i = this.indexOfChild(toParent);
               this.removeView(fromParent);
               this.addView(fromParent, i);
            }
         }
      }
   }

   public void a1(WebView view, e args) {
      a frame = this.e(args.y);
      frame = frame != null ? frame : (a) view;
      if (frame.b(1)) {
         g parent = (g) frame.getParent();
         parent.a(args);
         this.invalidate();
      }
   }

   private boolean a1(f moduleArgs) {
      a frame = this.e(moduleArgs.y);
      if (frame == null) {
         return false;
      } else {
         if (moduleArgs.H != null && !moduleArgs.H.a()) {
            frame.a(moduleArgs.H);
         }

         g parent = (g) frame.getParent();
         if (moduleArgs.empty()) {
            parent.bringToFront();
            if (!parent.isShown()) {
               parent.setVisibility(0);
            }

            return true;
         } else {
            if (moduleArgs.e()) {
               android.widget.RelativeLayout.LayoutParams oldlp = (android.widget.RelativeLayout.LayoutParams) parent.getLayoutParams();
               int winright = this.getRight();
               int winbottom = this.getBottom();
               android.widget.RelativeLayout.LayoutParams newlp = moduleArgs.a(oldlp, winright, winbottom);
               parent.setLayoutParams(newlp);
            }

            parent.bringToFront();
            if (!parent.isShown()) {
               parent.setVisibility(0);
            }

            if (moduleArgs.g()) {
               parent.a(moduleArgs.a);
               parent.b(moduleArgs.b);
               parent.a(moduleArgs.c);
            }

            this.c(frame);
            String url = moduleArgs.z;
            String curUrl = frame.q();
            curUrl = curUrl != null ? curUrl : "";
            if (!TextUtils.isEmpty(url) && !curUrl.equals(url) || moduleArgs.L) {
               url = !TextUtils.isEmpty(url) ? url : curUrl;
               if (!TextUtils.isEmpty(url)) {
                  frame.a(url);
               }
            }

            return true;
         }
      }
   }

   private boolean a1(com.uzmap.pkg.uzcore.uzmodule.aa.g args) {
      h frameGroup = this.q.get(args.p);
      if (frameGroup == null) {
         return false;
      } else {
         if (args.h()) {
            com.uzmap.pkg.uzcore.external.l.a(frameGroup, args.a(this.l()));
         }

         if (args.e()) {
            android.widget.RelativeLayout.LayoutParams oldlp = (android.widget.RelativeLayout.LayoutParams) frameGroup.getLayoutParams();
            int winRight = this.getRight();
            int winBottom = this.getBottom();
            android.widget.RelativeLayout.LayoutParams newlp = args.a(oldlp, winRight, winBottom);
            frameGroup.setLayoutParams(newlp);
            boolean outScreen = newlp.width * newlp.height == 0 || newlp.topMargin > winBottom || newlp.leftMargin > winRight;
            if (outScreen) {
               frameGroup.setVisibility(8);
            } else if (!frameGroup.isShown()) {
               frameGroup.setVisibility(0);
            }
         }

         if (!frameGroup.isShown()) {
            frameGroup.setVisibility(0);
         }

         return true;
      }
   }

   public void a1(WebView view, com.uzmap.pkg.uzcore.uzmodule.aa.g moduleArgs) {
      if (!this.a1(moduleArgs)) {
         h frameGroup = new h(this.l, this);
         frameGroup.a(moduleArgs.p);
         frameGroup.a(moduleArgs.u);
         int winRight = this.getRight();
         int winBottom = this.getBottom();
         android.widget.RelativeLayout.LayoutParams rlp = moduleArgs.a(winRight, winBottom);
         frameGroup.setLayoutParams(rlp);
         if (moduleArgs.h()) {
            com.uzmap.pkg.uzcore.external.l.a(frameGroup, moduleArgs.a(this.l()));
         }

         this.addView(frameGroup);
         frameGroup.a(moduleArgs);
         this.q.put(moduleArgs.p, frameGroup);
         frameGroup.a();
      }
   }

   public void b(WebView view, com.uzmap.pkg.uzcore.uzmodule.aa.g args) {
      h frameGroup = null;
      String groupName = args.p;
      if (TextUtils.isEmpty(groupName)) {
         a frame = (a) view;
         frameGroup = this.a1((String) null, frame.B());
      } else {
         frameGroup = this.q.get(args.p);
      }

      if (frameGroup != null) {
         if (args.h()) {
            com.uzmap.pkg.uzcore.external.l.a(frameGroup, args.a(this.l()));
         }

         if (args.e()) {
            android.widget.RelativeLayout.LayoutParams oldlp = (android.widget.RelativeLayout.LayoutParams) frameGroup.getLayoutParams();
            int winRight = this.getRight();
            int winBottom = this.getBottom();
            android.widget.RelativeLayout.LayoutParams newlp = args.a(oldlp, winRight, winBottom);
            frameGroup.setLayoutParams(newlp);
            boolean outScreen = newlp.width * newlp.height == 0 || newlp.topMargin > winBottom || newlp.leftMargin > winRight;
            if (outScreen) {
               frameGroup.setVisibility(8);
               UZCoreUtil.hideSoftKeyboard(this.l, frameGroup);
            } else {
               frameGroup.setVisibility(0);
            }
         }

         if (!args.isNull("hidden")) {
            if (args.m) {
               frameGroup.setVisibility(8);
               UZCoreUtil.hideSoftKeyboard(this.l, frameGroup);
            } else if (!frameGroup.isShown()) {
               frameGroup.setVisibility(0);
            }
         }

         if (!args.isNull("scrollEnabled")) {
            frameGroup.a(args.u);
         }

      }
   }

   public void c(WebView view, com.uzmap.pkg.uzcore.uzmodule.aa.g moduleArgs) {
      String groupName = moduleArgs.p;
      h group = this.a1(groupName, (String) null);
      if (group != null) {
         int index = moduleArgs.r;
         boolean smoothScroll = moduleArgs.q;
         boolean reload = moduleArgs.t;
         group.a(index, smoothScroll, reload);
      }

   }

   public void d(WebView view, com.uzmap.pkg.uzcore.uzmodule.aa.g moduleArgs) {
      String groupName = moduleArgs.p;
      h frameGroup = this.a1(groupName, (String) null);
      if (frameGroup != null) {
         UZCoreUtil.hideSoftKeyboard(this.l, frameGroup);
         this.removeView(frameGroup);
         this.q.remove(groupName);
         frameGroup.b();
      }

   }

   public void e(int keyCode) {
      if (this.n != null) {
         if (this.n.b(0)) {
            JSONObject json = new JSONObject();

            try {
               json.put("keyCode", keyCode);
            } catch (Exception var4) {
            }

            int event = 8;
            if (82 == keyCode) {
               event = 9;
            }

            this.n.a(event, json, "");
         }
      }
   }

   public void c() {
      if (this.n != null) {
         if (this.a1()) {
            this.g(false);
            this.clearAnimation();
            if (!this.post(this.u)) {
               this.x();
            }
         }

         this.a1(0, null, null);
         if (this.o.a(this)) {
            com.uzmap.pkg.uzkit.a.aa.a.a().b(this.k);
         }

      }
   }

   public void d() {
      if (this.n != null) {
         this.a1(1, null, null);
         if (this.o.a(this)) {
            com.uzmap.pkg.uzkit.a.aa.a.a().a(this.k);
         }

      }
   }

   public void a(String callingPackage, Intent intent) {
      if (this.n != null && intent != null) {
         Uri data = intent.getData();
         String extra;
         JSONObject json;
         if (data != null) {
            extra = data.getScheme();
            if ("rong".equals(extra)) {
               Object appParam = data.toString();
               json = new JSONObject();

               try {
                  json.put("iosUrl", "");
                  json.put("sourceAppId", callingPackage);
                  json.put("appParam", appParam);
               } catch (Exception var11) {
               }

               this.a1(19, json, null);
               return;
            }

            Set<String> paramnams = data.getQueryParameterNames();
            if (paramnams != null) {
               json = new JSONObject();
               Iterator var8 = paramnams.iterator();

               while (var8.hasNext()) {
                  String key = (String) var8.next();
                  String value = data.getQueryParameter(key);

                  try {
                     json.put(key, value);
                  } catch (Exception var13) {
                  }
               }

               json = new JSONObject();

               try {
                  json.put("iosUrl", "");
                  json.put("sourceAppId", callingPackage);
                  json.put("appParam", json);
                  if (data != null) {
                     json.put("data", data);
                  }
               } catch (Exception var12) {
               }

               this.a1(19, json, null);
               return;
            }
         }

         if (intent.hasExtra("api_arguments")) {
            extra = intent.getStringExtra("api_arguments");
            if (!TextUtils.isEmpty(extra)) {
               json = new JSONObject();

               try {
                  json = new JSONObject(extra);
               } catch (Exception var14) {
               }

               this.a1(18, json, null);
               return;
            }
         }

         if (intent.getExtras() != null) {
            extra = UZCoreUtil.parseAppParam(intent);
            Object appParam = extra;
            if (!TextUtils.isEmpty(extra)) {
               try {
                  appParam = new JSONObject(extra);
               } catch (Exception var16) {
               }
            }

            json = new JSONObject();

            try {
               json.put("iosUrl", "");
               json.put("sourceAppId", callingPackage);
               json.put("appParam", appParam);
               if (data != null) {
                  json.put("data", data);
               }
            } catch (Exception var15) {
            }

            this.a1(19, json, null);
         }

      }
   }

   public void a(boolean connected, String type) {
      String script = "if(window.api){api.connectionType='" + type + "'};";
      this.n.e(script);
      Iterator var5 = this.p.values().iterator();

      while (var5.hasNext()) {
         a frame = (a) var5.next();
         frame.e(script);
      }

      var5 = this.q.values().iterator();

      while (var5.hasNext()) {
         h group = (h) var5.next();
         group.c(script);
      }

      JSONObject json = new JSONObject();

      try {
         json.put("connectionType", type);
      } catch (Exception var6) {
      }

      this.a1(connected ? 2 : 3, json, null);
   }

   public void a(boolean success, d lastPackage) {
      JSONObject json = new JSONObject();
      String extra = lastPackage.d;
      if (extra != null) {
         Object o = extra;

         try {
            o = new JSONObject(extra);
         } catch (Exception var9) {
         }

         JSONArray value = new JSONArray();

         try {
            JSONObject extraJson = new JSONObject();
            extraJson.put("extra", o);
            value.put(extraJson);
            json.put("value", value);
         } catch (Exception var8) {
         }
      }

      this.a1(20, json, null);
   }

   public void a(Intent data) {
      if (data != null) {
         long id = data.getLongExtra("id", 0L);
         String mimeType = data.getStringExtra("mimeType");
         String url = data.getStringExtra("url");
         JSONObject json = new JSONObject();

         try {
            json.put("id", id);
            json.put("mimeType", mimeType);
            json.put("url", url);
         } catch (Exception var8) {
         }

         this.a1(21, json, null);
      }
   }

   public void b(boolean longPress) {
   }

   public void e() {
      if (this.n != null) {
         this.a1(7, null, null);
         if (com.uzmap.pkg.uzcore.d.v) {
            this.t.requestFocus();
         }

         com.uzmap.pkg.uzkit.a.aa.a.a().a(this.k);
      }
   }

   public void f() {
      if (this.n != null) {
         this.a1(10, null, null);
         com.uzmap.pkg.uzkit.a.aa.a.a().b(this.k);
      }
   }

   public void a(com.uzmap.pkg.uzcore.uzmodule.aa.d event) {
      Object value = event.c();
      JSONObject json = new JSONObject();

      try {
         json.put("value", value);
      } catch (Exception var5) {
      }

      this.a1(event.a, json, null);
   }

   protected void a1(int event, JSONObject jsonValue, String value) {
      this.n.a(event, jsonValue, value);
      Iterator var5 = this.p.values().iterator();

      while (var5.hasNext()) {
         a frame = (a) var5.next();
         frame.a(event, jsonValue, value);
      }

      var5 = this.q.values().iterator();

      while (var5.hasNext()) {
         h group = (h) var5.next();
         group.a(event, jsonValue, value);
      }

   }

   public void a1(WebView view, q moduleContext) {
      com.uzmap.pkg.uzcore.external.h element = new com.uzmap.pkg.uzcore.external.h(moduleContext.e);
      element.b = moduleContext.a;
      element.c = moduleContext.b;
      element.d = moduleContext.c;
      element.e = moduleContext.d;
      ((a) view).a(element);
   }

   public boolean a1(WebView view, int keyCode) {
      a target = (a) view;
      int direction = -1;
      boolean intersect = false;
      int targetbase;
      int mainbase;
      switch (keyCode) {
         case 19:
            direction = 33;
            targetbase = target.E();
            mainbase = this.n.E();
            intersect = targetbase > mainbase;
            break;
         case 20:
            direction = 130;
            targetbase = target.G();
            mainbase = this.n.G();
            intersect = targetbase < mainbase;
            break;
         case 21:
            direction = 17;
            targetbase = target.D();
            mainbase = this.n.D();
            intersect = targetbase > mainbase;
            break;
         case 22:
            direction = 66;
            targetbase = target.F();
            mainbase = this.n.F();
            intersect = targetbase < mainbase;
      }

      View nextFocus = this.focusSearch(target, direction);
      if (nextFocus != null && nextFocus instanceof a) {
         nextFocus.requestFocus();
         this.t = nextFocus;
         return true;
      } else {
         if (this.n != target) {
            if (intersect) {
               this.n.requestFocus();
               this.t = this.n;
               return true;
            }
         } else {
            com.uzmap.pkg.uzcore.external.h element = this.n.t();
            if (element != null) {
               Rect rect = new Rect(element.b, element.c, element.d, element.e);
               nextFocus = FocusFinder.getInstance().findNextFocusFromRect(this, rect, direction);
               if (nextFocus != null && nextFocus instanceof a) {
                  nextFocus.requestFocus();
                  this.t = nextFocus;
                  return true;
               }
            }
         }

         return false;
      }
   }

   public void a(String... content) {
      if (content != null && content.length != 0) {
         String url = content[0];
         if (url.startsWith("http")) {
            View parent = (View) this.n.getParent();
            parent.setBackgroundColor(-1);
         }

         this.n.a(url);
      }
   }

   public void g() {
      this.n.clearHistory();
   }

   public void bringToFront() {
      ViewGroup parent = (ViewGroup) this.getParent();
      if (parent != null) {
         int index = parent.indexOfChild(this);
         int count = parent.getChildCount();
         if (index == count - 1) {
            return;
         }
      }

      super.bringToFront();
   }

   public void f(int visibility) {
      if (visibility != this.getVisibility()) {
         super.setVisibility(visibility);
      }
   }

   public void setAnimation(Animation anim) {
      if (anim == null) {
         this.clearAnimation();
      } else {
         super.setAnimation(anim);
      }
   }

   public void startAnimation(Animation anim) {
      super.startAnimation(anim);
   }

   public boolean a1(String frameName, a view) {
      a frame = this.b(frameName);
      if (frame == null) {
         frame = view;
      }

      if (frame.canGoBack()) {
         frame.goBack();
         return true;
      } else {
         return false;
      }
   }

   public boolean b(String frameName, a view) {
      a frame = this.b(frameName);
      if (frame == null) {
         frame = view;
      }

      if (frame.canGoForward()) {
         frame.goForward();
         return true;
      } else {
         return false;
      }
   }

   public boolean h() {
      return this.n.canGoBack();
   }

   public void i() {
      this.n.goBack();
   }

   public String j() {
      return this.n.q();
   }

   public boolean k() {
      return this.h;
   }

   public void c(boolean flag) {
      this.h = flag;
      if (this.h) {
         this.c("root");
      }

   }

   protected com.uzmap.pkg.uzcore.uzmodule.e l() {
      return this.o.j();
   }

   public String m() {
      return this.k;
   }

   public void c(String inName) {
      if (TextUtils.isEmpty(inName)) {
         inName = Integer.toHexString(this.hashCode());
      }

      this.k = inName;
   }

   public boolean n() {
      return this.g;
   }

   public void a(com.uzmap.pkg.uzcore.uzmodule.d pageParam) {
      this.n.a(pageParam);
   }

   public void d(boolean flag) {
      this.g = flag;
      this.n.h(flag);
   }

   public void a1(com.uzmap.pkg.uzcore.uzmodule.aa.m args) {
      this.a1(args.b, args.d, args.c, args.e);
   }

   public void a1(int animationType, String msg, String title, boolean modal) {
      if (this.m == null) {
         this.m = new com.uzmap.pkg.uzcore.external.i(this.l, null);
         this.m.a();
         android.widget.RelativeLayout.LayoutParams parm = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
         this.m.setLayoutParams(parm);
      }

      this.m.a(msg);
      this.m.b(title);
      this.m.a(modal);
      if (this.m.getParent() != null) {
         this.m.bringToFront();
      } else {
         animationType = animationType > 0 ? animationType : 2;
         this.m.a(animationType);
         this.addView(this.m);
         this.m.b();
      }
   }

   public void o() {
      if (this.m != null) {
         this.m.c();
      }

   }

   public void a1(k args) {
      if (!this.q()) {
         com.uzmap.pkg.uzcore.f.b(this.l).a(args, this.l());
      }
   }

   public final void a1(String msg, int location, int duration) {
      com.uzmap.pkg.uzcore.external.k.a(this, msg, location, duration).a();
   }

   protected void a1(a view, String url) {
      this.g = false;
      this.o.a(this, url);
   }

   protected void b(a target, String url) {
      if (target.h()) {
         this.o();
      }

      int type = target.m();
      switch (type) {
         case 0:
            this.b(this.n);
            boolean ishttpUrl = url.startsWith("http");
            if (!ishttpUrl || ishttpUrl && "root".equals(this.k)) {
               this.o.b(this, url);
            }
            break;
         case 1:
            this.b(target);
            this.c(target);
            break;
         case 2:
            this.b(target);
      }

   }

   protected void a1(a target, int newProgress) {
      if (100 == newProgress && target.h()) {
         this.o();
      }

   }

   protected void b(a target) {
      if (target == null) {
         target = this.n;
      }

      target.e(com.uzmap.pkg.uzcore.aa.a.a());
   }

   public com.uzmap.pkg.uzcore.uzmodule.d p() {
      return this.o.n();
   }

   public boolean q() {
      return this.o.o();
   }

   public long r() {
      return this.d;
   }

   public void a(long animDuration) {
      this.d = animDuration;
   }

   public int s() {
      return this.b;
   }

   public void g(int animType) {
      this.b = animType;
   }

   public int t() {
      return this.c;
   }

   public void h(int subAnimType) {
      this.c = subAnimType;
   }

   public void i(int delay) {
      this.i = delay;
   }

   public int u() {
      return this.i;
   }

   public void e(boolean enable) {
      if (this.n != null) {
         this.n.setHorizontalScrollBarEnabled(enable);
      }

   }

   public void f(boolean enable) {
      if (this.n != null) {
         this.n.setVerticalScrollBarEnabled(enable);
      }

   }

   public void v() {
      this.n.stopLoading();
      Iterator var2 = this.p.values().iterator();

      while (var2.hasNext()) {
         a view = (a) var2.next();
         view.stopLoading();
      }

      var2 = this.q.values().iterator();

      while (var2.hasNext()) {
         h group = (h) var2.next();
         group.c();
      }

   }

   public void w() {
      this.v();
      this.n.clearCache(false);
      this.n.destroy();
      Iterator var2 = this.p.values().iterator();

      while (var2.hasNext()) {
         a view = (a) var2.next();
         View parent = (View) view.getParent();
         this.removeView(parent);
         view.clearCache(false);
         view.destroy();
      }

      this.p.clear();
      var2 = this.q.values().iterator();

      while (var2.hasNext()) {
         h group = (h) var2.next();
         this.removeView(group);
         group.b();
      }

      this.q.clear();
      this.removeAllViews();
      ViewGroup parent = (ViewGroup) this.getParent();
      if (parent != null) {
         parent.removeView(this);
      }

      this.n = null;
      this.p = null;
      this.q = null;
      this.o = null;
      this.l = null;

      try {
         com.uzmap.pkg.uzkit.a.aa.a.a().c(this.k);
      } catch (Exception var4) {
      }

      --a;
   }

   public void d(String name) {
      if (this.s != null) {
         this.s.equals(name);
      }

      this.s = name;
   }

   private void c(a frame) {
      g operate = (g) frame.getParent();
      if (operate.h()) {
         int animType = operate.i();
         int animSubType = operate.j();
         long duration = operate.g();
         v animPair = w.a(animType, animSubType, duration);
         if (!operate.isShown()) {
            operate.setVisibility(0);
         }

         operate.startAnimation(animPair.a);
      }
   }

   private void a1(a target, f moduleArgs) {
      UZCoreUtil.hideSoftKeyboard(this.l, target);
      g parent = (g) target.getParent();
      this.p.remove(target.B());
      target.stopLoading();
      if (parent != null) {
         parent.setVisibility(8);
         this.removeView(parent);
      }

      target.destroy();
   }

   public String toString() {
      return "win[" + this.k + "]" + "@" + Integer.toHexString(this.hashCode());
   }
}
