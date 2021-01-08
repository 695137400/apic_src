package com.uzmap.pkg.uzapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.uzmap.pkg.EntranceActivity;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import org.json.JSONObject;

public class d {
    private static d a;

    private d() {
    }

    public static d a() {
        if (a == null) {
            a = new d();
        }

        return a;
    }

    public int a(Context context, d.a option) {
        if (option.g) {
            Intent action = this.a(context, option.c);
            context.startActivity(action);
            return -1;
        } else {
            int notifyId = this.a(!option.e);
            int iconId = com.uzmap.pkg.uzcore.d.a().w;
            PendingIntent pi = this.a(context, option.c, option.e, notifyId);
            Builder builder = new Builder(context);
            builder.setContentIntent(pi);
            builder.setSmallIcon(iconId);
            String appName = UZCoreUtil.getAppName();
            builder.setTicker(appName);
            String title = !TextUtils.isEmpty(option.a) ? option.a : appName;
            builder.setContentTitle(title);
            builder.setContentText(option.b);
            builder.setAutoCancel(true);
            builder.setWhen(System.currentTimeMillis());
            boolean insilence = option.a();
            if (!insilence) {
                int defaults = 0;
                if (option.i && option.f) {
                    defaults = -1;
                } else if (option.f) {
                    defaults = 2;
                } else if (option.i) {
                    defaults = 1;
                }

                builder.setDefaults(defaults);
            }

            Notification notification = builder.build();
            NotificationManager nm = (NotificationManager) context.getSystemService("notification");
            nm.notify(notifyId, notification);
            return notifyId;
        }
    }

    public void a(Context context, int id) {
        if (id != -1 && id < 1073741823) {
            com.uzmap.pkg.uzcore.external.aa.c.a(context, id);
        } else {
            NotificationManager nm = (NotificationManager) context.getSystemService("notification");
            if (id == -1) {
                nm.cancelAll();
            } else {
                nm.cancel(id);
            }

        }
    }

    public synchronized PendingIntent a(Context context, String extra, boolean updateCurrent, int notifyId) {
        Intent action = new Intent("android.intent.action.MAIN");
        action.addCategory("android.intent.category.LAUNCHER");
        action.setComponent(new ComponentName(context, EntranceActivity.class));
        action.setFlags(805306368);
        if (!TextUtils.isEmpty(extra)) {
            JSONObject json = new JSONObject();

            try {
                json.put("type", 0);
                json.put("value", extra);
            } catch (Exception var8) {
            }

            action.putExtra("api_arguments", json.toString());
        }

        int flag = updateCurrent ? 134217728 : 1073741824;
        PendingIntent pi = PendingIntent.getActivity(context, notifyId, action, flag);
        return pi;
    }

    public synchronized Intent a(Context context, String extra) {
        Intent action = new Intent("android.intent.action.MAIN");
        action.addCategory("android.intent.category.LAUNCHER");
        action.setComponent(new ComponentName(context, EntranceActivity.class));
        action.setFlags(805306368);
        if (!TextUtils.isEmpty(extra)) {
            JSONObject json = new JSONObject();

            try {
                json.put("type", 0);
                json.put("value", extra);
            } catch (Exception var6) {
            }

            action.putExtra("api_arguments", json.toString());
        }

        return action;
    }

    public synchronized int a(boolean growth) {
        com.uzmap.pkg.uzkit.data.a apfe = com.uzmap.pkg.uzkit.data.a.a();
        int counter = apfe.b("last_notifyId", 1073741823);
        if (!growth) {
            return counter;
        } else {
            ++counter;
            apfe.a("last_notifyId", counter);
            return counter;
        }
    }

    public static class a {
        public String a;
        public String b;
        public String c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i = true;

        public boolean a() {
            if (this.h) {
                return true;
            } else {
                return !this.f && !this.i;
            }
        }
    }
}
