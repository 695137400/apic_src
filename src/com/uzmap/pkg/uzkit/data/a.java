package com.uzmap.pkg.uzkit.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.uzmap.pkg.uzcore.external.l;

import java.util.Calendar;

public class a {
    private static a c;
    private SharedPreferences a;
    private Editor b;

    private a() {
        this.i();
    }

    public static a a() {
        if (c == null) {
            c = new a();
        }

        return c;
    }

    public void a(String key, boolean value) {
        this.b.putBoolean(key, value);
        this.h();
    }

    public boolean b(String key, boolean defValue) {
        return this.a.getBoolean(key, defValue);
    }

    public void a(String key, int value) {
        this.b.putInt(key, value);
        this.h();
    }

    public int b(String key, int defValue) {
        return this.a.getInt(key, defValue);
    }

    public void a(String key, String value) {
        this.b.putString(key, value);
        this.h();
    }

    public String b(String key, String defValue) {
        return this.a.getString(key, defValue);
    }

    public boolean a(String key) {
        return this.a.contains(key);
    }

    public void b(String key) {
        this.b.remove(key);
        this.h();
    }

    private void h() {
        this.b.commit();
    }

    public boolean b() {
        return this.b("push_flag", true);
    }

    public void a(boolean flag) {
        this.a("push_flag", flag);
    }

    public boolean c() {
        return this.b("push_notify", true);
    }

    public boolean d() {
        return this.b("notify_updateCurrent", false);
    }

    public String e() {
        return this.b("push_defaults", "all");
    }

    public boolean f() {
        int startHour = this.b("push_silence_start_hour", -1);
        int startMinute = this.b("push_silence_start_minute", -1);
        int endHour = this.b("push_silence_end_hour", -1);
        int endMinute = this.b("push_silence_end_minute", -1);
        if (startHour >= 0 && startMinute >= 0 && endHour >= 0 && endMinute >= 0) {
            Calendar rightNow = Calendar.getInstance();
            long nowMillis = rightNow.getTimeInMillis();
            Calendar start = Calendar.getInstance();
            start.set(11, startHour);
            start.set(12, startMinute);
            long startMillis = start.getTimeInMillis();
            Calendar end = Calendar.getInstance();
            if (endHour < startHour) {
                end.add(5, 1);
            }

            end.set(11, endHour);
            end.set(12, endMinute);
            long endMillis = end.getTimeInMillis();
            return nowMillis > startMillis && nowMillis < endMillis;
        } else {
            return false;
        }
    }

    public com.uzmap.pkg.uzapp.d.a g() {
        this.i();
        com.uzmap.pkg.uzapp.d.a option = new com.uzmap.pkg.uzapp.d.a();
        option.d = this.c();
        option.h = this.f();
        option.e = this.d();
        String defaults = this.e();
        if ("all".equals(defaults)) {
            option.f = true;
            option.i = true;
        } else if ("vibrate".equals(defaults)) {
            option.f = true;
            option.i = false;
        } else if ("sound".equals(defaults)) {
            option.f = false;
            option.i = true;
        }

        return option;
    }

    private void i() {
        Context context = com.uzmap.pkg.uzcore.b.a().b();
        this.a = context.getSharedPreferences("UzAppStorage", l.b);
        this.b = this.a.edit();
    }
}
