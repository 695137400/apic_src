package com.uzmap.pkg.uzcore.external;

import android.text.TextUtils;
import android.webkit.URLUtil;

public class d {
    public static String a(String errorUrl) {
        String url = null;
        if (TextUtils.isEmpty(errorUrl)) {
            url = "null url";
        } else {
            url = URLUtil.guessFileName(errorUrl, null, "text/html");
        }

        return "<!DOCTYPE html><html ><head><meta content='text/html; charset=utf-8'><meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,user-scalable=0\" /><style>.active {background-color:rgb(200,90,50);}</style></head><body style=\"text-align:center;background:#FFF;\"><div style='margin-top:200px;color:#000;'>Error! Can Not Find File: <br><br>" + url + "<br><br></div><br><br><br>" + "<div style=\"text-align:center;background:#ccc;padding:10px;color:#000;\" tapmode=\"active\" onclick=\"api.closeWin()\">" + "Close This Window" + "</div>" + "</body>" + "</html>";
    }
}
