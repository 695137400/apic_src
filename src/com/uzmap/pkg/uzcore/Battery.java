package com.uzmap.pkg.uzcore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Battery extends BroadcastReceiver {
    private static Battery a;
    private Context b;
    private boolean c;
    private List<Battery.a> d = new ArrayList();

    private Battery(Context context) {
        this.b = context.getApplicationContext();
    }

    public static Battery a(Context context) {
        if (a == null) {
            a = new Battery(context);
        }

        return a;
    }

    public boolean a() {
        return this.c;
    }

    public void a(Battery.a listener) {
        if (listener != null) {
            synchronized (this.d) {
                if (!this.d.contains(listener)) {
                    this.d.add(listener);
                }

                if (!this.a()) {
                    this.b();
                }

            }
        }
    }

    public void b(Battery.a listener) {
        synchronized (this.d) {
            this.d.remove(listener);
            if (this.d.isEmpty()) {
                this.c();
            }

        }
    }

    private void b() {
        if (this.b != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.BATTERY_CHANGED");
            filter.addAction("android.intent.action.BATTERY_LOW");
            this.b.registerReceiver(this, filter);
            this.c = true;
        }
    }

    private void c() {
        if (this.b != null) {
            this.b.unregisterReceiver(this);
            this.c = false;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (!this.d.isEmpty() && intent != null) {
            String action = intent.getAction();
            int status = intent.getIntExtra("status", 1);
            int level = intent.getIntExtra("level", 0);
            boolean isPlugged = status == 2;
            JSONObject json = new JSONObject();

            try {
                json.put("level", level);
                json.put("isPlugged", isPlugged);
            } catch (Exception var12) {
                var12.printStackTrace();
            }

            boolean low = false;
            if ("android.intent.action.BATTERY_LOW".equals(action)) {
                low = true;
            }

            synchronized (this.d) {
                Iterator var11 = this.d.iterator();

                while (var11.hasNext()) {
                    Battery.a listener = (Battery.a) var11.next();
                    listener.a(low, json);
                }

            }
        }
    }

    public interface a {
        void a(boolean var1, JSONObject var2);
    }
}
