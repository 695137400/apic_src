package com.uzmap.pkg.uzsocket.f;

import org.json.JSONObject;

public class g {
    public Object a;
    public int b;
    public Object c;
    public Object d;
    public int e;
    public long f;
    public long g;
    public Object h;
    public Object i;
    public Object j;
    public JSONObject k;
    private final String l;

    public g(String data) {
        this.l = data;
        this.e();
    }

    private void e() {
        if (this.l != null) {
            JSONObject jsonEntity = null;

            try {
                jsonEntity = new JSONObject(this.l);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            if (jsonEntity != null) {
                this.a = jsonEntity.opt("id");
                this.e = jsonEntity.optInt("t");
                this.f = jsonEntity.optLong("ct");
                this.b = jsonEntity.optInt("j");
                this.k = jsonEntity.optJSONObject("c");
                if (this.e != 0) {
                    this.c = jsonEntity.optString("a");
                    this.i = jsonEntity.optString("r");
                    this.h = jsonEntity.optString("s");
                    this.g = jsonEntity.optLong("g");
                    this.j = jsonEntity.optLong("ig");
                    this.d = jsonEntity.optString("dn");
                }
            }
        }
    }

    public boolean a() {
        return this.k != null;
    }

    public a b() {
        a message = null;
        int contentType = this.k.optInt("t");
        switch (contentType) {
            case 1:
                message = new e(this.k);
                break;
            case 2:
                message = new d(this.k);
                break;
            case 101:
            case 102:
            case 103:
            case 107:
                message = new c(this.k);
        }

        if (message != null) {
            message.c = this.f;
            message.a = this.a;
        }

        return message;
    }

    public b c() {
        b message = new b(this.k);
        message.c = this.f;
        message.a = this.a;
        message.d = this.h;
        message.e = this.i;
        message.f = this.j;
        return message;
    }

    public a d() {
        f message = new f(this.k);
        message.c = this.f;
        message.a = this.a;
        return message;
    }

    public String toString() {
        return this.l;
    }
}
