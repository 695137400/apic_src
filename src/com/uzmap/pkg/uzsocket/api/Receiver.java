package com.uzmap.pkg.uzsocket.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzsocket.g.a;
import com.uzmap.pkg.uzsocket.g.c;
import com.uzmap.pkg.uzsocket.g.f;
import org.json.JSONArray;
import org.json.JSONObject;

public class Receiver extends BroadcastReceiver {
    private c mDelegate;

    public Receiver(c delegate) {
        this.mDelegate = delegate;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent != null ? intent.getAction() : "";
        if (a.e.equals(action)) {
            int rcode = this.getResultCode();
            f.a("UPns PerformMsg Back: " + action + "," + (-1 != rcode ? "send faild" : "send ok"));
            if (-1 != rcode) {
                String data = intent.getStringExtra("data");
                String title = intent.getStringExtra("title");
                if (data != null) {
                    this.handCancel(title, data);
                }

                f.a("UPns PerformMsg Back: " + data);
            }
        }
    }

    private void handCancel(String title, String data) {
        String message = data;
        if (!TextUtils.isEmpty(data)) {
            try {
                JSONArray offLineMsg = null;
                String off_line_msg = this.mDelegate.a().b("off_line_msg", null);
                if (TextUtils.isEmpty(off_line_msg)) {
                    offLineMsg = new JSONArray();
                } else {
                    offLineMsg = new JSONArray(off_line_msg);
                }

                JSONObject json = new JSONObject(message);
                JSONArray msgs = json.optJSONArray("value");
                int l = msgs.length();

                for (int i = 0; i < l; ++i) {
                    Object o = msgs.opt(i);
                    if (o != null) {
                        offLineMsg.put(o);
                    }
                }

                this.mDelegate.a().a("off_line_msg", offLineMsg.toString());
            } catch (Exception var11) {
                var11.printStackTrace();
            }

            title = !TextUtils.isEmpty(title) ? title : "You Have A New Message";
            this.mDelegate.a(UZCoreUtil.getAppName(), title, data, true);
        }
    }
}
