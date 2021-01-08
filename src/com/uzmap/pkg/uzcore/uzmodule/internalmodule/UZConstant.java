//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.uzmodule.internalmodule;

import java.util.Hashtable;

public class UZConstant {
    public static final boolean STATUS_OK = true;
    public static final boolean STATUS_NO = false;
    public static final int AJAX_METHOD_GET = 0;
    public static final int AJAX_METHOD_POST = 1;
    public static final int AJAX_METHOD_HEAD = 2;
    public static final int AJAX_METHOD_PUT = 3;
    public static final int AJAX_METHOD_DELETE = 4;
    public static final int AJAX_METHOD_DOWNLOAD = 5;
    public static final int AJAX_METHOD_UPLOAD = 6;
    public static final int AJAX_DATA_TYPE_JSON = 0;
    public static final int AJAX_DATA_TYPE_TEXT = 1;
    public static final int AJAX_ERROE_CODE_CONNECTION_FAILURE = 0;
    public static final int AJAX_ERROE_CODE_TIMEOUT = 1;
    public static final int AJAX_ERROE_CODE_AUTHENTICATION = 2;
    public static final int ANIM_SUB_TYPE_FROM_RIGHT = 0;
    public static final int ANIM_SUB_TYPE_FROM_LEFT = 1;
    public static final int ANIM_SUB_TYPE_FROM_TOP = 2;
    public static final int ANIM_SUB_TYPE_FROM_BOTTOM = 3;
    public static final int ANIM_TYPE_NONE = -1;
    public static final int ANIM_TYPE_PUSH = 0;
    public static final int ANIM_TYPE_MOVEIN = 1;
    public static final int ANIM_TYPE_FADE = 2;
    public static final int ANIM_TYPE_FLIP = 3;
    public static final int ANIM_TYPE_REVEAL = 4;
    public static final int ANIM_TYPE_RIPPLE = 5;
    public static final int ANIM_TYPE_CURL = 6;
    public static final int ANIM_TYPE_UN_CURL = 7;
    public static final int ANIM_TYPE_SUCK = 8;
    public static final int ANIM_TYPE_CUBE = 9;
    public static final int CALL_TYPE_TEL_PROMPT = 0;
    public static final int CALL_TYPE_TEL = 1;
    public static final int CALL_TYPE_FACETIME = 2;
    public static final int CONNECT_TYPE_UNKNOWN = 0;
    public static final int CONNECT_TYPE_ETHERNET = 1;
    public static final int CONNECT_TYPE_WIFI = 2;
    public static final int CONNECT_TYPE_CELL_2G = 3;
    public static final int CONNECT_TYPE_CELL_3G = 4;
    public static final int CONNECT_TYPE_CELL_4G = 5;
    public static final int CONNECT_TYPE_NONE = 6;
    public static final int ERROR_CODE_ERROR = 0;
    public static final int ERROR_CODE_NO_MODULE = 1;
    public static final int ERROR_CODE_NO_METHOD = 2;
    public static final int ERROR_CODE_UNMATCH_ARGUMENT = 3;
    public static final int ERROR_CODE_NO_PERMISSION = 4;
    public static final int FS_ERROR_CODE_NO_ERROR = 0;
    public static final int FS_ERROR_CODE_NOT_FOUND_ERR = 1;
    public static final int FS_ERROR_CODE_NOT_READABLE_ERR = 2;
    public static final int FS_ERROR_CODE_ENCODING_ERR = 3;
    public static final int FS_ERROR_CODE_NO_MODIFICATION_ALLOWED_ERR = 4;
    public static final int FS_ERROR_CODE_INVALID_MODIFICATION_ERR = 5;
    public static final int FS_ERROR_CODE_QUOTA_EXCEEDED_ERR = 6;
    public static final int FS_ERROR_CODE_PATH_EXISTS_ERR = 7;
    public static final int IMAGE_DATA_TYPE_BASE64 = 0;
    public static final int IMAGE_DATA_TYPE_URL = 1;
    public static final int IMAGE_ENCODING_TYPE_JPEG = 0;
    public static final int IMAGE_ENCODING_TYPE_PNG = 1;
    public static final int MEDIA_TYPE_PICTURE = 0;
    public static final int MEDIA_TYPE_VIDEO = 1;
    public static final int MEDIA_TYPE_ALL_MEDIA = 2;
    public static final int PICTURE_SOURCE_TYPE_PHOTO_LIBRARY = 0;
    public static final int PICTURE_SOURCE_TYPE_CAMERA = 1;
    public static final int PICTURE_SOURCE_TYPE_SAVED_PHOTO_ALBUM = 2;
    public static final int PROGRESS_ANIM_TYPE_FADE = 2;
    public static final int PROGRESS_ANIM_TYPE_ZOOM = 1;
    public static final int PROGRESS_STYLE_DEFAULT = 0;
    public static final int PROGRESS_STYLE_ROUND = 1;
    public static final int PROGRESS_STYLE_CUSTOM = 2;
    public static final int SENSOR_TYPE_ACCELEROMETER = 0;
    public static final int SENSOR_TYPE_GYROSCOPE = 1;
    public static final int SENSOR_TYPE_MAGNETIC_FIELD = 2;
    public static final int SENSOR_TYPE_PROXIMITY = 3;
    public static final int SENSOR_TYPE_ORIENTATION = 4;
    public static final int SENSOR_TYPE_PRESSURE = 5;
    public static final int ACCURACY_TEN_METERS = 0;
    public static final int ACCURACY_HUNDRED_METERS = 1;
    public static final int ACCURACY_KILOMETER = 2;
    public static final int ACCURACY_THREE_KILOMETERS = 3;
    public static final int SYSTEM_TYPE_IOS = 0;
    public static final int SYSTEM_TYPE_Android = 1;
    public static final int SYSTEM_TYPE_Win = 2;
    public static final int SYSTEM_TYPE_WP = 3;
    public static final int STATUS_BAR_STYLE_DARK = 0;
    public static final int STATUS_BAR_STYLE_LIGHT = 1;
    public static final int PICKER_TYPE_DATE = 0;
    public static final int PICKER_TYPE_TIME = 1;
    public static final int PICKER_TYPE_DATE_TIME = 2;
    public static final int STATE_LOADING = 0;
    public static final int STATE_FINISHED = 1;
    public static final int STATE_FAILED = 2;
    public static final int PLAY_STATE_ERROR = 0;
    public static final int PLAY_STATE_FINISH = 1;
    public static final int TOAST_BOTTOM = 0;
    public static final int TOAST_MIDDLE = 1;
    public static final int TOAST_TOP = 2;
    public static final int ORIENTATION_PORTRAIT_UP = 1;
    public static final int ORIENTATION_PORTRAIT_DOWN = 9;
    public static final int ORIENTATION_LANDSCAPE_LEFT = 0;
    public static final int ORIENTATION_LANDSCAPE_RIGHT = 8;
    public static final int ORIENTATION_AUTO = 4;
    public static final int ORIENTATION_AUTO_LANDSCAPE = 6;
    public static final int ORIENTATION_AUTO_PORTRAIT = 7;
    public static final long UNAVAILABLE = -1L;
    public static final long PREPARING = -2L;
    public static final long UNKNOWN_SIZE = -3L;
    public static final int SLID_TYPE_LEFT = 0;
    public static final int SLID_TYPE_RIGHT = 1;
    public static final int SLID_TYPE_ALL = 2;
    public static final Hashtable<String, Integer> ConstantMap = new Hashtable();

    static {
        ConstantMap.put("library", 0);
        ConstantMap.put("camera", 1);
        ConstantMap.put("album", 2);
        ConstantMap.put("pic", 0);
        ConstantMap.put("video", 1);
        ConstantMap.put("all", 2);
        ConstantMap.put("default", 0);
        ConstantMap.put("zoom", 1);
        ConstantMap.put("jpg", 0);
        ConstantMap.put("png", 1);
        ConstantMap.put("base64", 0);
        ConstantMap.put("url", 1);
        ConstantMap.put("json", 0);
        ConstantMap.put("text", 1);
        ConstantMap.put("get", 0);
        ConstantMap.put("post", 1);
        ConstantMap.put("head", 2);
        ConstantMap.put("put", 3);
        ConstantMap.put("delete", 4);
        ConstantMap.put("fail", 0);
        ConstantMap.put("timeout", 1);
        ConstantMap.put("auth", 2);
        ConstantMap.put("tel_prompt", 0);
        ConstantMap.put("tel", 1);
        ConstantMap.put("facetime", 2);
        ConstantMap.put("unknown", 0);
        ConstantMap.put("ethernet", 1);
        ConstantMap.put("wifi", 2);
        ConstantMap.put("2G", 3);
        ConstantMap.put("3G", 4);
        ConstantMap.put("4G", 5);
        ConstantMap.put("none", 6);
        ConstantMap.put("ios", 0);
        ConstantMap.put("android", 1);
        ConstantMap.put("win", 2);
        ConstantMap.put("wp", 3);
        ConstantMap.put("none", -1);
        ConstantMap.put("push", 0);
        ConstantMap.put("movein", 1);
        ConstantMap.put("fade", 2);
        ConstantMap.put("flip", 3);
        ConstantMap.put("reveal", 4);
        ConstantMap.put("ripple", 5);
        ConstantMap.put("curl", 6);
        ConstantMap.put("un_curl", 7);
        ConstantMap.put("suck", 8);
        ConstantMap.put("cube", 9);
        ConstantMap.put("from_right", 0);
        ConstantMap.put("from_left", 1);
        ConstantMap.put("from_top", 2);
        ConstantMap.put("from_bottom", 3);
        ConstantMap.put("10m", 0);
        ConstantMap.put("100m", 1);
        ConstantMap.put("1km", 2);
        ConstantMap.put("3km", 3);
        ConstantMap.put("accelerometer", 0);
        ConstantMap.put("gyroscope", 1);
        ConstantMap.put("magnetic_field", 2);
        ConstantMap.put("proximity", 3);
        ConstantMap.put("orientation", 4);
        ConstantMap.put("pressure", 5);
        ConstantMap.put("dark", 0);
        ConstantMap.put("light", 1);
        ConstantMap.put("date", 0);
        ConstantMap.put("time", 1);
        ConstantMap.put("date_time", 2);
        ConstantMap.put("top", 2);
        ConstantMap.put("bottom", 0);
        ConstantMap.put("middle", 1);
        ConstantMap.put("left", 0);
        ConstantMap.put("right", 1);
        ConstantMap.put("portrait_up", 1);
        ConstantMap.put("portrait_down", 9);
        ConstantMap.put("landscape_left", 0);
        ConstantMap.put("landscape_right", 8);
        ConstantMap.put("auto", 4);
        ConstantMap.put("auto_landscape", 6);
        ConstantMap.put("auto_portrait", 7);
    }

    public UZConstant() {
    }

    public static int mapInt(String str, int defalut) {
        if (str == null) {
            return defalut;
        } else {
            str = str.toLowerCase();
            Integer value = (Integer)ConstantMap.get(str);
            return value == null ? defalut : value;
        }
    }
}
