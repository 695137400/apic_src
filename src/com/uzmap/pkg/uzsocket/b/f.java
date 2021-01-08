package com.uzmap.pkg.uzsocket.b;

public class f extends c {
    private String g;
    private String h;
    private String i;

    public f() {
    }

    public f(String wgtId, String uuid, String userToken) {
        this.g = wgtId;
        this.h = uuid;
        this.i = userToken;
    }

    public static boolean a(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public void a(String wgtId, String uuid, String userToken) {
        this.g = wgtId;
        this.h = uuid;
        this.i = userToken;
    }

    public com.uzmap.pkg.uzsocket.e.b a(com.uzmap.pkg.uzsocket.e.b request) {
        super.a(request);
        request.a("Cookie", this.d());
        return request;
    }

    private String d() {
        StringBuffer sb = new StringBuffer();
        if (!a(this.g)) {
            sb.append("widgetId");
            sb.append("=");
            sb.append(this.g);
            sb.append("; ");
        }

        if (!a(this.h)) {
            sb.append("uuid");
            sb.append("=");
            sb.append(this.h);
            sb.append("; ");
        }

        if (!a(this.i)) {
            sb.append("userToken");
            sb.append("=");
            sb.append(this.i);
        }

        return sb.toString();
    }

    public com.uzmap.pkg.uzsocket.b.a c() {
        com.uzmap.pkg.uzsocket.b.a draft = new f(this.g, this.h, this.i);
        return draft;
    }

    public String toString() {
        return "WidgetId: " + this.g + " , UUid: " + this.h + " , UserToken: " + this.i;
    }
}
