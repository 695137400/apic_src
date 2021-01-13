package com.uzmap.pkg.a.intent;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Toast;
import com.uzmap.pkg.uzapp.e;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpHead;

import java.io.IOException;

public class a {
    public static void a(Activity activity, String url, String userAgent, String contentDisposition, String mimetype) {
        if (contentDisposition == null || !contentDisposition.regionMatches(true, 0, "attachment", 0, 10)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(url), mimetype);
            ResolveInfo info = activity.getPackageManager().resolveActivity(intent, 65536);
            if (info != null) {
                ComponentName myName = activity.getComponentName();
                if (!myName.getPackageName().equals(info.activityInfo.packageName) || !myName.getClassName().equals(info.activityInfo.name)) {
                    try {
                        activity.startActivity(intent);
                        return;
                    } catch (ActivityNotFoundException var9) {
                        var9.printStackTrace();
                    }
                }
            }
        }

        b(activity, url, userAgent, contentDisposition, mimetype);
    }

    static void b(Activity activity, String url, String userAgent, String contentDisposition, String mimetype) {
        String filename = URLUtil.guessFileName(url, contentDisposition, mimetype);
        String status = Environment.getExternalStorageState();
        if (!status.equals("mounted")) {
            String title;
            String msg;
            if (status.equals("shared")) {
                msg = "SD 卡正忙。要允许下载，请在通知中触摸“关闭 USB 存储设备”。";
                title = "SD 卡不可用";
            } else {
                msg = "需要有 SD 卡才能下载";
                title = "无 SD 卡";
            }

            (new Builder(activity)).setTitle(title).setIcon(17301543).setMessage(msg).setPositiveButton("确定", null).show();
        } else {
            Uri uri = Uri.parse(url);

            final Request request;
            try {
                request = new Request(uri);
            } catch (IllegalArgumentException var12) {
                Toast.makeText(activity, "只能从“http”或“https”网址下载。", 0).show();
                return;
            }

            request.setMimeType(mimetype);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
            request.allowScanningByMediaScanner();
            request.setDescription(uri.getHost());
            String cookies = e.a().a(url);
            request.addRequestHeader("cookie", cookies);
            request.setNotificationVisibility(1);
            if (mimetype == null) {
                if (TextUtils.isEmpty(url)) {
                    return;
                }

                (new at(activity, request, url, cookies, userAgent)).start();
            } else {
                final DownloadManager manager = (DownloadManager) activity.getSystemService("download");
                (new Thread("APICloud download") {
                    public void run() {
                        manager.enqueue(request);
                    }
                }).start();
            }

            Toast.makeText(activity, "正在开始下载...", 0).show();
        }
    }

    static class at extends Thread {
        private Context a;
        private Request b;
        private String c;
        private String d;
        private String e;

        public at(Context context, Request request, String uri, String cookies, String userAgent) {
            this.a = context.getApplicationContext();
            this.b = request;
            this.c = uri;
            this.d = cookies;
            this.e = userAgent;
        }

        public void run() {
            AndroidHttpClient client = AndroidHttpClient.newInstance(this.e);
            HttpHead request = new HttpHead(this.c);
            if (this.d != null && this.d.length() > 0) {
                request.addHeader("Cookie", this.d);
            }

            String mimeType = null;
            String contentDisposition = null;

            try {
                HttpResponse response = client.execute(request);
                if (response.getStatusLine().getStatusCode() == 200) {
                    Header header = response.getFirstHeader("Content-Type");
                    if (header != null) {
                        mimeType = header.getValue();
                        int semicolonIndex = mimeType.indexOf(59);
                        if (semicolonIndex != -1) {
                            mimeType = mimeType.substring(0, semicolonIndex);
                        }
                    }

                    Header contentDispositionHeader = response.getFirstHeader("Content-Disposition");
                    if (contentDispositionHeader != null) {
                        contentDisposition = contentDispositionHeader.getValue();
                    }
                }
            } catch (IllegalArgumentException var12) {
                if (request != null) {
                    request.abort();
                }
            } catch (IOException var13) {
                if (request != null) {
                    request.abort();
                }
            } finally {
                client.close();
            }

            if (mimeType != null) {
                String newMimeType;
                if (mimeType.equalsIgnoreCase("text/plain") || mimeType.equalsIgnoreCase("application/octet-stream")) {
                    newMimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(this.c));
                    if (newMimeType != null) {
                        mimeType = newMimeType;
                        this.b.setMimeType(newMimeType);
                    }
                }

                newMimeType = URLUtil.guessFileName(this.c, contentDisposition, mimeType);
                this.b.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, newMimeType);
            }

            DownloadManager manager = (DownloadManager) this.a.getSystemService("download");
            manager.enqueue(this.b);
        }
    }
}
