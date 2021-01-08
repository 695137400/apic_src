package com.uzmap.pkg.uzkit;

import android.content.Context;
import com.uzmap.pkg.uzapp.b;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.uzmodule.e;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import com.uzmap.pkg.uzkit.data.a;
import com.uzmap.pkg.uzkit.data.d;

public class UZOpenApi {
    public static final String ACTION = "UZMAP.UPUSH";
    public static final String ACTION_BIND = "UZMAP.UPUSH.BIND";
    public static final String ACTION_JOIN = "UZMAP.UPUSH.JOIN";
    public static final String ACTION_LEAVE = "UZMAP.UPUSH.LEAVE";
    public static final String ACTION_MSM = "UZMAP.UPUSH.MSM";
    public static final String ACTION_MSM_AUTH = "UZMAP.UPUSH.MSM.AUTH";
    public static final String ACTION_ORDER_MSG = "UZMAP.UPUSH.MSG.ORDER";
    public static final String ACTION_REC_GEO = "UZMAP.MODULE.REC.GEO";
    public static final String ACTION_REPORT_GEO = "UZMAP.NEED.REPORT.GEO";
    public static final String APP_PREFERENCE = "UzAppStorage";
    public static final String LAST_LAT = "last_lat";
    public static final String LAST_LOG = "last_log";
    public static final String LAST_ALT = "last_alt";
    public static final String LAST_RAD = "last_rad";
    public static final String LAST_ADDR = "last_addr";
    public static final String UNAME = "uname";
    public static final String UID = "uid";
    public static final String CID = "cid";
    public static final String WID = "wid";
    public static final String TKN = "tkn";
    public static final String OPERATE = "operate";
    public static final String DATA = "data";
    public static final String VALUE = "value";
    public static final String VERSION = "cur_wgt_version";
    public static final String GROUP_NAME = "groupName";
    public static final String NET_ALIVE = "netAlive";
    public static final String RESULT = "result";
    public static final String OFF_LINE_MSG = "off_line_msg";
    public static final String PUSH_FLAG = "push_flag";
    public static final String PUSH_NOTIFY = "push_notify";
    public static final String PUSH_NOTIFY_FLAG = "notify_updateCurrent";
    public static final String PUSH_SILENCE_START_HOUR = "push_silence_start_hour";
    public static final String PUSH_SILENCE_START_MINUTE = "push_silence_start_minute";
    public static final String PUSH_SILENCE_END_HOUR = "push_silence_end_hour";
    public static final String PUSH_SILENCE_END_MINUTE = "push_silence_end_minute";
    public static final String PUSH_DEFAULTS = "push_defaults";
    public static final String UPNS_UID = "upns_uid";
    public static final String UPNS_WID = "upns_wid";
    public static final String UPNS_UNAME = "upns_uname";
    public static final String UPNS_BIND = "upns_bind";
    public static final String ACCESS_TOKEN = "ac_token";
    public static final String APP_PARAM = "appParam";
    public static final String API_ARGUMENTS = "api_arguments";

    public static String getMsmAccessToken() {
        return a.a().b("ac_token", "");
    }

    public static void setMsmAccessToken(String token) {
        a.a().a("ac_token", token);
    }

    public static String getUserToken(String widgetId) {
        return b.a(widgetId);
    }

    public static String getWidgetKey(String widgetId) {
        return b.b(widgetId);
    }

    public static String getUUID() {
        return UZCoreUtil.getUUID();
    }

    public static String getClientId(String widgetId) {
        return b.c(widgetId);
    }

    public static String mamHost() {
        return b.c();
    }

    public static String mcmHost() {
        return b.d();
    }

    public static String msmHost() {
        return b.e();
    }

    public static String pushHost() {
        return b.f();
    }

    public static String storeHost() {
        return b.g();
    }

    public static String analysisHost() {
        return b.h();
    }

    public static UZWidgetInfo queryWidgetInfo(Context context, String widgetId) {
        e entity = d.a(widgetId, false);
        return entity != null ? entity.i() : null;
    }
}
