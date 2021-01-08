//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.external.bb;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.*;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.UzResourceCache;

public class h extends RelativeLayout {
   private final TextView a;
   private final TextView b;
   private final ProgressBar c;
   private final ImageView d;
   private final RotateAnimation e;
   private final RotateAnimation f;
   private boolean g;
   private final int h = -9600871;
   private final int i = -4461415;
   private String j = "下拉可以刷新...";
   private String k = "松开可以刷新...";
   private String l = "刷新中";
   private String m;

   public h(Context context, Object o) {
      super(context);
      this.setFocusable(false);
      this.setBackgroundColor(this.i);
      RelativeLayout layout = new RelativeLayout(context);
      layout.setFocusable(false);
      int padding = UZCoreUtil.dipToPix(5);
      layout.setPadding(padding, padding, padding, padding);
      LayoutParams llrlp = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.e);
      llrlp.addRule(12, -1);
      layout.setLayoutParams(llrlp);
      this.addView(layout);
      LinearLayout textArea = new LinearLayout(context);
      textArea.setOrientation(1);
      textArea.setId(273);
      textArea.setGravity(17);
      LayoutParams trlp = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.e, com.uzmap.pkg.uzcore.external.l.e);
      trlp.addRule(13, -1);
      textArea.setLayoutParams(trlp);
      layout.addView(textArea);
      this.a = new TextView(context);
      android.widget.LinearLayout.LayoutParams tlp = com.uzmap.pkg.uzcore.external.l.c(com.uzmap.pkg.uzcore.external.l.e, com.uzmap.pkg.uzcore.external.l.e);
      this.a.setLayoutParams(tlp);
      this.a.setTextColor(this.h);
      this.a.setText(this.j);
      this.a.setTextSize(15.0F);
      textArea.addView(this.a);
      this.b = new TextView(context);
      tlp = com.uzmap.pkg.uzcore.external.l.c(com.uzmap.pkg.uzcore.external.l.e, com.uzmap.pkg.uzcore.external.l.e);
      this.b.setLayoutParams(tlp);
      this.b.setTextColor(this.h);
      this.b.setTextSize(12.0F);
      this.b.setVisibility(8);
      textArea.addView(this.b);
      RelativeLayout imageArea = new RelativeLayout(context);
      int wh = UZCoreUtil.dipToPix(35);
      LayoutParams irlp = com.uzmap.pkg.uzcore.external.l.b(wh, wh);
      irlp.rightMargin = UZCoreUtil.dipToPix(10);
      irlp.addRule(15, -1);
      irlp.addRule(0, 273);
      imageArea.setLayoutParams(irlp);
      layout.addView(imageArea);
      this.c = new ProgressBar(context);
      this.c.setIndeterminate(true);
      int size = UZCoreUtil.dipToPix(25);
      LayoutParams prlp = com.uzmap.pkg.uzcore.external.l.b(size, size);
      prlp.addRule(13, -1);
      this.c.setLayoutParams(prlp);
      this.c.setVisibility(8);
      imageArea.addView(this.c);
      this.d = new ImageView(context);
      prlp = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.e, com.uzmap.pkg.uzcore.external.l.e);
      prlp.addRule(13, -1);
      this.d.setLayoutParams(prlp);
      imageArea.addView(this.d);
      this.f = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
      this.f.setInterpolator(new AccelerateInterpolator());
      this.f.setDuration(250L);
      this.f.setFillAfter(true);
      this.e = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
      this.e.setInterpolator(new AccelerateInterpolator());
      this.e.setDuration(250L);
      this.e.setFillAfter(true);
   }

   public void a(int v) {
      this.d.setVisibility(v);
      if (v == 8) {
         this.d.clearAnimation();
      }

   }

   public void b(int type) {
      this.d.clearAnimation();
      switch (type) {
         case 0:
            this.d.startAnimation(this.e);
            break;
         case 1:
            this.d.startAnimation(this.f);
      }

   }

   public void c(int v) {
      this.c.setVisibility(v);
   }

   public void d(int v) {
      this.a.setVisibility(v);
   }

   public void a(Drawable drawable) {
      Resources res = this.getContext().getResources();
      if (drawable != null) {
         com.uzmap.pkg.uzcore.external.l.a(this.d, drawable);
      } else {
         int refresh = com.uzmap.pkg.uzcore.d.a().y;
         if (refresh > 0) {
            drawable = res.getDrawable(refresh);
            com.uzmap.pkg.uzcore.external.l.a(this.d, drawable);
         }
      }

   }

   public void a(String path) {
      Bitmap bitmap = UzResourceCache.get().getImage(path);
      Resources res = this.getContext().getResources();
      Drawable drawable = null;
      if (bitmap != null) {
         drawable = new BitmapDrawable(res, bitmap);
      } else {
         int refresh = com.uzmap.pkg.uzcore.d.a().y;
         if (refresh > 0) {
            drawable = res.getDrawable(refresh);
         }
      }

      this.a(drawable);
   }

   public void a(boolean showTime) {
      if (this.g != showTime) {
         this.g = showTime;
         if (this.g) {
            String time = this.m != null ? this.m : "最后更新：" + UZCoreUtil.formatDate(System.currentTimeMillis());
            this.b.setText(time);
            this.b.setVisibility(0);
         } else {
            this.b.setVisibility(8);
         }
      }

   }

   public void a() {
      if (this.g) {
         String time = this.m != null ? this.m : "最后更新：" + UZCoreUtil.formatDate(System.currentTimeMillis());
         this.b.setText(time);
      }

   }

   public void e(int color) {
      this.a.setTextColor(color);
      this.b.setTextColor(color);
   }

   public void b(String value) {
      this.j = value;
      this.b();
   }

   public void c(String value) {
      this.k = value;
      this.c();
   }

   public void d(String value) {
      this.l = value;
      this.d();
   }

   public void e(String value) {
      this.m = value;
   }

   public void b() {
      this.a.setText(this.j);
      this.a.setVisibility(0);
   }

   public void c() {
      this.a.setText(this.k);
      this.a.setVisibility(0);
   }

   public void d() {
      this.a.setVisibility(0);
      this.a.setText(this.l);
   }
}
