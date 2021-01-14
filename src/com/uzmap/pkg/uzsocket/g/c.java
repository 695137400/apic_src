package com.uzmap.pkg.uzsocket.g;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.uzmap.pkg.uzapp.PropertiesUtil;
import com.uzmap.pkg.uzcore.ApplicationProcess;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.UZOpenApi;
import com.uzmap.pkg.uzsocket.UPnsService;
import com.uzmap.pkg.uzsocket.api.Receiver;
import org.json.JSONObject;

import java.net.URI;

public class c {
    private static g l = null;
    private UPnsService a;
    private String b;
    private boolean c;
    private b d;
    private f e;
    private String f;
    private com.uzmap.pkg.uzkit.data.a g;
    private Receiver h;
    private d i;
    private final c.a j = new c.a(null);
    private int k;

    public void a(UPnsService context) {
        this.a = context;
    }

    public com.uzmap.pkg.uzkit.data.a a() {
        return this.g;
    }

    public void b() {
        com.uzmap.pkg.uzsocket.g.f.a("UPns Service onCreate");
        this.g = com.uzmap.pkg.uzkit.data.a.a();
        this.c = this.g.b();
        this.e = new f();
        this.f = this.a.getPackageName();
        this.d = new b(this);
        this.h = new Receiver(this);
    }

    public int a(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : "";
        if (!com.uzmap.pkg.uzsocket.g.a.a.equals(action)) {
            this.f();
            return 1;
        } else {
            int operate = intent.getIntExtra("operate", -1);
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service onStartCommand operate : " + operate);
            switch (operate) {
                case 1:
                    this.a(intent.getBundleExtra("data"), true);
                    break;
                case 2:
                    this.c = intent.getBooleanExtra("value", true);
                    this.g.a(this.c);
                    if (!this.c && !ApplicationProcess.initialize().i()) {
                        this.a.stopSelf();
                        return 1;
                    }

                    if (this.c && !this.g()) {
                        this.a(intent.getBundleExtra("data"), false);
                    }
                    break;
                case 3:
                    if (l == null) {
                        l = new g();
                        l.a(this.a);
                    }

                    l.a(200L);
                    if (!this.g()) {
                        this.a(null, false);
                    }
                    break;
                case 4:
                    if (l == null) {
                        l = new g();
                        l.a(this.a);
                    }

                    l.a(200L);
                    boolean network = UZCoreUtil.networkEnable();
                    com.uzmap.pkg.uzsocket.g.f.a("UPns Network Changed: " + network);
                    if (!network) {
                        this.i();
                    } else {
                        this.a(null, false);
                    }
            }

            return 1;
        }
    }

    public void c() {
        com.uzmap.pkg.uzsocket.g.f.a("UPns Service onDestroy");
        if (l != null) {
            l.c();
            l = null;
        }

        this.i();
        this.k();
    }

    private void f() {
        if (!this.g() && !ApplicationProcess.initialize().n()) {
            this.a(null, false);
        }

    }

    private boolean g() {
        return this.i != null && this.i.e();
    }

    private void a(Bundle data, boolean fromBind) {
        if (!this.e.a()) {
            if (this.g()) {
                if (fromBind) {
                    this.i.h();
                }

            } else {
                com.uzmap.pkg.uzsocket.g.f.a("UPns Service readyToBind");
                if (data != null) {
                    this.b = data.getString("wid");
                    if (!TextUtils.isEmpty(this.b)) {
                        this.g.a("upns_wid", this.b);
                    }
                }

                if (this.b == null) {
                    this.b = this.g.b("upns_wid", null);
                    if (this.b == null) {
                        return;
                    }
                }

                boolean binded = this.g.b("upns_bind", false);
                if (!binded) {
                    this.l();
                } else {
                    this.h();
                }

            }
        }
    }

    private void h() {
        com.uzmap.pkg.uzsocket.g.f.a("UPns Service openSocketClient: " + this.i);
        if (this.i == null) {
            String server = PropertiesUtil.f();
            if (TextUtils.isEmpty(server)) {
                return;
            }

            URI u = URI.create(server);
            String protocol = "ws";
            String host = u.getHost();
            int port = u.getPort();
            if (port <= 0) {
                port = 80;
            }

            server = protocol + "://" + host + ":" + port;
            u = URI.create(server);
            com.uzmap.pkg.uzsocket.b.f draft = new com.uzmap.pkg.uzsocket.b.f();
            String widgetId = this.b;
            String uuid = UZOpenApi.getUUID();
            String userToken = UZOpenApi.getUserToken(widgetId);
            draft.a(widgetId, uuid, userToken);
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service TCP Connect: " + draft + " , " + u);
            this.i = new d(u, draft);
            this.i.a(this.j);
            this.i.c();
        }

    }

    private void i() {
        if (this.i != null) {
            this.i.d();
            this.i.g();
            this.i = null;
        }

        this.n();
        this.d.d();
    }

    private void j() {
        this.k();
        Intent intent = new Intent(this.a, UPnsService.class);
        intent.setAction(com.uzmap.pkg.uzsocket.g.a.a);
        intent.putExtra("operate", 3);
        PendingIntent pi = PendingIntent.getService(this.a, 0, intent, 0);
        AlarmManager alarmMgr = (AlarmManager) this.a.getSystemService("alarm");
        long interval = 180000L;
        long triggerTime = System.currentTimeMillis() + interval;
        alarmMgr.setRepeating(0, triggerTime, interval, pi);
        com.uzmap.pkg.uzsocket.g.f.a("UPns Service KeepServiceAlive");
    }

    private void k() {
        Intent intent = new Intent(this.a, UPnsService.class);
        intent.setAction(com.uzmap.pkg.uzsocket.g.a.a);
        intent.putExtra("operate", 3);
        PendingIntent pi = PendingIntent.getService(this.a, 0, intent, 0);
        AlarmManager alarmMgr = (AlarmManager) this.a.getSystemService("alarm");
        alarmMgr.cancel(pi);
        com.uzmap.pkg.uzsocket.g.f.a("UPns Service StopKeepServiceAlive");
    }

    public void d() {
    }

    private void l() {
        if (this.b != null) {
            f.a callback = new f.a() {
                public void a(int statusCode, String data) {
                    int status = 1;

                    try {
                        JSONObject json = new JSONObject(data);
                        status = json.optInt("status");
                    } catch (Exception var5) {
                    }

                    if (200 == statusCode && 1 == status) {
                        com.uzmap.pkg.uzsocket.g.f.a("UPns Service HTTP Bind OK : \n" + data);
                        c.this.h();
                        c.this.g.a("upns_bind", true);
                    } else {
                        com.uzmap.pkg.uzsocket.g.f.b("UPns Service HTTP Bind Faild");
                    }

                }
            };
            this.e.a(this.b, callback);
        }
    }

    public void a(String title, String text, String data, boolean msg) {
        com.uzmap.pkg.uzapp.d.a option = this.g.g();
        if (this.c && option.d) {
            option.a = title;
            option.b = text;
            option.c = data;
            option.g = false;
            com.uzmap.pkg.uzapp.d.a().a(this.a, option);
        }
    }

    private void a(com.uzmap.pkg.uzsocket.f.e message) {
        if (this.c && this.g.c()) {
            Intent intent = new Intent(com.uzmap.pkg.uzsocket.g.a.e);
            intent.setPackage(this.f);
            intent.putExtra("data", message.b());
            intent.putExtra("title", message.c());
            this.a.sendOrderedBroadcast(intent, null, this.h, null, 0, null, null);
        }
    }

    private void a(com.uzmap.pkg.uzsocket.f.c data) {
        if (ApplicationProcess.initialize().i()) {
            if (101 == data.b) {
                this.e.a(this.a, this.b);
            } else {
                String action = com.uzmap.pkg.uzsocket.g.a.c;
                if (data.b()) {
                    action = com.uzmap.pkg.uzsocket.g.a.d;
                }

                Intent intent = new Intent(action);
                intent.setPackage(this.f);
                intent.putExtra("data", data.c());
                this.a.sendBroadcast(intent);
            }
        }
    }

    public void e() {
        if (this.i != null) {
            this.i.d();
            this.i.g();
        }

        this.i = null;
        if (this.o()) {
            this.h();
        }
    }

    private void m() {
        this.d.b();
        this.d.a(this.k * 2);
    }

    private void n() {
        this.k = 0;
        this.d.c();
    }

    private boolean o() {
        if (this.k > 10) {
            this.n();
            return false;
        } else {
            ++this.k;
            return true;
        }
    }

    private class a implements e {
        private a() {
        }

        // $FF: synthetic method
        a(c.a var2) {
            this();
        }

        public void a() {
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service onOpen");
            c.this.n();
            c.this.j();
            c.this.d.a();
        }

        public void a(int code, String reason, boolean remote) {
            Runnable action = new Runnable() {
                public void run() {
                    c.this.m();
                }
            };
            c.this.d.a(action);
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service onClose: " + code + " , " + reason + " , " + remote);
        }

        public void a(Exception ex) {
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service onError: " + (ex != null ? ex.getMessage() : ""));
            ex.printStackTrace();
        }

        public void a(com.uzmap.pkg.uzsocket.f.e message) {
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service onPushMessage: " + message);
            c.this.a(message);
        }

        public void a(com.uzmap.pkg.uzsocket.f.d message) {
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service onNoticeMessage: " + message);
            c.this.a(message.d, message.e, null, false);
        }

        public void a(com.uzmap.pkg.uzsocket.f.c message) {
            com.uzmap.pkg.uzsocket.g.f.a("UPns Service onMsmMessage: " + message);
            c.this.a(message);
        }

        public void a(com.uzmap.pkg.uzsocket.f.a message) {
        }
    }
}
