//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.uzmap.pkg.uzcore.uzmodule.aa.k;
import org.json.JSONObject;

public class UPMessage extends BroadcastReceiver {
    private k a;

    public UPMessage(k callback) {
        this.a = callback;
    }

    public void a(k callback) {
        this.a = callback;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && this.a != null) {
            String msg = "success";
            boolean status = true;
            switch(this.getResultCode()) {
                case -1:
                    msg = "success";
                    break;
                case 0:
                default:
                    status = false;
                    msg = "unkonwn error";
                    break;
                case 1:
                    status = false;
                    msg = "generic failure";
                    break;
                case 2:
                    status = false;
                    msg = "radio off";
                    break;
                case 3:
                    status = false;
                    msg = "null pdu";
                    break;
                case 4:
                    status = false;
                    msg = "no serice";
            }

            String action = intent.getAction();
            if ("APICLOUD.SMS.SEND".equals(action)) {
                JSONObject json = new JSONObject();

                try {
                    json.put("status", status);
                    json.put("msg", msg);
                } catch (Exception var8) {
                }

                this.a.success(json, false);
            }

            "APICLOUD.SMS.DELIVERED".equals(action);
        }
    }

    public void a() {
        this.a = null;
    }
}
