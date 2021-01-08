//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.d1;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.uzmap.pkg.a.b.p;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class b extends Authenticator {
    private static b a;
    private b.a b;
    private final Handler c = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            com.uzmap.pkg.a.b.d1.a res = (com.uzmap.pkg.a.b.d1.a)msg.obj;
            String acount = msg.getData().getString("acount");
            String url = msg.getData().getString("url");
            if (!b.this.b.a(url, acount, res)) {
                res.a();
                res.b();
            }

        }
    };

    private b(Context context) {
    }

    public static final void a(Context context) {
        if (a == null) {
            a = new b(context);
        }

        Authenticator.setDefault(a);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        p.a("getPasswordAuthentication", this);
        String url = this.getRequestingSite() != null ? this.getRequestingSite().toString() : null;
        if (url == null) {
            return null;
        } else {
            com.uzmap.pkg.a.b.d1.a result = this.a(url, this.getRequestingPrompt());
            String name = result != null ? result.d() : null;
            String psw = result != null ? result.e() : null;
            return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(psw) ? new PasswordAuthentication(name, psw.toCharArray()) : null;
        }
    }

    private com.uzmap.pkg.a.b.d1.a a(String site, String acount) {
        if (this.b == null) {
            return null;
        } else {
            com.uzmap.pkg.a.b.d1.a result = new com.uzmap.pkg.a.b.d1.a(this);
            Message message = this.c.obtainMessage(0, result);
            message.getData().putString("url", site);
            message.getData().putString("acount", acount);
            synchronized(this) {
                this.c.sendMessage(message);

                try {
                    this.wait();
                } catch (InterruptedException var7) {
                    Log.e("ldx", "Caught exception while waiting for authentication");
                    Log.e("ldx", Log.getStackTraceString(var7));
                }

                return result;
            }
        }
    }

    public String a() {
        StringBuffer sb = new StringBuffer();
        sb.append("Host=" + this.getRequestingHost() + "\n");
        sb.append("Port=" + this.getRequestingPort() + "\n");
        sb.append("Prompt=" + this.getRequestingPrompt() + "\n");
        sb.append("Protocol=" + this.getRequestingProtocol() + "\n");
        sb.append("Scheme=" + this.getRequestingScheme() + "\n");
        sb.append("Site=" + this.getRequestingSite() + "\n");
        sb.append("URL=" + this.getRequestingURL() + "\n");
        sb.append("Type=" + this.getRequestorType() + "\n");
        return sb.toString();
    }

    public interface a {
        boolean a(String var1, String var2, com.uzmap.pkg.a.b.d1.a var3);
    }
}
