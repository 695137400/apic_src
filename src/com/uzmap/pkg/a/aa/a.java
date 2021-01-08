//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.aa;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class a extends Handler {
    private LocationManager a;
    private Context b;
    private List<com.uzmap.pkg.a.aa.a.b> c = new ArrayList();
    private static com.uzmap.pkg.a.aa.a d;

    private a(Context context) {
        this.b = context;
    }

    public static com.uzmap.pkg.a.aa.a a(Context context) {
        if (d == null) {
            d = new com.uzmap.pkg.a.aa.a(context);
        }

        return d;
    }

    public void a(com.uzmap.pkg.a.aa.a.aa callback) {
        if (this.b != null) {
            this.b(callback);
            int accuracy = 2;
            float filter = 100.0F;
            com.uzmap.pkg.a.aa.a.b listener = this.c(callback);
            if (listener == null) {
                listener = new com.uzmap.pkg.a.aa.a.b(callback, true);

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

    public void a(com.uzmap.pkg.a.aa.a.c option, com.uzmap.pkg.a.aa.a.aa callback) {
        if (this.b != null) {
            int accuracy = option.a;
            switch(accuracy) {
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
            com.uzmap.pkg.a.aa.a.b listener = this.c(callback);
            if (listener == null) {
                listener = new com.uzmap.pkg.a.aa.a.b(callback, option.c);

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

    public void b(com.uzmap.pkg.a.aa.a.aa callback) {
        if (this.b != null && callback != null) {
            com.uzmap.pkg.a.aa.a.b listener = this.c(callback);
            if (listener != null) {
                this.b(listener);
                this.a.removeUpdates(listener);
                this.removeCallbacks(listener);
            }

        }
    }

    private void a(float filter, int accuracy, com.uzmap.pkg.a.aa.a.b listener) throws Exception {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(accuracy);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(3);
        criteria.setSpeedRequired(false);
        if (this.a == null) {
            this.a = (LocationManager)this.b.getSystemService("location");
        }

        String bestProvider = this.a.getBestProvider(criteria, true);
        if (TextUtils.isEmpty(bestProvider)) {
            bestProvider = "network";
        }

        UZCoreUtil.logd("Geo BestProviderr: " + bestProvider);
        this.a.requestLocationUpdates(bestProvider, 0L, filter, listener);
        this.postDelayed(listener, 60000L);
    }

    private boolean a(com.uzmap.pkg.a.aa.a.b callback) {
        synchronized(this.c) {
            if (!this.c.contains(callback)) {
                this.c.add(callback);
                return true;
            } else {
                return false;
            }
        }
    }

    private com.uzmap.pkg.a.aa.a.b c(com.uzmap.pkg.a.aa.a.aa callback) {
        synchronized(this.c) {
            Iterator var4 = this.c.iterator();

            while(var4.hasNext()) {
                com.uzmap.pkg.a.aa.a.b listener = (com.uzmap.pkg.a.aa.a.b)var4.next();
                if (listener.a(callback)) {
                    return listener;
                }
            }

            return null;
        }
    }

    private void b(com.uzmap.pkg.a.aa.a.b listener) {
        synchronized(this.c) {
            this.c.remove(listener);
        }
    }

    private void a(com.uzmap.pkg.a.aa.a.aa listener, String msg) {
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

    public interface aa {
        void a(JSONObject var1);
    }

    private class b implements LocationListener, Runnable {
        private final boolean b;
        private final com.uzmap.pkg.a.aa.a.aa c;

        public b(com.uzmap.pkg.a.aa.a.aa listener, boolean autoStop) {
            this.b = autoStop;
            this.c = listener;
        }

        public boolean a(com.uzmap.pkg.a.aa.a.aa listener) {
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
}
