package com.uzmap.pkg.uzcore.external.aa;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.*;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.text.format.DateFormat;
import android.util.Log;
import com.uzmap.pkg.uzcore.external.Alarm;

import java.util.Calendar;

public class d {
    public static long a(Context context, Alarm alarm) {
        ContentValues values = a(alarm);
        Uri uri = context.getContentResolver().insert(e.a, values);
        alarm.a = (int) ContentUris.parseId(uri);
        long timeInMillis = b(alarm);
        if (alarm.b) {
            a(context, timeInMillis);
        }

        b(context);
        return timeInMillis;
    }

    public static void a(Context context, int alarmId) {
        if (alarmId != -1) {
            ContentResolver contentResolver = context.getContentResolver();
            b(context, alarmId);
            Uri uri = ContentUris.withAppendedId(e.a, alarmId);
            contentResolver.delete(uri, "", null);
            b(context);
        }
    }

    private static Cursor a(ContentResolver contentResolver) {
        return contentResolver.query(e.a, b.a, "enabled=1", null, null);
    }

    private static ContentValues a(Alarm alarm) {
        ContentValues values = new ContentValues(8);
        long time = 0L;
        if (!alarm.e.b()) {
            time = b(alarm);
        }

        values.put("enabled", alarm.b ? 1 : 0);
        values.put("hour", alarm.c);
        values.put("minutes", alarm.d);
        values.put("alarmtime", alarm.f);
        values.put("daysofweek", alarm.e.a());
        values.put("vibrate", alarm.g);
        values.put("message", alarm.h);
        values.put("alert", alarm.i == null ? "silent" : alarm.i.toString());
        Log.d("alarm", "time: " + time);
        return values;
    }

    private static void a(Context context, long alarmTime) {
        SharedPreferences prefs = context.getSharedPreferences("APICloudAlarmClock", 0);
        long snoozeTime = prefs.getLong("snooze_time", 0L);
        if (alarmTime < snoozeTime) {
            a(context, prefs);
        }

    }

    public static Alarm a(ContentResolver contentResolver, int alarmId) {
        Cursor cursor = contentResolver.query(ContentUris.withAppendedId(e.a, alarmId), b.a, null, null, null);
        Alarm alarm = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                alarm = new Alarm(cursor);
            }

            cursor.close();
        }

        return alarm;
    }

    public static long b(Context context, Alarm alarm) {
        ContentValues values = a(alarm);
        ContentResolver resolver = context.getContentResolver();
        resolver.update(ContentUris.withAppendedId(e.a, alarm.a), values, null, null);
        long timeInMillis = b(alarm);
        if (alarm.b) {
            b(context, alarm.a);
            a(context, timeInMillis);
        }

        b(context);
        return timeInMillis;
    }

    public static void a(Context context, int id, boolean enabled) {
        b(context, id, enabled);
        b(context);
    }

    private static void b(Context context, int id, boolean enabled) {
        a(context, a(context.getContentResolver(), id), enabled);
    }

    private static void a(Context context, Alarm alarm, boolean enabled) {
        if (alarm != null) {
            ContentResolver resolver = context.getContentResolver();
            ContentValues values = new ContentValues(2);
            values.put("enabled", enabled ? 1 : 0);
            if (enabled) {
                long time = 0L;
                if (!alarm.e.b()) {
                    time = b(alarm);
                }

                values.put("alarmtime", time);
            } else {
                b(context, alarm.a);
            }

            resolver.update(ContentUris.withAppendedId(e.a, alarm.a), values, null, null);
        }
    }

    public static Alarm a(Context context) {
        Alarm alarm = null;
        long minTime = Long.MAX_VALUE;
        long now = System.currentTimeMillis();
        Cursor cursor = a(context.getContentResolver());
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Alarm a = new Alarm(cursor);
                    if (a.f == 0L) {
                        a.f = b(a);
                    } else if (a.f < now) {
                        a(context, a, false);
                        continue;
                    }

                    if (a.f < minTime) {
                        minTime = a.f;
                        alarm = a;
                    }
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return alarm;
    }

    public static void b(Context context) {
        if (!d(context)) {
            Alarm alarm = a(context);
            if (alarm != null) {
                a(context, alarm, alarm.f);
            } else {
                c(context);
            }
        }

    }

    private static void a(Context context, Alarm alarm, long atTimeInMillis) {
        AlarmManager am = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent("android.intent.apicloud.notification");
        intent.setPackage(context.getPackageName());
        Parcel out = Parcel.obtain();
        alarm.writeToParcel(out, 0);
        out.setDataPosition(0);
        intent.putExtra("intent.extra.alarm_raw", out.marshall());
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 268435456);
        am.set(0, atTimeInMillis, sender);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(atTimeInMillis);
        String timeString = a(context, c);
        a(context, timeString);
    }

    static void c(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService("alarm");
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, new Intent("android.intent.apicloud.notification"), 268435456);
        am.cancel(sender);
        a(context, "");
    }

    public static void b(Context context, int id) {
        SharedPreferences prefs = context.getSharedPreferences("APICloudAlarmClock", 0);
        int snoozeId = prefs.getInt("snooze_id", -1);
        if (snoozeId != -1) {
            if (snoozeId == id) {
                a(context, prefs);
            }

        }
    }

    private static void a(Context context, SharedPreferences prefs) {
        int alarmId = prefs.getInt("snooze_id", -1);
        if (alarmId != -1) {
            NotificationManager nm = (NotificationManager) context.getSystemService("notification");
            nm.cancel(alarmId);
        }

        Editor ed = prefs.edit();
        ed.remove("snooze_id");
        ed.remove("snooze_time");
        ed.apply();
    }

    private static boolean d(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("APICloudAlarmClock", 0);
        int id = prefs.getInt("snooze_id", -1);
        if (id == -1) {
            return false;
        } else {
            long time = prefs.getLong("snooze_time", -1L);
            Alarm alarm = a(context.getContentResolver(), id);
            if (alarm == null) {
                return false;
            } else {
                alarm.f = time;
                a(context, alarm, time);
                return true;
            }
        }
    }

    private static long b(Alarm alarm) {
        return a(alarm.c, alarm.d, alarm.e).getTimeInMillis();
    }

    private static Calendar a(int hour, int minute, Alarm.a daysOfWeek) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int nowHour = c.get(11);
        int nowMinute = c.get(12);
        if (hour < nowHour || hour == nowHour && minute <= nowMinute) {
            c.add(6, 1);
        }

        c.set(11, hour);
        c.set(12, minute);
        c.set(13, 0);
        c.set(14, 0);
        int addDays = daysOfWeek.a(c);
        if (addDays > 0) {
            c.add(7, addDays);
        }

        return c;
    }

    private static String a(Context context, Calendar c) {
        String format = e(context) ? "E k:mm" : "E h:mm aa";
        return c == null ? "" : (String) DateFormat.format(format, c);
    }

    private static void a(Context context, String timeString) {
        try {
            android.provider.Settings.System.putString(context.getContentResolver(), "next_alarm_formatted", timeString);
        } catch (Exception var3) {
        }

    }

    private static boolean e(Context context) {
        return DateFormat.is24HourFormat(context);
    }
}
