package com.uzmap.pkg.uzcore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.uzmap.pkg.uzsocket.api.UPnsListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class UZPlatformBridge extends BroadcastReceiver {
    private boolean a;
    private String b;
    private String c;
    private Context d;
    private ArrayList<UPnsListener> e = new ArrayList();
    private com.uzmap.pkg.uzkit.data.a f = com.uzmap.pkg.uzkit.data.a.a();
    private boolean g;
    private String h;
    private UZPlatformBridge.a i;
    private static UZPlatformBridge j;

    private UZPlatformBridge(Context context) {
        this.d = context.getApplicationContext();
    }

    public static synchronized UZPlatformBridge a(Context context) {
        if (j == null) {
            j = new UZPlatformBridge(context);
        }

        return j;
    }

    public void a() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("UZMAP.UPUSH.MSM");
        filter.addAction("UZMAP.UPUSH.MSM.AUTH");
        filter.addAction("UZMAP.UPUSH.MSG.ORDER");
        filter.addAction("UZMAP.MODULE.REC.GEO");
        filter.addAction("UZMAP.NEED.REPORT.GEO");
        filter.addAction("UZMAP.DOWNLOAD.COMPLETE");
        filter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        this.d.registerReceiver(j, filter);
    }

    public synchronized void b() {
        if (j != null) {
            this.d.unregisterReceiver(j);
            j = null;
        }
    }

    public void a(UZPlatformBridge.a listener) {
        this.i = listener;
    }

    public String c() {
        if (!TextUtils.isEmpty(this.b)) {
            return this.b;
        } else {
            this.b = UZCoreUtil.getConnectedTypeString();
            return this.b;
        }
    }

    public String d() {
        if (!TextUtils.isEmpty(this.c)) {
            return this.c;
        } else {
            this.c = UZCoreUtil.getMobileOperatorName();
            if ("unknown".equals(this.c)) {
                this.c = "none";
            }

            return this.c;
        }
    }

    public void a(UPnsListener listener) {
        this.e.add(listener);
        String offLineMsg = this.f.b("off_line_msg", null);
        if (!TextUtils.isEmpty(offLineMsg)) {
            JSONArray jay = null;

            try {
                jay = new JSONArray(offLineMsg);
            } catch (JSONException var5) {
                var5.printStackTrace();
            }

            if (jay != null) {
                this.a(jay.toString());
                this.f.b("off_line_msg");
            }
        }

    }

    public void b(UPnsListener listener) {
        this.e.remove(listener);
    }

    private void a(boolean flag) {
        this.g = flag;
    }

    private boolean e() {
        return this.g;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                int type = UZCoreUtil.getConnectedType();
                boolean connected = 6 != type && type != 0;
                String ctype = UZCoreUtil.getConnectedTypeString(type);
                if (this.a != connected || !ctype.equals(this.b)) {
                    this.a = connected;
                    this.b = ctype;
                    if (this.i != null) {
                        this.i.a(this.a, this.b);
                    }
                }
            } else {
                String message;
                if ("UZMAP.UPUSH.MSM".equals(action)) {
                    message = intent.getStringExtra("data");
                    if (message != null) {
                        UZCoreUtil.logd("UZPlatformBridge Receive MSM CMD: " + message);
                        this.a(message, false);
                    }
                } else if ("UZMAP.UPUSH.MSM.AUTH".equals(action)) {
                    message = intent.getStringExtra("data");
                    if (message != null) {
                        UZCoreUtil.logd("UZPlatformBridge Receive MSM Auth: " + message);
                        this.b(message);
                    }
                } else if ("UZMAP.UPUSH.MSG.ORDER".equals(action)) {
                    message = null;

                    try {
                        String data = intent.getStringExtra("data");
                        JSONObject json = new JSONObject(data);
                        JSONArray msgs = json.optJSONArray("value");
                        message = msgs.toString();
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }

                    UZCoreUtil.logd("UZPlatformBridge Receive Msg: " + message);
                    if (this.e.size() > 0) {
                        this.setResultCode(-1);
                        this.a(message);
                    } else {
                        this.setResultCode(0);
                    }
                } else {
                    double lat;
                    double log;
                    if ("UZMAP.MODULE.REC.GEO".equals(action)) {
                        lat = intent.getDoubleExtra("last_lat", 0.0D);
                        log = intent.getDoubleExtra("last_log", 0.0D);
                        float rad = intent.getFloatExtra("last_rad", 0.0F);
                        String addr = intent.getStringExtra("last_addr");
                        UZCoreUtil.logd("UZPlatformBridge Receive Module geo: " + lat + "," + log + "," + rad + "," + addr);
                        if (this.e()) {
                            this.a(this.h, true);
                        }
                    } else if ("UZMAP.NEED.REPORT.GEO".equals(action)) {
                        lat = intent.getDoubleExtra("last_lat", 0.0D);
                        log = intent.getDoubleExtra("last_log", 0.0D);
                        String widgetId = intent.getStringExtra("wid");
                        if (this.i != null) {
                            this.i.a(lat, log, widgetId);
                        }
                    } else if ("UZMAP.DOWNLOAD.COMPLETE".equals(action)) {
                        if (this.i != null) {
                            this.i.a(intent);
                        }
                    } else if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) && this.i != null) {
                        this.i.b(intent);
                    }
                }
            }
        }

    }

    private void a(String message) {
        if (!TextUtils.isEmpty(message)) {
            Iterator var3 = this.e.iterator();

            while (var3.hasNext()) {
                UPnsListener lis = (UPnsListener) var3.next();
                lis.onMessage(message);
            }

        }
    }

    private void a(String cmd, boolean hasGeo) {
        JSONObject json = null;

        try {
            json = new JSONObject(cmd);
        } catch (Exception var19) {
            var19.printStackTrace();
        }

        if (json != null) {
            boolean gps_use = false;
            String gps_msg = "";
            JSONObject gps = json.optJSONObject("gps");
            if (gps != null) {
                gps_use = this.a(gps.optInt("use"));
                if (gps_use && !hasGeo) {
                    this.c(cmd);
                    return;
                }

                gps_msg = gps.optString("msg");
                JSONArray l = gps.optJSONArray("l");
                int length = l != null ? l.length() : 0;
                if (length > 0) {
                    gps_use = true;

                    for (int i = 0; i < length; ++i) {
                        JSONObject item = l.optJSONObject(i);
                        int lt = item.optInt("t");
                        double lat = item.optDouble("x", 0.0D);
                        double lon = item.optDouble("y", 0.0D);
                        String addr = item.optString("a", "");
                        float limit = (float) item.optDouble("r", 0.0D);
                        boolean infence = this.a(lt, lat, lon, addr, limit);
                        if (infence) {
                            gps_use = false;
                            break;
                        }
                    }
                }
            }

            JSONObject tm = json.optJSONObject("tm");
            boolean tm_use = false;
            String tm_msg = "";
            if (tm != null) {
                tm_use = this.a(tm.optInt("use"));
                tm_msg = tm.optString("msg");
            }

            boolean nt_use = false;
            String nt_msg = "";
            JSONObject nt = json.optJSONObject("nt");
            String etc_msg;
            if (nt != null) {
                nt_use = this.a(nt.optInt("use"));
                nt_msg = nt.optString("msg");
                String nts = nt.optString("nts", "").toLowerCase();
                if (nts != null && nt_use) {
                    etc_msg = UZCoreUtil.getConnectedTypeString().toLowerCase();
                    if (etc_msg != null && nts.contains(etc_msg)) {
                        nt_use = false;
                    }
                }
            }

            boolean etc_use = false;
            etc_msg = "";
            JSONObject etc = json.optJSONObject("etc");
            boolean forbiden;
            if (etc != null) {
                etc_use = this.a(etc.optInt("use"));
                etc_msg = etc.optString("msg");
                forbiden = UZCoreUtil.deviceBeRoot();
                if (forbiden && etc_use) {
                    etc_use = true;
                } else {
                    etc_use = false;
                }
            }

            forbiden = tm_use || nt_use || gps_use || etc_use;
            if (forbiden) {
                String bidMsg = "您的应用被限制运行：\n";
                if (tm_use) {
                    bidMsg = bidMsg + tm_msg;
                    bidMsg = bidMsg + "\n";
                }

                if (nt_use) {
                    bidMsg = bidMsg + nt_msg;
                    bidMsg = bidMsg + "\n";
                }

                if (gps_use) {
                    bidMsg = bidMsg + gps_msg;
                    bidMsg = bidMsg + "\n";
                }

                if (etc_use) {
                    bidMsg = bidMsg + etc_msg;
                    bidMsg = bidMsg + "\n";
                }

                if (this.i != null) {
                    this.i.a("运行控制", bidMsg, "退出");
                }
            }

            this.h = null;
            this.a(false);
        }
    }

    private void b(String cmd) {
        JSONObject json = null;

        try {
            json = new JSONObject(cmd);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        if (json != null) {
            int opt = json.optInt("opt", -1);
            if (1 == opt) {
                this.g();
            } else if (2 == opt) {
                if (this.i != null) {
                    this.i.a("运行控制", "您已被取消使用权限", "退出应用");
                }
            } else if (3 == opt) {
                if (this.i != null) {
                    this.i.a("运行控制", "您已被取消使用权限", "退出应用");
                }
            } else if (4 == opt) {
            }

        }
    }

    private void c(String cmd) {
        this.h = cmd;
        this.a(true);
        Class target = null;

        try {
            target = Class.forName("com.uzmap.pkg.uzmodules.uzBaiduLocation.UzBaiduLocation");
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        if (target == null) {
            this.a(this.h, true);
        } else {
            Class[] param = new Class[]{Context.class};
            Method forceLocation = null;

            try {
                forceLocation = target.getDeclaredMethod("forceLocation", param);
                if (forceLocation == null) {
                    this.a(this.h, true);
                    return;
                }

                forceLocation.invoke(null, this.d);
            } catch (Exception var6) {
                var6.printStackTrace();
            }

        }
    }

    private boolean a(int use) {
        return use == 1;
    }

    private boolean f() {
        return ApplicationProcess.initialize().l();
    }

    private boolean a(int lt, double lat, double log, String addr, float limit) {
        if (!this.f()) {
            return true;
        } else {
            String lats;
            if (1 == lt) {
                lats = this.f.b("last_addr", null);
                return this.a(addr, lats);
            } else {
                if (2 == lt) {
                    lats = this.f.b("last_lat", "0.0");
                    String logs = this.f.b("last_log", "0.0");
                    double llat = 0.0D;

                    try {
                        llat = Double.parseDouble(lats);
                    } catch (Exception var17) {
                    }

                    double llog = 0.0D;

                    try {
                        llog = Double.parseDouble(logs);
                    } catch (Exception var16) {
                    }

                    if (llat * llog <= 0.0D) {
                        return true;
                    }

                    double distance = this.a(llat, llog, lat, log);
                    distance = distance > 0.0D ? distance / 1000.0D : 0.0D;
                    return !(distance > (double) limit);
                }

                return true;
            }
        }
    }

    private double a(double lat1, double log1, double lat2, double log2) {
        double radLat1 = this.a(lat1);
        double radLat2 = this.a(lat2);
        double a = radLat1 - radLat2;
        double b = this.a(log1) - this.a(log2);
        double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
        s *= 6378137.0D;
        s = (double) (Math.round(s * 10000.0D) / 10000L);
        return s;
    }

    private double a(double d) {
        return d * 3.141592653589793D / 180.0D;
    }

    private void g() {
    }

    private boolean a(String addr, String laddr) {
        if (!TextUtils.isEmpty(addr) && !TextUtils.isEmpty(laddr)) {
            String[] addrs = addr.split(",");
            String[] laddrs;
            if (3 != addrs.length) {
                if (addrs.length == 0) {
                    laddrs = new String[]{"", "", ""};
                    addrs = laddrs;
                } else if (1 == addrs.length) {
                    laddrs = new String[]{addrs[0], "", ""};
                    addrs = laddrs;
                } else if (2 == addrs.length) {
                    laddrs = new String[]{addrs[0], addrs[1], ""};
                    addrs = laddrs;
                }
            }

            laddrs = laddr.split(",");
            if (3 != laddrs.length) {
                String[] ts;
                if (laddrs.length == 0) {
                    ts = new String[]{"", "", ""};
                    laddrs = ts;
                } else if (1 == laddrs.length) {
                    ts = new String[]{laddrs[0], "", ""};
                    laddrs = ts;
                } else if (2 == laddrs.length) {
                    ts = new String[]{laddrs[0], laddrs[1], ""};
                    laddrs = ts;
                }
            }

            if (this.b(addrs[0], laddrs[0])) {
                return this.b(addrs[1], laddrs[1]) ? this.b(addrs[2], laddrs[2]) : false;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private boolean b(String s1, String s2) {
        if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)) {
            return s1.equals(s2);
        } else {
            return true;
        }
    }

    public interface a {
        void a(boolean var1, String var2);

        void a(Intent var1);

        void b(Intent var1);

        void a(double var1, double var3, String var5);

        void a(String var1, String var2, String var3);
    }
}
