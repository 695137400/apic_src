//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.external.aa;

import android.content.Context;
import android.util.Log;
import com.uzmap.pkg.uzcore.external.Alarm;
import com.uzmap.pkg.uzcore.external.Alarm.a;

public class c {
    private static int a = -1;

    public static int a(Context context, int hour, int minutes, int[] days, long time, String notify) {
        Alarm alarm = new Alarm();
        alarm.a = a;
        if (time > 0L) {
            alarm.f = time;
            alarm.e = a((int[])null);
        } else {
            alarm.c = hour;
            alarm.d = minutes;
            alarm.e = a(days);
        }

        alarm.g = true;
        alarm.h = notify;
        alarm.i = null;
        alarm.b = true;
        int id = a;
        long lastTime;
        if (alarm.a == a) {
            lastTime = d.a(context, alarm);
            id = alarm.a;
        } else {
            lastTime = d.b(context, alarm);
        }

        Log.d("alarm", "lastTime: " + lastTime);
        return id;
    }

    public static void a(Context context, int alarmId) {
        d.a(context, alarmId);
    }

    private static a a(int[] days) {
        a daysOfWeek = new a(0);
        if (days != null && days.length != 0) {
            int[] var5 = days;
            int var4 = days.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                int d = var5[var3];
                --d;
                if (d >= 0 && d <= 6) {
                    daysOfWeek.a(d, true);
                }
            }
        }

        return daysOfWeek;
    }
}
