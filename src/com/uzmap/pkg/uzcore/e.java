package com.uzmap.pkg.uzcore;

import android.text.TextUtils;

import java.util.Hashtable;

public class e {
    static final Hashtable<String, Integer> b = new Hashtable();
    static int a = 99;

    static {
        b.put("empty", -1);
        b.put("pause", 0);
        b.put("resume", 1);
        b.put("online", 2);
        b.put("offline", 3);
        b.put("batterylow", 4);
        b.put("batterystatus", 5);
        b.put("scrolltobottom", 6);
        b.put("viewappear", 7);
        b.put("keyback", 8);
        b.put("keymenu", 9);
        b.put("viewdisappear", 10);
        b.put("tap", 11);
        b.put("shake", 12);
        b.put("error", 13);
        b.put("swipeleft", 14);
        b.put("swiperight", 15);
        b.put("swipeup", 16);
        b.put("swipedown", 17);
        b.put("noticeclicked", 18);
        b.put("appintent", 19);
        b.put("smartupdatefinish", 20);
        b.put("sysdownloadcomplete", 21);
        b.put("telecontroller", 22);
        b.put("focuschange", 23);
    }

    public static int a(String event) {
        if (TextUtils.isEmpty(event)) {
            return -1;
        } else {
            event = event.toLowerCase();
            Integer value = b.get(event);
            if (value == null) {
                Integer eId = a++;
                b.put(event, eId);
                return eId;
            } else {
                return value;
            }
        }
    }
}
