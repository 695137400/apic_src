package com.uzmap.pkg.a.a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.uzmodule.aa.l;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
   private static c a;

   private c() {
   }

   public static c a() {
      if (a == null) {
         a = new c();
      }

      return a;
   }

   public int a(l moduleContext) {
      if (moduleContext != null && moduleContext.c()) {
         return this.b(moduleContext);
      } else {
         int flag = 0;
         if (moduleContext.c == null || "default".equals(moduleContext.c)) {
            flag |= 1;
         }

         if (moduleContext.d) {
            flag |= 4;
         }

         String title = moduleContext.d();
         String content = moduleContext.e();
         Context c = com.uzmap.pkg.uzcore.b.a().b();
         Builder builder = new Builder(c);
         builder.setContentTitle(title);
         builder.setContentText(content);
         builder.setTicker("");
         builder.setAutoCancel(true);
         com.uzmap.pkg.uzapp.d instance = com.uzmap.pkg.uzapp.d.a();
         int notifyId = instance.a(false);
         if (moduleContext.b()) {
            int iconId = com.uzmap.pkg.uzcore.d.a().u.icon;
            String extra = moduleContext.f();
            boolean updateCurrent = moduleContext.g();
            if (!updateCurrent) {
               notifyId = instance.a(true);
            }

            PendingIntent pi = instance.a(c, extra, updateCurrent, notifyId);
            builder.setContentIntent(pi);
            builder.setSmallIcon(iconId);
            builder.setTicker(UZCoreUtil.getAppName());
         }

         if (moduleContext.b != null) {
            builder.setVibrate(moduleContext.b);
         }

         builder.setDefaults(flag);
         Notification notification = builder.build();
         ((NotificationManager) c.getSystemService("notification")).notify(notifyId, notification);
         return notifyId;
      }
   }

   public void a(int id) {
      com.uzmap.pkg.uzapp.d.a().a(com.uzmap.pkg.uzcore.b.a().b(), id);
   }

   protected int b(l moduleContext) {
      int notifyId = -1;
      JSONObject alarm = moduleContext.e;
      long time = alarm.optLong("time", 0L);
      int hour = alarm.optInt("hour", 0);
      int minutes = alarm.optInt("minutes", 0);
      if (0L == time) {
         if (hour < 0 || hour > 23) {
            return notifyId;
         }

         if (minutes < 0 || minutes > 59) {
            return notifyId;
         }
      }

      JSONArray days = alarm.optJSONArray("daysOfWeek");
      int l = days != null ? days.length() : 0;
      int[] daysOfWeek = new int[l];

      for (int i = 0; i < l; ++i) {
         daysOfWeek[i] = days.optInt(i);
      }

      boolean openApp = alarm.optBoolean("openApp", false);
      JSONObject notify = moduleContext.a;
      if (notify == null) {
         notify = new JSONObject();
      }

      try {
         notify.put("openApp", openApp);
      } catch (Exception var15) {
      }

      try {
         notifyId = com.uzmap.pkg.uzcore.external.aa.c.a(com.uzmap.pkg.uzcore.b.a().b(), hour, minutes, daysOfWeek, time, notify.toString());
      } catch (Exception var14) {
         var14.printStackTrace();
      }

      return notifyId;
   }
}
