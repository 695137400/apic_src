package com.uzmap.pkg.uzapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

import java.lang.reflect.Method;

public class a {
    static String a = null;
    static String b;
    static int c;

    static {
        b = Build.MANUFACTURER != null ? Build.MANUFACTURER.toLowerCase() : "";
        c = -1;
    }

    public static void a(Context context, int count) {
        count = count < 0 ? 0 : (count > 99 ? 99 : count);
        if (b.equalsIgnoreCase("xiaomi")) {
            b(context, count);
        } else if (b.equalsIgnoreCase("sony")) {
            c(context, count);
        } else if (b.contains("samsung")) {
            d(context, count);
        } else {
            d(context, count);
        }

    }

    public static void a(Context context) {
        a(context, 0);
    }

    private static void b(Context context, int count) {
        if (!a()) {
            Intent localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
            localIntent.putExtra("android.intent.extra.update_application_component_name", context.getPackageName() + "/" + b(context));
            localIntent.putExtra("android.intent.extra.update_application_message_text", String.valueOf(count == 0 ? "" : count));
            context.sendBroadcast(localIntent);
        }

    }

    private static void c(Context context, int count) {
        String launcherClass = b(context);
        if (launcherClass != null) {
            boolean shown = count != 0;
            Integer number = count;
            Intent localIntent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", shown);
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", launcherClass);
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", number);
            localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());
            context.sendBroadcast(localIntent);
        }
    }

    private static void d(Context context, int count) {
        String launcherClass = b(context);
        if (launcherClass != null) {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", count);
            intent.putExtra("badge_count_package_name", context.getPackageName());
            intent.putExtra("badge_count_class_name", launcherClass);
            context.sendBroadcast(intent);
        }
    }

    private static String b(Context context) {
        if (a != null) {
            return a;
        } else {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setPackage(context.getPackageName());
            intent.addCategory("android.intent.category.LAUNCHER");
            ResolveInfo info = packageManager.resolveActivity(intent, 65536);
            if (info == null) {
                info = packageManager.resolveActivity(intent, 0);
            }

            a = info.activityInfo.name;
            return a;
        }
    }

    private static boolean a() {
        if (c >= 0) {
            return c >= 6;
        } else {
            try {
                Class[] param = new Class[]{String.class};
                Method method = Build.class.getDeclaredMethod("getString", param);
                method.setAccessible(true);
                String name = "ro.miui.ui.version.name";
                String value = (String) method.invoke(null, name);
                if (value == null) {
                    return false;
                } else {
                    String ver = value.substring(1);
                    int version = 0;

                    try {
                        version = Integer.parseInt(ver);
                    } catch (Exception var7) {
                    }

                    c = version;
                    return c >= 6;
                }
            } catch (Exception var8) {
                var8.printStackTrace();
                return false;
            }
        }
    }
}
