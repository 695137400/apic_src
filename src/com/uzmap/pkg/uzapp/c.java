package com.uzmap.pkg.uzapp;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Process;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.ApplicationProcess;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.Alarm;
import com.uzmap.pkg.uzsocket.UPnsService;
import org.json.JSONObject;

public class c {
    private static c a;

    public static c a() {
        if (a == null) {
            a = new c();
        }

        return a;
    }

    public void a(Context context, Intent intent) {
        if (context != null && intent != null) {
            String action = intent.getAction();
            UZCoreUtil.logi("UPnsReceiver onReceive: " + Process.myPid() + " , " + action);
            if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
                if (!ApplicationProcess.initialize().n()) {
                    UPnsService.a(context);
                }
            } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                if (!ApplicationProcess.initialize().n()) {
                    UPnsService.a(context);
                }
            } else if ("android.intent.action.DOWNLOAD_COMPLETE".equals(action)) {
                this.c(context, intent);
            } else if ("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED".equals(action)) {
                this.b(context, intent);
            } else if ("android.intent.apicloud.notification".equals(action)) {
                this.d(context, intent);
            } else if (!"android.intent.action.PACKAGE_ADDED".equals(action) && !"android.intent.action.PACKAGE_REMOVED".equals(action) && "com.android.vending.INSTALL_REFERRER".equals(action)) {
            }

        }
    }

    private void b(Context context, Intent intent) {
        long[] ids = intent.getLongArrayExtra("extra_click_download_ids");
        if (ids != null && ids.length != 0) {
            long id = ids[0];
            DownloadManager manager = (DownloadManager) context.getSystemService("download");
            Uri uri = manager.getUriForDownloadedFile(id);
            if (uri == null) {
                this.a(context);
            } else {
                Intent launchIntent = new Intent("android.intent.action.VIEW");
                launchIntent.setDataAndType(uri, manager.getMimeTypeForDownloadedFile(id));
                launchIntent.setFlags(268435456);

                try {
                    context.startActivity(launchIntent);
                } catch (ActivityNotFoundException var10) {
                    this.a(context);
                }

            }
        } else {
            this.a(context);
        }
    }

    private void a(Context context) {
        Intent pageView = new Intent("android.intent.action.VIEW_DOWNLOADS");
        pageView.setFlags(268435456);
        context.startActivity(pageView);
    }

    private void c(Context context, Intent intent) {
        DownloadManager dm = (DownloadManager) context.getSystemService("download");
        long id = intent.getLongExtra("extra_download_id", -1L);
        if (id > 0L) {
            String mimeType = dm.getMimeTypeForDownloadedFile(id);
            Uri uri = dm.getUriForDownloadedFile(id);
            if (uri != null) {
                String url = uri.toString();
                Intent broadcast = new Intent("UZMAP.DOWNLOAD.COMPLETE");
                intent.setPackage(context.getPackageName());
                intent.putExtra("id", id);
                intent.putExtra("mimeType", mimeType);
                intent.putExtra("url", url);
                context.sendBroadcast(broadcast);
            }
        }
    }

    private void d(Context context, Intent intent) {
        com.uzmap.pkg.uzcore.external.aa.e.b(context);
        Alarm alarm = null;
        byte[] data = intent.getByteArrayExtra("intent.extra.alarm_raw");
        if (data != null) {
            Parcel in = Parcel.obtain();
            in.unmarshall(data, 0, data.length);
            in.setDataPosition(0);
            alarm = Alarm.CREATOR.createFromParcel(in);
        }

        if (alarm == null) {
            com.uzmap.pkg.uzcore.external.aa.d.b(context);
        } else {
            com.uzmap.pkg.uzcore.external.aa.d.b(context, alarm.a);
            if (!alarm.e.b()) {
                com.uzmap.pkg.uzcore.external.aa.d.a(context, alarm.a, false);
            } else {
                com.uzmap.pkg.uzcore.external.aa.d.b(context);
            }

            long now = System.currentTimeMillis();
            if (now <= alarm.f + 1800000L) {
                String title = null;
                String content = "新的提醒";
                String extra = "{}";
                String label = alarm.h;
                boolean openApp = false;
                if (!TextUtils.isEmpty(label)) {
                    try {
                        JSONObject notify = new JSONObject(label);
                        title = notify.optString("title");
                        content = notify.optString("content", "新的提醒");
                        extra = notify.optString("extra");
                        openApp = notify.optBoolean("openApp", false);
                    } catch (Exception var13) {
                    }
                }

                d.a option = new d.a();
                option.a = title;
                option.b = content;
                option.c = extra;
                option.f = true;
                option.g = openApp;
                d.a().a(context, option);
            }
        }
    }
}
