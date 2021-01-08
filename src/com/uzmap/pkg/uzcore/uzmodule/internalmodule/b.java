package com.uzmap.pkg.uzcore.uzmodule.internalmodule;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.*;
import android.content.DialogInterface.OnDismissListener;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.MediaStore.Images.Media;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import com.uzmap.pkg.a.a.d;
import com.uzmap.pkg.a.a.e;
import com.uzmap.pkg.uzapp.UPMessage;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzcore.external.n;
import com.uzmap.pkg.uzcore.external.q;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.aa.h;
import com.uzmap.pkg.uzcore.uzmodule.aa.j;
import com.uzmap.pkg.uzcore.uzmodule.aa.k;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import com.uzmap.pkg.uzkit.fineHttp.RequestParam;
import com.uzmap.pkg.uzkit.fineHttp.UZHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

public class b {
    private UZCoreModule a;
    private n b;
    private q c;
    private UZModuleContext d;
    private String e;
    private com.uzmap.pkg.a.a.b f;
    private e g;
    private com.uzmap.pkg.a.a.a.a1 h;
    private UPMessage i;

    public b(UZCoreModule module) {
        this.a = module;
    }

    protected void a(k moduleArgs) {
        String packagename = moduleArgs.optString("androidPkg");
        JSONObject appParam = moduleArgs.optJSONObject("appParam");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setPackage(packagename);
        if (UZCoreUtil.appExist(intent)) {
            PackageManager pkgmgr = this.a.getContext().getPackageManager();
            intent = pkgmgr.getLaunchIntentForPackage(packagename);
            if (appParam != null) {
                this.a(intent, appParam);
            }

            intent.setFlags(805306368);

            try {
                this.a.startActivityForResult(intent, 2000001);
                this.d = moduleArgs;
            } catch (Exception var9) {
                Toast.makeText(this.a.getContext(), "not find any app", 0).show();
            }

        } else {
            String mimeType = moduleArgs.optString("mimeType");
            String uri = moduleArgs.optString("uri");
            packagename = !TextUtils.isEmpty(packagename) ? packagename : "android.intent.action.VIEW";
            intent = new Intent(packagename);
            if (!TextUtils.isEmpty(uri) && uri.startsWith("intent")) {
                try {
                    intent = Intent.parseUri(uri, 0);
                } catch (URISyntaxException var12) {
                    var12.printStackTrace();
                }
            } else {
                Uri u;
                if (!TextUtils.isEmpty(uri) && !TextUtils.isEmpty(mimeType)) {
                    u = Uri.parse(uri);
                    intent.setDataAndType(u, mimeType);
                } else {
                    if (!TextUtils.isEmpty(uri)) {
                        u = Uri.parse(uri);
                        intent.setData(u);
                    }

                    if (!TextUtils.isEmpty(mimeType)) {
                        intent.setType(mimeType);
                    }
                }
            }

            if (appParam != null) {
                this.a(intent, appParam);
            }

            if (intent != null && UZCoreUtil.appExist(intent)) {
                intent.setFlags(805306368);

                try {
                    this.a.startActivityForResult(Intent.createChooser(intent, "请选择:"), 2000001);
                    this.d = moduleArgs;
                } catch (Exception var10) {
                    Toast.makeText(this.a.getContext(), "not find any app", 0).show();
                }

            } else {
                JSONObject result = new JSONObject();

                try {
                    result.put("msg", "not find any app");
                    moduleArgs.error(null, result, true);
                } catch (Exception var11) {
                }

            }
        }
    }

    private void a(Intent intent, JSONObject json) {
        try {
            Iterator keys = json.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = json.optString(key);
                if (key != null && value != null) {
                    intent.putExtra(key, value);
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    protected void a(String number, boolean callnow) {
        if (!TextUtils.isEmpty(number)) {
            try {
                Intent intent = new Intent();
                if (callnow) {
                    intent.setAction("android.intent.action.CALL");
                } else {
                    intent.setAction("android.intent.action.DIAL");
                }

                intent.setData(Uri.parse("tel:" + number));
                intent.setFlags(268435456);
                this.a.startActivity(intent);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    protected void b(k moduleContext) {
        String text = moduleContext.optString("text");
        JSONArray numbers = moduleContext.optJSONArray("numbers");
        boolean silent = moduleContext.optBoolean("silent");
        String error;
        if (!silent) {
            Uri uri = null;
            error = "";
            if (numbers != null && numbers.length() > 0) {
                error = numbers.optString(0);
            }

            text = text != null ? text : "";
            uri = Uri.parse("smsto:" + error);
            Intent intent = new Intent("android.intent.action.SENDTO", uri);
            intent.putExtra("sms_body", text);
            this.a.startActivityForResult(intent, 2000007);
            this.d = moduleContext;
        } else {
            boolean status = true;
            error = "";
            if (numbers == null || numbers.length() == 0 || TextUtils.isEmpty(text)) {
                status = false;
                error = "text or numbers can not be empty";
            }

            if (!UZCoreUtil.SIMCardReady()) {
                status = false;
                error = "SIM card do not work";
            }

            SmsManager smsManager = null;

            try {
                smsManager = SmsManager.getDefault();
            } catch (Exception var22) {
            }

            if (smsManager == null) {
                status = false;
                error = "can not get device smsManager";
            }

            if (!status) {
                JSONObject json = new JSONObject();

                try {
                    json.put("status", status);
                    json.put("msg", error);
                } catch (Exception var20) {
                    var20.printStackTrace();
                }

                moduleContext.success(json, true);
            } else {
                if (this.i == null) {
                    this.i = new UPMessage(moduleContext);
                }

                this.i.a(moduleContext);
                Context context = this.a.getContext();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("APICLOUD.SMS.SEND");
                intentFilter.addAction("APICLOUD.SMS.DELIVERED");
                context.registerReceiver(this.i, intentFilter);
                Intent intent = new Intent("APICLOUD.SMS.SEND");
                PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                intent = new Intent("APICLOUD.SMS.DELIVERED");
                PendingIntent deliverIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                ArrayList<String> messages = smsManager.divideMessage(text);
                int length = numbers.length();
                boolean exception = false;

                for (int i = 0; i < length; ++i) {
                    String number = numbers.optString(i);
                    if (!TextUtils.isEmpty(number)) {
                        try {
                            Iterator var19 = messages.iterator();

                            while (var19.hasNext()) {
                                String msg = (String) var19.next();
                                smsManager.sendTextMessage(number, null, msg, sentIntent, deliverIntent);
                            }
                        } catch (Exception var23) {
                            var23.printStackTrace();
                            exception = true;
                        }
                    }
                }

                if (exception) {
                    JSONObject json = new JSONObject();

                    try {
                        json.put("status", false);
                        json.put("msg", "exception");
                    } catch (Exception var21) {
                        var21.printStackTrace();
                    }

                    moduleContext.success(json, true);
                }

            }
        }
    }

    protected void a(int resultCode, Intent result) {
        if (this.d != null) {
            boolean status = true;
            if (-1 != resultCode) {
                status = false;
            }

            JSONObject json = new JSONObject();

            try {
                json.put("status", status);
                this.d.success(json, true);
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            this.d = null;
        }
    }

    protected void a(UZModuleContext moduleContext) {
        JSONArray recipients = moduleContext.optJSONArray("recipients");
        JSONArray attachments = moduleContext.optJSONArray("attachments");
        String subject = moduleContext.optString("subject");
        String body = moduleContext.optString("body");
        if (recipients != null && attachments != null) {
            int rl = recipients.length();
            String[] addresses = new String[rl];

            int al;
            for (al = 0; al < rl; ++al) {
                addresses[al] = recipients.optString(al);
            }

            al = attachments.length();
            String[] atta = new String[al];

            for (int i = 0; i < rl; ++i) {
                String at = attachments.optString(i);
                if (!TextUtils.isEmpty(at)) {
                    atta[i] = at;
                }
            }

            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setPackage("com.android.email");
                boolean mailAppExist = true;
                if (!UZCoreUtil.appExist(intent)) {
                    intent.setPackage("com.google.android.gm");
                    if (!UZCoreUtil.appExist(intent)) {
                        mailAppExist = false;
                    }
                }

                if (!mailAppExist) {
                    intent.setPackage(null);
                }

                intent.putExtra("android.intent.extra.EMAIL", addresses);
                intent.putExtra("android.intent.extra.TEXT", body);
                intent.putExtra("android.intent.extra.SUBJECT", subject);
                ArrayList<Uri> imageUris = new ArrayList();
                String[] var16 = atta;
                int var15 = atta.length;

                for (int var14 = 0; var14 < var15; ++var14) {
                    String attchment = var16[var14];
                    String fullPath = this.a.makeRealPath(attchment);
                    File f = new File(fullPath);
                    if (f.exists()) {
                        if (!fullPath.startsWith("file://")) {
                            fullPath = "file://" + fullPath;
                        }

                        imageUris.add(Uri.parse(fullPath));
                    }
                }

                if (imageUris.size() > 0) {
                    intent.putExtra("android.intent.extra.STREAM", imageUris.get(0));
                }

                intent.setType("*/*");
                this.a.startActivity(Intent.createChooser(intent, "选择发送邮件程序"));
            } catch (Exception var19) {
                var19.printStackTrace();
            }

        }
    }

    protected void c(final k moduleContext) {
        int type = UZConstant.mapInt(moduleContext.optString("type"), 0);
        String defaultDate = moduleContext.optString("date");
        String title = moduleContext.optString("title");
        long defaultTime = UZCoreUtil.parseDateToMills(defaultDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(defaultTime);
        final int year = calendar.get(1);
        final int month = calendar.get(2);
        final int day = calendar.get(5);
        final int hour = calendar.get(11);
        final int minut = calendar.get(12);
        if (type != 0 && 2 != type) {
            if (1 == type) {
                if (this.c != null) {
                    moduleContext.error(null, this.a(0, "picker has opened!"), true);
                    return;
                }

                this.c = new q(this.a.getContext(), new OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        JSONObject json = new JSONObject();

                        try {
                            json.put("year", year);
                            json.put("month", month);
                            json.put("day", day);
                            json.put("hour", hourOfDay);
                            json.put("minute", minute);
                        } catch (Exception var6) {
                            var6.printStackTrace();
                        }

                        moduleContext.success(json, true);
                        b.this.c = null;
                    }
                }, hour, minut);
                this.c.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        b.this.c = null;
                    }
                });
                if (!TextUtils.isEmpty(title)) {
                    this.c.setTitle(title);
                }

                this.c.show();
            } else {
                moduleContext.error(null, this.a(0, "unknown case"), true);
            }
        } else {
            if (this.b != null) {
                moduleContext.error(null, this.a(0, "picker has opened!"), true);
                return;
            }

            this.b = new n(this.a.getContext(), new OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    JSONObject json = new JSONObject();

                    try {
                        json.put("year", year);
                        json.put("month", monthOfYear + 1);
                        json.put("day", dayOfMonth);
                        json.put("hour", hour);
                        json.put("minute", minut);
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }

                    moduleContext.success(json, true);
                    b.this.b = null;
                }
            }, year, month, day);
            this.b.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    b.this.b = null;
                }
            });
            if (!TextUtils.isEmpty(title)) {
                this.b.setTitle(title);
            }

            this.b.show();
        }

    }

    protected JSONObject a(String path, String charset) {
        String retData = "";

        try {
            InputStream input = UZUtility.guessInputStream(path);
            if (input == null) {
                return new JSONObject();
            } else {
                retData = UZCoreUtil.readString(input, charset);
                input.close();
                JSONObject json = new JSONObject();
                json.put("data", retData);
                json.put("status", true);
                return json;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            return new JSONObject();
        }
    }

    protected JSONObject a(String path, String data, boolean append) {
        JSONObject json = new JSONObject();

        try {
            File file = new File(path);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
            writer.write(data);
            writer.flush();
            writer.close();
            json.put("status", true);
            return json;
        } catch (Exception var9) {
            Exception e = var9;
            var9.printStackTrace();

            try {
                json.put("status", false);
                json.put("msg", e.getMessage());
            } catch (JSONException var8) {
                var8.printStackTrace();
            }

            return json;
        }
    }

    protected void a(j mContext) {
        if (this.d == null) {
            if (this.c()) {
                this.h();
                Intent intent;
                Uri data;
                switch (mContext.a) {
                    case 0:
                    case 2:
                        intent = new Intent("android.intent.action.PICK");
                        if (mContext.c == 2) {
                            String type;
                            if (l.a < 11) {
                                type = "*/*;image/*;video/*";
                            } else {
                                type = "image/*,video/*";
                            }

                            intent.setType(type);
                        } else {
                            data = Media.EXTERNAL_CONTENT_URI;
                            String type = "vnd.android.cursor.dir/image";
                            if (mContext.c == 1) {
                                data = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                                type = "vnd.android.cursor.dir/video";
                            }

                            intent.setDataAndType(data, type);
                        }

                        if (!UZCoreUtil.appExist(intent)) {
                            intent.setData(Media.EXTERNAL_CONTENT_URI);
                        }

                        this.d = mContext;
                        this.a.startActivityForResult(intent, 2000003);
                        break;
                    case 1:
                        if (mContext.c == 1) {
                            intent = new Intent("android.media.action.VIDEO_CAPTURE");
                            intent.putExtra("android.intent.extra.videoQuality", 0);
                            intent.setFlags(4194304);
                            this.a.startActivityForResult(intent, 2000005);
                        } else {
                            intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            this.e = this.a(mContext.g);
                            data = Uri.fromFile(new File(this.e));
                            intent.putExtra("output", data);
                            intent.setFlags(4194304);
                            this.a.startActivityForResult(intent, 2000002);
                        }

                        this.d = mContext;
                }

            }
        }
    }

    protected void b(final UZModuleContext moduleContext) {
        String imgPath = moduleContext.optString("path");
        final String path = this.a.makeRealPath(imgPath);
        if (path == null) {
            JSONObject json = new JSONObject();

            try {
                json.put("status", false);
                json.put("msg", "path can not be empty");
            } catch (Exception var6) {
            }

            moduleContext.success(json, true);
        } else {
            (new Thread() {
                public void run() {
                    b.this.a(path, moduleContext);
                }
            }).start();
        }
    }

    private String a(boolean saveToPhotoAlbum) {
        String appId = this.a.getWidgetInfo().id;
        String rootPath = UZFileSystem.get().getWidgetCachePath(appId);
        if (saveToPhotoAlbum) {
            rootPath = UZUtility.getCameraStoragePath();
        }

        return rootPath + "p-" + UZCoreUtil.random() + ".jpg";
    }

    private String e() {
        return this.g() + "p-" + UZCoreUtil.random() + ".jpg";
    }

    private String f() {
        String appId = this.a.getWidgetInfo().id;
        return UZFileSystem.get().getWidgetMediaPath(appId);
    }

    private String g() {
        String appId = this.a.getWidgetInfo().id;
        return UZFileSystem.get().getWidgetPicturePath(appId);
    }

    private void h() {
        String picTemp = this.f();
        File temp = new File(picTemp);
        if (!temp.exists()) {
            temp.mkdirs();
        } else {
            File[] files = temp.listFiles();
            if (files.length >= 30) {
                File[] var7 = files;
                int var6 = files.length;

                for (int var5 = 0; var5 < var6; ++var5) {
                    File file = var7[var5];
                    file.delete();
                }
            }
        }

    }

    protected void b(int resultCode, Intent result) {
        if (this.d != null) {
            int duration = 0;
            String data = "";
            String base64Data = "";
            j moduleContext = (j) this.d;
            if (-1 != resultCode) {
                data = "";
            } else {
                String realPath;
                if (moduleContext.c != 1) {
                    realPath = "";
                    if (result != null) {
                        realPath = result.getDataString();
                        realPath = UZUtility.makeRealPath(realPath, this.a.getWidgetInfo());
                    } else {
                        realPath = this.e;
                        if (moduleContext.g) {
                            File cameraFile = new File(realPath);
                            if (cameraFile.exists()) {
                                Uri ur = Uri.fromFile(cameraFile);
                                this.a.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", ur));
                            }
                        }
                    }

                    int degree = com.uzmap.pkg.uzcore.external.g.a(realPath);
                    if (moduleContext.a() || degree > 0) {
                        realPath = this.a(realPath, moduleContext, degree);
                    }

                    if (moduleContext.b()) {
                        data = realPath;
                        base64Data = UZUtility.bitmapToBase64(realPath);
                    } else {
                        data = realPath;
                    }
                } else {
                    realPath = "";
                    if (result != null) {
                        realPath = result.getDataString();
                    } else {
                        realPath = this.e;
                    }

                    duration = UZUtility.guessVideoDuration(realPath);
                    data = UZUtility.makeRealPath(realPath, this.a.getWidgetInfo());
                }
            }

            if (data == null) {
                data = "";
            }

            try {
                JSONObject json = new JSONObject();
                json.put("data", data);
                json.put("base64Data", base64Data);
                json.put("duration", duration / 1000);
                moduleContext.success(json, true);
            } catch (Exception var10) {
                var10.printStackTrace();
            }

            if (moduleContext.d() && this.e != null) {
                (new File(this.e)).delete();
            }

            this.e = null;
            this.d = null;
        }
    }

    protected void d(k moduleContext) {
        if (this.d == null) {
            Intent intent = l.a();
            if (!UZCoreUtil.appExist(intent)) {
                Toast.makeText(this.a.getContext(), "未找到通讯录程序!", 0).show();
            } else {
                this.a.startActivityForResult(intent, 2000006);
                this.d = moduleContext;
            }
        }
    }

    protected void c(int resultCode, Intent data) {
        if (this.d != null) {
            JSONObject result = this.a(this.a.getContext(), data);
            this.d.success(result, true);
            this.d = null;
        }
    }

    public void e(k moduleContext) {
        if (this.c()) {
            String path = moduleContext.optString("path");
            if (!TextUtils.isEmpty(path)) {
                path = UZUtility.makeRealPath(path, this.a.getWidgetInfo());
            } else {
                path = this.i();
            }

            if (this.f == null) {
                this.f = new com.uzmap.pkg.a.a.b();
            }

            this.f.a(path);
        }
    }

    public void f(k moduleContext) {
        if (this.f != null) {
            this.f.b();
            String path = this.f.c();
            int duration = this.f.d();
            JSONObject json = new JSONObject();

            try {
                json.put("path", path);
                json.put("duration", duration);
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            moduleContext.success(json, true);
        }

    }

    public void g(final k moduleContext) {
        String path = moduleContext.optString("path");
        if (!TextUtils.isEmpty(path)) {
            path = UZUtility.makeRealPath(path, this.a.getWidgetInfo());
            if (this.f == null) {
                this.f = new com.uzmap.pkg.a.a.b();
            }

            this.f.a(new d() {
                public void a(String msg) {
                    JSONObject json = new JSONObject();

                    try {
                        json.put("state", 0);
                        json.put("msg", msg);
                    } catch (Exception var4) {
                        var4.printStackTrace();
                    }

                    moduleContext.success(json, true);
                }

                public void a() {
                    JSONObject json = new JSONObject();

                    try {
                        json.put("state", 1);
                    } catch (Exception var3) {
                        var3.printStackTrace();
                    }

                    moduleContext.success(json, true);
                }
            });
            this.f.a(this.a.getContext(), path);
        } else {
            JSONObject error = new JSONObject();

            try {
                error.put("status", false);
                error.put("msg", "path not valid");
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            moduleContext.error(null, error, true);
        }
    }

    public void a() {
        if (this.f != null) {
            this.f.a();
        }

    }

    public void h(final k moduleContext) {
        if (this.a != null) {
            int accuracy = UZConstant.mapInt(moduleContext.optString("accuracy"), 0);
            float filter = (float) moduleContext.optDouble("filter");
            final boolean autoStop = moduleContext.optBoolean("autoStop", true);
            this.b();
            this.h = new com.uzmap.pkg.a.a.a.a1() {
                public void a(JSONObject location) {
                    moduleContext.success(location, autoStop);
                }
            };
            com.uzmap.pkg.a.a.a.c option = new com.uzmap.pkg.a.a.a.c(accuracy, filter, autoStop);
            com.uzmap.pkg.a.a.a.a(this.a.getContext()).a(option, this.h);
        }
    }

    public void b() {
        if (this.a != null) {
            if (this.h != null) {
                com.uzmap.pkg.a.a.a.a(this.a.getContext()).b(this.h);
                this.h = null;
            }

        }
    }

    public void i(final k moduleContext) {
        this.b();
        this.h = new com.uzmap.pkg.a.a.a.a1() {
            public void a(JSONObject location) {
                moduleContext.success(location, true);
            }
        };
        com.uzmap.pkg.a.a.a.a(this.a.getContext()).a(this.h);
    }

    public void a(String args) {
    }

    public void j(k moduleContext) {
        int type = UZConstant.mapInt(moduleContext.optString("type"), 0);
        if (this.g == null) {
            this.g = new e();
        }

        boolean ok = this.g.a(type, -1, moduleContext);
        if (!ok) {
            JSONObject error = new JSONObject();

            try {
                error.put("status", false);
                error.put("msg", "Hardware did not supported!");
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            moduleContext.error(null, error, true);
        }

    }

    public void k(k moduleContext) {
        int type = UZConstant.mapInt(moduleContext.optString("type"), 0);
        if (this.g != null) {
            this.g.a(type);
        }

    }

    public void a(com.uzmap.pkg.uzcore.uzmodule.aa.l moduleContext) {
        int nid = com.uzmap.pkg.a.a.c.a().a(moduleContext);
        if (moduleContext.b()) {
            JSONObject json = new JSONObject();

            try {
                json.put("id", nid);
                if (nid < 0) {
                    json.put("msg", "Alarm Exception!");
                }
            } catch (Exception var5) {
            }

            if (nid >= 0) {
                moduleContext.success(json, true);
            } else {
                moduleContext.error(null, json, true);
            }
        }

    }

    public void b(com.uzmap.pkg.uzcore.uzmodule.aa.l moduleContext) {
        int nid = moduleContext.optInt("id");
        com.uzmap.pkg.a.a.c.a().a(nid);
    }

    public void a(k moduleContext, String targetId) {
        long length = 0L;
        String state = Environment.getExternalStorageState();
        if ("mounted".equals(state)) {
            Context context = com.uzmap.pkg.uzcore.b.a().b();
            File boxcache = context.getCacheDir();
            if (boxcache != null) {
                length += UZCoreUtil.computeDirOrFileSize(boxcache);
            }

            File extcache = context.getExternalCacheDir();
            if (extcache != null) {
                length += UZCoreUtil.computeDirOrFileSize(extcache);
            }

            if (targetId != null) {
                length += UZFileSystem.get().computeCacheSize(targetId);
            }
        } else {
            if (!"mounted".equals(state)) {
                length = -1L;
            }

            if ("checking".equals(state)) {
                length = -2L;
            }
        }

        JSONObject json = new JSONObject();

        try {
            json.put("size", length);
        } catch (Exception var9) {
        }

        moduleContext.success(json, true);
    }

    public void a(h moduleContext, UZWidgetInfo info) {
        if (!moduleContext.a()) {
            RequestParam argument = moduleContext.c();
            UZHttpClient.get().execute(argument, moduleContext);
        }
    }

    protected void d(int resultCode, Intent result) {
        if (this.d != null) {
            JSONObject json = new JSONObject();
            if (result != null) {
                Bundle allExtras = result.getExtras();
                if (allExtras != null) {
                    Set<String> keys = allExtras.keySet();
                    Iterator var7 = keys.iterator();

                    label57:
                    while (true) {
                        while (true) {
                            if (!var7.hasNext()) {
                                break label57;
                            }

                            String key = (String) var7.next();
                            Object value = allExtras.get(key);
                            if (value.getClass().isArray()) {
                                Object[] array = (Object[]) value;
                                JSONArray jsa = new JSONArray();
                                Object[] var14 = array;
                                int var13 = array.length;

                                for (int var12 = 0; var12 < var13; ++var12) {
                                    Object a = var14[var12];

                                    try {
                                        jsa.put(a);
                                    } catch (Exception var19) {
                                        var19.printStackTrace();
                                    }
                                }

                                try {
                                    json.put(key, jsa);
                                } catch (JSONException var18) {
                                    var18.printStackTrace();
                                }
                            } else {
                                try {
                                    json.put(key, value);
                                } catch (JSONException var17) {
                                    var17.printStackTrace();
                                }
                            }
                        }
                    }
                }

                String data = result.getDataString();
                if (data != null) {
                    try {
                        json.put("uri", data);
                    } catch (JSONException var16) {
                        var16.printStackTrace();
                    }
                }
            }

            this.d.success(json, true);
        }

        this.d = null;
    }

    private String a(String file, j mediaContext, int degree) {
        try {
            int maxNumOfPixels = -1;
            int quality = 100;
            if (mediaContext.c()) {
                maxNumOfPixels = mediaContext.h * mediaContext.i;
                quality = 95;
            }

            String newPath = this.e();
            File newFile = new File(newPath);
            File p = newFile.getParentFile();
            if (p != null && !p.exists()) {
                p.mkdirs();
            }

            if (degree > 0 && maxNumOfPixels < 0) {
                Options options = new Options();

                try {
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file, options);
                } catch (Exception var12) {
                }

                int sPix = com.uzmap.pkg.uzcore.d.a().k * com.uzmap.pkg.uzcore.d.a().m;
                int outPix = options.outWidth * options.outHeight / 2;
                if (outPix >= sPix) {
                    maxNumOfPixels = outPix;
                } else {
                    maxNumOfPixels = sPix;
                }

                quality = 95;
            }

            Bitmap picture = UZUtility.makeBitmap(file, maxNumOfPixels);
            if (picture != null && degree > 0) {
                picture = com.uzmap.pkg.uzcore.external.g.a(picture, degree);
            }

            if (picture != null) {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
                quality = mediaContext.f > 0 ? mediaContext.f : quality;
                picture.compress(CompressFormat.JPEG, quality, bos);
                bos.flush();
                bos.close();
                picture.recycle();
                return newPath;
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }

        return file.toString();
    }

    private String i() {
        return this.f() + "a-" + UZCoreUtil.random() + ".amr";
    }

    protected boolean c() {
        long as = UZCoreUtil.getAvailableSpace();
        if (as < 1L) {
            String noStorageText = null;
            if (as == -1L) {
                noStorageText = "请先插入 SD 卡。";
            } else if (as == -2L) {
                noStorageText = "正在准备 USB 存储设备...";
            } else if (as == -3L) {
                noStorageText = "无法访问 SD 卡。";
            } else if (as < 1L) {
                noStorageText = "SD 卡已满。";
            }

            String finalNoStorageText = noStorageText;
            Runnable action = new Runnable() {
                public void run() {
                    Toast.makeText(b.this.a.getContext(), finalNoStorageText, 1).show();
                }
            };
            this.a.runOnUiThread(action);
            return false;
        } else {
            return true;
        }
    }

    private JSONObject a(int status, String msg) {
        JSONObject json = new JSONObject();

        try {
            json.put("status", status);
            json.put("msg", msg);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return json;
    }

    public void a(Activity a, int color) {
        l.a(a, color);
    }

    private void a(String file, UZModuleContext jsCallback) {
        boolean status = true;
        String msg = "";
        String cameraPath = UZUtility.getCameraStoragePath();
        String filename = URLUtil.guessFileName(file, "", "");
        if (TextUtils.isEmpty(filename)) {
            filename = "p-" + UZCoreUtil.random() + ".jpg";
        }

        String cameraFile = cameraPath + filename;
        InputStream inputStream = null;

        try {
            inputStream = UZUtility.guessInputStream(file);
        } catch (Exception var15) {
        }

        if (inputStream == null) {
            status = false;
            msg = "file not found";
        } else {
            try {
                File target = new File(cameraFile);
                if (target.exists()) {
                    target.delete();
                }

                OutputStream out = new FileOutputStream(target);
                byte[] buf = new byte[8192];

                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                inputStream.close();
                out.close();
                Uri ur = Uri.fromFile(target);
                this.a.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", ur));
            } catch (Exception var16) {
                var16.printStackTrace();
                status = false;
                msg = var16.getMessage();
            }
        }

        JSONObject json = new JSONObject();

        try {
            json.put("status", status);
            json.put("msg", msg);
        } catch (Exception var14) {
        }

        jsCallback.success(json, true);
    }

    private JSONObject a(Context context, Intent data) {
        String name = "";
        String phone = "";
        boolean status = true;
        if (data != null) {
            Uri content = data.getData();
            if (content != null) {
                ContentResolver crls = context.getContentResolver();
                Cursor cursor = null;
                String idKey = "_id";
                String nameKey = "display_name";
                String numberKey = "data1";
                if (l.a < 11) {
                    cursor = crls.query(content, null, null, null, null);
                    nameKey = "display_name";
                    numberKey = "data1";
                } else {
                    String id = null;
                    id = content.getLastPathSegment();
                    String[] projection = new String[]{idKey, nameKey, numberKey};
                    String selection = idKey + "=?";
                    String[] selectionArgs = new String[]{id};
                    cursor = crls.query(content, projection, selection, selectionArgs, null);
                }

                if (cursor != null && cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(idKey);
                    int nameIndex = cursor.getColumnIndex(nameKey);
                    int phoneIndex = cursor.getColumnIndex(numberKey);
                    if (nameIndex > 0) {
                        name = cursor.getString(nameIndex);
                    }

                    if (phoneIndex > 0) {
                        phone = cursor.getString(phoneIndex);
                    } else {
                        String contactId = cursor.getString(idIndex);
                        Cursor c = crls.query(Phone.CONTENT_URI, null, "contact_id=" + contactId, null, null);
                        if (c != null && c.moveToFirst()) {
                            phone = c.getString(c.getColumnIndex(numberKey));
                        }

                        if (c != null) {
                            c.close();
                        }
                    }
                }

                if (cursor != null) {
                    cursor.close();
                }
            }

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                status = false;
            }
        }

        JSONObject result = new JSONObject();

        try {
            result.put("status", status);
            result.put("name", name);
            result.put("phone", phone);
        } catch (Exception var17) {
        }

        return result;
    }

    protected void d() {
        if (this.f != null) {
            this.f.e();
            this.f = null;
        }

        if (this.g != null) {
            this.g.a();
            this.g = null;
        }

        this.b();
        if (this.i != null) {
            Context context = this.a != null ? this.a.getContext() : null;
            if (context != null) {
                try {
                    context.unregisterReceiver(this.i);
                } catch (Exception var3) {
                }
            }

            this.i.a();
            this.i = null;
        }

        this.d = null;
        this.e = null;
    }
}
