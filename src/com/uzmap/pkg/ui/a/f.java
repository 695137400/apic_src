package com.uzmap.pkg.ui.a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class f implements SensorEventListener {
   private static f j;
   private float a;
   private float b;
   private float c;
   private long d;
   private SensorManager e;
   private Vibrator f;
   private boolean g;
   private final Context h;
   private final List<f.a> i = new ArrayList();

   private f(Context context) {
      this.h = context != null ? context.getApplicationContext() : null;
   }

   public static final f a(Context context) {
      if (j == null) {
         j = new f(context);
      }

      return j;
   }

   private void b() {
      if (this.h != null) {
         if (this.e == null) {
            try {
               this.e = (SensorManager) this.h.getSystemService("sensor");
            } catch (Exception var2) {
            }
         }

         Sensor sensor = null;
         if (this.e != null) {
            sensor = this.e.getDefaultSensor(1);
         }

         if (sensor != null) {
            this.e.registerListener(this, sensor, 1);
         }

         this.g = true;
      }
   }

   private void c() {
      if (this.e != null) {
         this.e.unregisterListener(this);
      }

      this.g = false;
   }

   public void a(f.a listener) {
      if (listener != null) {
         synchronized (this.i) {
            if (!this.i.contains(listener)) {
               this.i.add(listener);
            }

            if (!this.a()) {
               this.b();
            }

         }
      }
   }

   public void b(f.a listener) {
      synchronized (this.i) {
         this.i.remove(listener);
         if (this.i.isEmpty()) {
            this.c();
         }

      }
   }

   public boolean a() {
      return this.g;
   }

   public void onSensorChanged(SensorEvent event) {
      long currentUpdateTime = System.currentTimeMillis();
      long timeInterval = currentUpdateTime - this.d;
      if (timeInterval >= 70L) {
         this.d = currentUpdateTime;
         float x = event.values[0];
         float y = event.values[1];
         float z = event.values[2];
         float deltaX = x - this.a;
         float deltaY = y - this.b;
         float deltaZ = z - this.c;
         this.a = x;
         this.b = y;
         this.c = z;
         double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) / (double) timeInterval * 10000.0D;
         if (speed >= 3000.0D) {
            this.d();
         }

      }
   }

   public void onAccuracyChanged(Sensor sensor, int accuracy) {
   }

   private void d() {
      if (!this.i.isEmpty()) {
         if (this.f == null) {
            try {
               this.f = (Vibrator) this.h.getSystemService("vibrator");
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }

         if (this.f != null) {
            this.f.vibrate(new long[]{500L, 200L, 500L, 200L}, -1);
         }

         synchronized (this.i) {
            Iterator var3 = this.i.iterator();

            while (var3.hasNext()) {
               f.a listener = (f.a) var3.next();
               listener.a();
            }

         }
      }
   }

   public interface a {
      void a();
   }
}
