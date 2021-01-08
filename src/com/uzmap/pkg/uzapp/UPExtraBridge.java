package com.uzmap.pkg.uzapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UPExtraBridge extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        c.a().a(context, intent);
    }
}
