package com.uzmap.pkg.a.a;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a extends Handler {
   private static com.uzmap.pkg.a.a.a d;
   private LocationManager locationManager;
   private final Context b;
   private final List<com.uzmap.pkg.a.a.a.b> c = new ArrayList();

   private a(Context context) {
      this.b = context;
   }

   public static com.uzmap.pkg.a.a.a a(Context context) {
      if (d == null) {
         d = new com.uzmap.pkg.a.a.a(context);
      }

      return d;
   }

   public void a(com.uzmap.pkg.a.a.a.a1 callback) {
      if (this.b != null) {
         this.b(callback);
         int accuracy = 2;
         float filter = 100.0F;
         com.uzmap.pkg.a.a.a.b listener = this.c(callback);
         if (listener == null) {
            listener = new com.uzmap.pkg.a.a.a.b(callback, true);

            try {
               this.a(filter, accuracy, listener);
            } catch (Exception var6) {
               var6.printStackTrace();
               this.a(callback, "" + var6);
               return;
            }

            this.a(listener);
         }

      }
   }

   public void a(com.uzmap.pkg.a.a.a.c option, com.uzmap.pkg.a.a.a.a1 callback) {
      if (this.b != null) {
         int accuracy = option.a;
         switch (accuracy) {
            case 0:
               accuracy = 1;
               break;
            case 1:
               accuracy = 1;
               break;
            case 2:
               accuracy = 2;
               break;
            case 3:
               accuracy = 2;
               break;
            default:
               accuracy = 2;
         }

         this.b(callback);
         com.uzmap.pkg.a.a.a.b listener = this.c(callback);
         if (listener == null) {
            listener = new com.uzmap.pkg.a.a.a.b(callback, option.c);

            try {
               this.a(option.b, accuracy, listener);
            } catch (Exception var6) {
               var6.printStackTrace();
               this.a(callback, "" + var6);
               return;
            }

            this.a(listener);
         }

      }
   }

   public void b(com.uzmap.pkg.a.a.a.a1 callback) {
      if (this.b != null && callback != null) {
         com.uzmap.pkg.a.a.a.b listener = this.c(callback);
         if (listener != null) {
            this.b(listener);
            this.locationManager.removeUpdates(listener);
            this.removeCallbacks(listener);
         }

      }
   }

   private void a(float filter, int accuracy, com.uzmap.pkg.a.a.a.b listener) throws Exception {
      Criteria criteria = new Criteria();
      criteria.setAccuracy(accuracy);
      criteria.setAltitudeRequired(false);
      criteria.setBearingRequired(false);
      criteria.setCostAllowed(true);
      criteria.setPowerRequirement(3);
      criteria.setSpeedRequired(false);
      if (this.locationManager == null) {
         this.locationManager = (LocationManager) this.b.getSystemService("location");
      }

      String bestProvider = this.locationManager.getBestProvider(criteria, true);
      if (TextUtils.isEmpty(bestProvider)) {
         bestProvider = "network";
      }

      UZCoreUtil.logd("Geo BestProviderr: " + bestProvider);
      this.locationManager.requestLocationUpdates(bestProvider, 0L, filter, listener);
      this.postDelayed(listener, 60000L);
   }

   private boolean a(com.uzmap.pkg.a.a.a.b callback) {
      synchronized (this.c) {
         if (!this.c.contains(callback)) {
            this.c.add(callback);
            return true;
         } else {
            return false;
         }
      }
   }

   private com.uzmap.pkg.a.a.a.b c(com.uzmap.pkg.a.a.a.a1 callback) {
      synchronized (this.c) {
         Iterator var4 = this.c.iterator();

         while (var4.hasNext()) {
            com.uzmap.pkg.a.a.a.b listener = (com.uzmap.pkg.a.a.a.b) var4.next();
            if (listener.a(callback)) {
               return listener;
            }
         }

         return null;
      }
   }

   private void b(com.uzmap.pkg.a.a.a.b listener) {
      synchronized (this.c) {
         this.c.remove(listener);
      }
   }

   private void a(com.uzmap.pkg.a.a.a.a1 listener, String msg) {
      if (listener != null) {
         JSONObject location = new JSONObject();

         try {
            location.put("latitude", 0);
            location.put("longitude", 0);
            location.put("timestamp", 0);
            location.put("altitude", 0.0D);
            location.put("status", false);
            location.put("msg", msg);
         } catch (Exception var5) {
            var5.printStackTrace();
         }

         listener.a(location);
         this.b(listener);
         UZCoreUtil.logd("onLocationChanged: " + msg);
      }
   }

   public interface a1 {
      void a(JSONObject var1);
   }

   public static class c {
      final int a;
      final float b;
      final boolean c;

      public c(int acc, float filter, boolean autoStop) {
         this.a = acc;
         this.b = filter;
         this.c = autoStop;
      }
   }

   private class b implements LocationListener, Runnable {
      private final boolean b;
      private final com.uzmap.pkg.a.a.a.a1 c;

      public b(com.uzmap.pkg.a.a.a.a1 listener, boolean autoStop) {
         this.b = autoStop;
         this.c = listener;
      }

      public boolean a(com.uzmap.pkg.a.a.a.a1 listener) {
         return this.c == listener;
      }

      public void a() {
         a.this.a(this.c, "timeout");
      }

      public void onLocationChanged(Location location) {
         a.this.removeCallbacks(this);
         double lat = location.getLatitude();
         double log = location.getLongitude();
         double alt = location.getAltitude();
         long timestamp = System.currentTimeMillis();
         JSONObject json = new JSONObject();

         try {
            json.put("latitude", lat);
            json.put("longitude", log);
            json.put("altitude", alt);
            json.put("timestamp", timestamp);
            json.put("status", true);
            json.put("msg", "");
         } catch (Exception var12) {
            var12.printStackTrace();
         }

         if (this.c != null) {
            this.c.a(json);
         }

         if (this.b) {
            a.this.b(this.c);
         }

         UZCoreUtil.logd("onLocationChanged: lat: " + lat + " , log: " + log);
      }

      public void onProviderDisabled(String provider) {
         UZCoreUtil.logd("onProviderDisabled");
      }

      public void onProviderEnabled(String provider) {
         UZCoreUtil.logd("onProviderEnabled");
      }

      public void onStatusChanged(String provider, int status, Bundle extras) {
         UZCoreUtil.logd("onStatusChanged: " + provider + " , " + status);
      }

      public void run() {
         this.a();
      }
   }
}
