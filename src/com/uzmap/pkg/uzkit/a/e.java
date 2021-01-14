package com.uzmap.pkg.uzkit.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.uzmap.pkg.uzapp.PropertiesUtil;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.n;
import com.uzmap.pkg.uzcore.uzmodule.ApiConfig;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.fineHttp.*;
import com.uzmap.pkg.uzsocket.UPnsService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class e extends Handler {
    private static e j;
    private final Context a;
    private Activity b;
    private boolean c;
    private final com.uzmap.pkg.uzcore.b d;
    private final com.uzmap.pkg.uzkit.a.aa.a e;
    private final c f;
    private ProgressDialog g;
    private f h;
    private List<d> i;

    private e(Context context) {
        super(Looper.getMainLooper());
        this.a = context;
        this.d = com.uzmap.pkg.uzcore.b.a();
        this.f = com.uzmap.pkg.uzkit.a.c.a(context);
        this.e = com.uzmap.pkg.uzkit.a.aa.a.a(context);
    }

    public static e a(Context context) {
        if (j == null) {
            j = new e(context);
        }

        return j;
    }

    public void a(Activity activity) {
        this.b = activity;
    }

    public final void a(ApiConfig wgtInfo) {
        com.uzmap.pkg.uzkit.a.b.a("UZPlatform DisPatchAppResume");
        this.e.b();
    }

    public final void b(ApiConfig wgtInfo) {
        com.uzmap.pkg.uzkit.a.b.a("UZPlatform DisPatchAppPause");
        this.e.a(wgtInfo);
    }

    public final void a() {
        com.uzmap.pkg.uzkit.a.b.a("UZPlatform DisPatchAppFinish");
        this.e.c();
    }

    private final boolean b() {
        return this.c;
    }

    private final void a(boolean flag) {
        this.c = flag;
        if (flag && this.h != null) {
            this.h.a();
        }

    }

    public void a(f callback) {
        this.h = callback;
    }

    public final void c(ApiConfig wgtInfo) {
        if (this.d.j()) {
            this.f(wgtInfo);
        }

        if (this.d.i()) {
            this.h(wgtInfo);
        }

        if (this.d.h() && !this.d.o()) {
            this.e(wgtInfo);
        }

    }

    private void e(final ApiConfig wgtInfo) {
        (new Thread("##Thread-" + Thread.currentThread().getId() + "##") {
            public void run() {
                while (e.this.b()) {
                    try {
                        Thread.sleep(3000L);
                    } catch (Exception var11) {
                        var11.printStackTrace();
                    }
                }

                String widgetId = wgtInfo.g();
                String appVersion = e.this.f.k;
                String host = com.uzmap.pkg.uzkit.a.c.d;
                RequestParam argument = new RequestParam();
                argument.setUrl(host);
                argument.setMethod(1);
                argument.setResultDataType(0);
                argument.setEscape(false);
                argument.setInSecure(widgetId);
                argument.setRqValue("appVersion", appVersion);
                int incVersion = UZFileSystem.get().readIncrementVersion();
                argument.setRqValue("incNo", "" + incVersion);
                argument.setRqValue("systemType", e.this.f.p);
                String channel = com.uzmap.pkg.uzcore.d.a().a((String) null);
                if (channel != null) {
                    argument.setRqValue("channel", channel);
                }

                if (com.uzmap.pkg.uzkit.a.c.a) {
                    argument.setRqValue("aid", wgtInfo.r);
                }

                argument.setRqValue("pkg", e.this.f.r);
                argument.setRqValue("appName", e.this.f.l);
                if (e.this.f.s) {
                    argument.setRqValue("versionCode", "" + e.this.f.n);
                }

                argument.setRqValue("reportTime", "" + System.currentTimeMillis());
                JSONObject deviceInfo = e.this.i(wgtInfo);
                argument.setRqValue("deviceInfo", deviceInfo.toString());
                argument.setRqValue("runningTimeInfo", e.this.e.d());
                RequestListener listener = new RequestListener() {
                    public void onResult(Response result) {
                        if (result.success()) {
                            com.uzmap.pkg.uzkit.a.b.a("start report ok! result: " + result);
                            e.this.a(result.content, wgtInfo);
                        } else {
                            com.uzmap.pkg.uzkit.a.b.b("start report faild! result: " + result);
                        }

                    }
                };
                UZHttpClient.get().execute(argument, listener);

                try {
                    Thread.sleep(2000L);
                } catch (Exception var10) {
                    var10.printStackTrace();
                }

                e.this.d(wgtInfo);
            }
        }).start();
    }

    private void f(ApiConfig wgtInfo) {
        String widgetId = wgtInfo.r;
        String host = com.uzmap.pkg.uzkit.a.c.e;
        RequestParam argument = new RequestParam();
        argument.setUrl(host);
        argument.setMethod(1);
        argument.setEscape(false);
        argument.setResultDataType(0);
        argument.setInSecure(widgetId);
        String accessToken = com.uzmap.pkg.uzkit.data.a.a().b("ac_token", null);
        if (accessToken != null) {
            argument.setRqValue("accessToken", accessToken);
        }

        RequestListener callback = new RequestListener() {
            public void onResult(Response result) {
                e.this.a(false);
                if (result.success()) {
                    com.uzmap.pkg.uzkit.a.b.a("MSM Verification ok! result: " + result);
                } else {
                    com.uzmap.pkg.uzkit.a.b.a("MSM Verification faild! result: " + result);
                }

                e.this.a(result);
            }
        };
        this.a(true);
        UZHttpClient.get().execute(argument, callback);
    }

    public final void d(ApiConfig wgtInfo) {
        this.e.b(wgtInfo);
    }

    public void a(double lat, double log, String widgetId) {
        if (PropertiesUtil.o()) {
            widgetId = "A6965066952332";
        }

        this.e.a(lat, log, widgetId);
    }

    private void a(String result, ApiConfig wgtInfo) {
        if (!PropertiesUtil.o()) {
            JSONObject json = com.uzmap.pkg.uzkit.a.a.a(result);
            if (json != null) {
                int status = json.optInt("status");
                if (status == 0) {
                    int errorcode = json.optInt("errorCode");
                    if (errorcode >= 1) {
                    }

                    com.uzmap.pkg.uzkit.a.b.b("Report Server Error Status: " + errorcode);
                } else {
                    this.e.a(0);
                    JSONObject object = json.optJSONObject("result");
                    if (object != null) {
                        boolean push = object.optBoolean("push", true);
                        if (this.d.k()) {
                            this.a(push, wgtInfo);
                        }

                        if (wgtInfo.M) {
                            boolean update = object.optBoolean("update");
                            boolean closed = object.optBoolean("closed");
                            String version = object.optString("version");
                            String closeTip = object.optString("closeTip", "");
                            String msg = "本应用该版本不再使用";
                            closeTip = "null".equals(closeTip) ? msg : closeTip;
                            String updateTip = object.optString("updateTip", "");
                            msg = "有新版本啦";
                            updateTip = "null".equals(updateTip) ? msg : updateTip;
                            String source = object.optString("source");
                            String time = object.optString("time");
                            if (!update && !closed) {
                                this.a(wgtInfo, object);
                                this.g(wgtInfo);
                            } else if (update && !closed) {
                                this.a(version, updateTip, source, time, false);
                            } else if (update && closed) {
                                this.a(version, updateTip, source, time, true);
                            } else if (!update && closed) {
                                this.a(closeTip);
                            }

                        }
                    }
                }
            }
        }
    }

    private void g(ApiConfig wgtinfo) {
        if (wgtinfo.N && this.e()) {
            while (UZFileSystem.assetLocked()) {
                try {
                    Thread.sleep(500L);
                } catch (Exception var4) {
                    var4.printStackTrace();
                }
            }

            final d nextPackage = this.f();
            if (this.e()) {
                this.a(nextPackage);
            } else if (nextPackage.c) {
                this.a(nextPackage);
            } else {
                Runnable action = new Runnable() {
                    public void run() {
                        AlertDialog dia = e.this.g();
                        dia.setMessage("您有新的更新包");
                        dia.setTitle("增量更新");
                        dia.setCancelable(false);
                        dia.setButton(-1, "立即更新", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                e.this.a(nextPackage);
                            }
                        });
                        dia.setButton(-2, "取消", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dia.show();
                    }
                };
                this.post(action);
            }

        }
    }

    private void a(Response result) {
        if (!result.success()) {
            if (this.h != null) {
                this.h.a(false, null, "认证失败，网络或服务器错误!");
            }

        } else {
            JSONObject json = com.uzmap.pkg.uzkit.a.a.a(result.content);
            if (json == null) {
                if (this.h != null) {
                    this.h.a(false, null, "认证失败，网络或服务器错误!");
                }

            } else {
                int status = json.optInt("status");
                if (status == 0) {
                    if (this.h != null) {
                        this.h.a(false, null, "验证失败，服务器错误");
                    }

                } else {
                    JSONObject object = json.optJSONObject("result");
                    if (object == null) {
                        if (this.h != null) {
                            this.h.a(false, null, "验证失败，服务器错误");
                        }

                    } else {
                        int authStatus = object.optInt("authStatus", 0);
                        int authType = object.optInt("authType", 0);
                        if (authType == 0) {
                            if (this.h != null) {
                                this.h.a(true, null, "");
                            }

                        } else {
                            String accessToken = object.optString("accessToken", null);
                            if (!TextUtils.isEmpty(accessToken)) {
                                com.uzmap.pkg.uzkit.data.a.a().a("ac_token", accessToken);
                            }

                            if (1 != authStatus) {
                                String pageurl = object.optString("pageurl");
                                if (TextUtils.isEmpty(pageurl)) {
                                    if (this.h != null) {
                                        this.h.a(false, null, "验证失败，服务器错误");
                                    }

                                    return;
                                }

                                if (this.h != null) {
                                    this.h.a(true, pageurl, "");
                                }
                            } else if (this.h != null) {
                                this.h.a(true, "", "");
                            }

                        }
                    }
                }
            }
        }
    }

    private void h(ApiConfig info) {
        Bundle data = new Bundle();
        data.putString("wid", info.r);
        data.putString("cur_wgt_version", info.s);
        UPnsService.a(this.a, data);
    }

    private void a(boolean flag, ApiConfig info) {
        Bundle data = new Bundle();
        data.putString("wid", info.r);
        UPnsService.a(this.a, flag, data);
    }

    private void a(final String version, final String updateTip, final String source, final String time, final boolean force) {
        Runnable action = new Runnable() {
            public void run() {
                String title = force ? "强制更新" : "更新提示";
                String message = "有新版本啦！\n\n最新版本：" + version + "\n" + "更新描述：\n" + updateTip + "\n" + "发布时间：" + time + "\n";
                AlertDialog dia;
                if (force) {
                    dia = e.this.g();
                    dia.setTitle(title);
                    dia.setMessage(message);
                    dia.setCancelable(false);
                    dia.setButton(-1, "立即更新", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            e.this.a(source, force);
                        }
                    });
                    dia.show();
                } else {
                    dia = e.this.g();
                    dia.setMessage(message);
                    dia.setTitle(title);
                    dia.setCancelable(false);
                    dia.setButton(-2, "取消", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dia.setButton(-3, "立即更新", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            e.this.a(source, force);
                        }
                    });
                    dia.show();
                }

            }
        };
        this.post(action);
    }

    private void a(String url, final boolean force) {
        this.b("下载安装包");
        RequestParam downloadParam = new RequestParam();
        downloadParam.setUrl(url);
        downloadParam.setMethod(5);
        String defaultPath = UZUtility.getExternaStoragePath() + "Download/";
        downloadParam.setDefaultSavePath(defaultPath);
        downloadParam.setWillReportProgress(true);
        UZHttpClient.get().execute(downloadParam, new ProgressListener() {
            public void onResult(Response result) {
            }

            public void onProgress(int state, JSONObject result) {
                e.this.a(state, result);
                if (1 == state) {
                    final String savePath = result.optString("savePath");
                    UZCoreUtil.installApp(e.this.a, savePath);
                    if (!force) {
                        return;
                    }

                    Runnable action = new Runnable() {
                        public void run() {
                            AlertDialog dia = e.this.g();
                            dia.setTitle("下载成功");
                            dia.setMessage("强制更新版本，必须安装新的版本才能继续使用");
                            dia.setCancelable(false);
                            dia.setButton(-1, "立即安装", (OnClickListener) null);
                            dia.show();
                            Button btn = dia.getButton(-1);
                            if (btn != null) {
                                btn.setOnClickListener(new android.view.View.OnClickListener() {
                                    public void onClick(View v) {
                                        UZCoreUtil.installApp(e.this.a, savePath);
                                    }
                                });
                            }
                        }
                    };
                    e.this.post(action);
                }

            }
        });
    }

    private void a(final d inePackage) {
        if (!inePackage.c && !this.e()) {
            this.b("下载更新包");
        }

        RequestParam downloadParam = new RequestParam();
        downloadParam.setUrl(inePackage.b);
        downloadParam.setMethod(5);
        String defaultPath = UZUtility.getExternalCacheDir() + "Download/";
        downloadParam.setDefaultSavePath(defaultPath);
        downloadParam.setWillReportProgress(true);
        UZHttpClient.get().execute(downloadParam, new ProgressListener() {
            public void onResult(Response result) {
            }

            public void onProgress(int state, JSONObject result) {
                if (!inePackage.c) {
                    e.this.a(state, result);
                }

                if (1 == state) {
                    String savePath = result.optString("savePath");
                    boolean success = n.a(inePackage.e, inePackage.a, savePath);
                    if (success) {
                        e.this.b(inePackage);
                    } else {
                        e.this.d();
                    }
                }

            }
        });
    }

    private final void b(final d inePackage) {
        String appVersion = UZCoreUtil.getAppVersionName();
        String host = PropertiesUtil.h() + "/AM_Service_API/IncpkgUpdateStatusReport";
        RequestParam argument = new RequestParam();
        argument.setUrl(host);
        argument.setMethod(1);
        argument.setResultDataType(0);
        argument.setEscape(false);
        argument.setInSecure(inePackage.e.g());
        argument.setRqValue("systemType", this.f.p);
        argument.setRqValue("appVersion", appVersion);
        argument.setRqValue("subVersion", "" + inePackage.a);
        argument.setRqValue("fStatus", inePackage.c ? "1" : "2");
        argument.setRqValue("uStatus", "1");
        if (this.f.s) {
            argument.setRqValue("versionCode", "" + this.f.n);
        }

        argument.setRqValue("reportTime", "" + System.currentTimeMillis());
        RequestListener listener = new RequestListener() {
            public void onResult(Response result) {
                if (result.success()) {
                    com.uzmap.pkg.uzkit.a.b.a("incpkgUpdateStatusReport ok! result: " + result);
                    e.this.c(inePackage);
                } else {
                    com.uzmap.pkg.uzkit.a.b.b("incpkgUpdateStatusReport faild! result: " + result);
                    e.this.d();
                }

            }
        };
        UZHttpClient.get().execute(argument, listener);
    }

    private void a(final String closeTip) {
        Runnable action = new Runnable() {
            public void run() {
                String tip = closeTip != null ? closeTip : "";
                AlertDialog dia = e.this.g();
                dia.setTitle("强制关闭");
                dia.setMessage(tip);
                dia.setCancelable(false);
                dia.setButton(-1, "确定", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (e.this.h != null) {
                            e.this.h.a(true);
                        }

                    }
                });
                dia.show();
            }
        };
        this.post(action);
    }

    private void c(final d incPackage) {
        if (this.e()) {
            this.g(incPackage.e);
        } else {
            Runnable action;
            if (incPackage.c) {
                if (this.h != null) {
                    action = new Runnable() {
                        public void run() {
                            e.this.h.a(true, incPackage);
                        }
                    };
                    this.postDelayed(action, 5000L);
                }
            } else {
                action = new Runnable() {
                    public void run() {
                        AlertDialog dia = e.this.g();
                        dia.setTitle("提示消息");
                        dia.setMessage("更新包下载成功，将在下次启动应用时起作用");
                        dia.setCancelable(false);
                        dia.setButton(-1, "确定", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        dia.show();
                    }
                };
                this.post(action);
            }

        }
    }

    private JSONObject i(ApiConfig wgtInfo) {
        JSONObject deviceInfo = new JSONObject();

        try {
            com.uzmap.pkg.uzkit.data.a asp = com.uzmap.pkg.uzkit.data.a.a();
            boolean isNewVersion = false;
            String oldVersion = asp.b("appVersion", null);
            String curVersion = this.f.k;
            if (oldVersion != null && !curVersion.equals(oldVersion)) {
                isNewVersion = true;
            }

            asp.a("appVersion", curVersion);
            deviceInfo.put("isNewVersion", isNewVersion);
            deviceInfo.put("appVersion", curVersion);
            deviceInfo.put("widgetVersion", wgtInfo.s);
            deviceInfo.put("engineVersion", this.f.m);
            deviceInfo.put("channel", "");
            deviceInfo.put("jailbroken", UZCoreUtil.deviceBeRoot());
            deviceInfo.put("model", this.f.q);
            String manu = Build.MANUFACTURER;
            if ("|".equals(manu)) {
                manu = "unknown";
            }

            deviceInfo.put("manufacturer", manu);
            deviceInfo.put("operator", UZCoreUtil.getMobileOperatorName());
            deviceInfo.put("systemVersion", this.f.o);
            deviceInfo.put("systemType", this.f.p);
            int sw = this.f.j;
            int sh = this.f.i;
            String resolution = sw + "*" + sh;
            deviceInfo.put("resolution", resolution);
            deviceInfo.put("width", sw);
            deviceInfo.put("height", sh);
            String connectedType = com.uzmap.pkg.uzcore.b.a().c();
            deviceInfo.put("connectedType", connectedType);
            deviceInfo.put("deviceId", UZCoreUtil.getDeviceId());
            deviceInfo.put("longitude", asp.b("last_lat", "0.0"));
            deviceInfo.put("latitude", asp.b("last_log", "0.0"));
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return deviceInfo;
    }

    private void c() {
        if (this.g != null) {
            this.g.dismiss();
            this.g = null;
        }

    }

    private void b(String msg) {
        if (this.b != null) {
            this.g = new ProgressDialog(this.b);
        } else {
            this.g = new ProgressDialog(this.a);
            this.g.getWindow().setType(2003);
        }

        this.g.setTitle(msg);
        this.g.setCancelable(false);
        this.g.setProgressStyle(1);
        this.g.setMax(100);
        this.g.show();
    }

    private void a(final int state, final JSONObject result) {
        if (this.g != null) {
            Runnable action = new Runnable() {
                public void run() {
                    String percent;
                    float p;
                    if (state == 0) {
                        percent = result.optString("percent");
                        p = Float.parseFloat(percent);
                        e.this.g.setProgress((int) p);
                    } else if (1 == state) {
                        percent = result.optString("percent");
                        p = Float.parseFloat(percent);
                        e.this.g.setProgress((int) p);
                        e.this.c();
                    } else if (2 == state) {
                        e.this.c();
                        Toast.makeText(e.this.a, "下载失败", 0).show();
                    }

                }
            };
            this.post(action);
        }
    }

    private void a(ApiConfig wgtinfo, JSONObject object) {
        if (object != null) {
            JSONArray pakages = object.optJSONArray("widgets");
            if (pakages != null) {
                int length = pakages.length();
                this.i = new ArrayList(length);

                for (int i = 0; i < length; ++i) {
                    JSONObject pakage = pakages.optJSONObject(i);
                    if (pakage != null) {
                        int version = pakage.optInt("incNo");
                        String url = pakage.optString("url");
                        int slt = pakage.optInt("silent");
                        String extra = pakage.optString("extra");
                        boolean silent = 1 == slt;
                        d inePackage = new d(wgtinfo);
                        inePackage.a = version;
                        inePackage.b = url;
                        inePackage.c = silent;
                        inePackage.d = extra;
                        this.i.add(inePackage);
                    }
                }

            }
        }
    }

    private synchronized void d() {
        if (this.i != null) {
            this.i.clear();
            this.i = null;
        }

    }

    private synchronized boolean e() {
        return this.i != null && this.i.size() > 0;
    }

    private synchronized d f() {
        return this.i != null ? this.i.remove(0) : null;
    }

    private AlertDialog g() {
        Context mContext = null;
        AlertDialog dialog = null;
        Builder builder;
        if (this.b != null) {
            builder = new Builder(this.b);
            dialog = builder.create();
        } else {
            builder = new Builder(mContext);
            dialog = builder.create();
            dialog.getWindow().setType(2003);
        }

        return dialog;
    }
}
