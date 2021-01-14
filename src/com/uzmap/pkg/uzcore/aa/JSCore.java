package com.uzmap.pkg.uzcore.aa;

import com.uzmap.pkg.uzcore.UZCoreUtil;

import java.util.Random;

public final class JSCore {
    public static final String a = "X" + a(3);
    private static String d;
    private static String e;
    public static String b = "";
    public static String c = "";
    private static String f;
    private static String g;

    static {
        f = "window."
                + a + "$e = function(p){var op = {};if (p.length === 1) {if (typeof p[0] === 'function') {op.cbId = "
                + a + "$cb.id++;" + a + "$cb.fn[op.cbId] = p[0];}else{op.arg = p[0];}}else if(p.length === 2){op.arg = p[0];op.cbId = "
                + a + "$cb.id++;" + a + "$cb.fn[op.cbId] = p[1];}return JSON.stringify(op);};window."
                + a + "$cb = {fn:{}, id:1, on:function(cbId,ret,err,del) {this.fn[cbId] && this.fn[cbId](ret,err);if(del)delete this.fn[cbId];},gc:function(cbId){delete this.fn[cbId];}};" + "window."
                + a + "$md = {};";
        g = "if(window.apiready){window.apiready();};" + a + ".ovrri();api.parseTapmode();";
        e = "(function(){var MAX_MOVE = 10;var slop = 1500;var tap = 'tapmode';function addTouchedClass(node, clas) {    if (node && clas) {        if (!hasClass(node, clas)) {            node.className = node.className ? node.className + ' ' + clas : clas;        }    }};function removeTouchedClass(node) {    if (node && node.clicker) {    \tvar clas = node.clicker.clas;    \tvar className = node.className;    \tif(className && clas){    \t\tnode.className = className.replace(clas, '').trim();    \t}    };};function hasClass(el, className) {    return new RegExp('(^|\\s)' + className + '(\\s|$)').test(el.className);};var Clicker = function(){};function parseTapmode(){\tvar nodes = document.querySelectorAll('[' + tap + ']');  \tif(nodes){\t\tfor(var i = 0; i < nodes.length; i++){\t\t\tvar node = nodes[i];\t\t\tnode.removeEventListener('touchstart', handStart, false);\t\t\tnode.removeEventListener('touchmove', handMove, false);\t\t\tnode.removeEventListener('touchend', handEnd, false);\t\t\tnode.removeEventListener('touchcancel', handCancel, false);\t\t\tnode.addEventListener('touchstart', handStart, false);\t\t\tnode.addEventListener('touchmove', handMove, false);\t\t\tnode.addEventListener('touchend', handEnd, false);\t\t\tnode.addEventListener('touchcancel', handCancel, false);\t\t\tif(node.onclick){\t\t\t\tnode.uzclick=node.onclick;\t\t\t\tnode.onclick=null;\t\t\t}\t\t}  \t}};function handStart(e){  \t var node = e.currentTarget;  \t if(node.disabled){ return; }     var c = new Clicker();    c.X = e.touches[0].clientX;    c.Y = e.touches[0].clientY;    var clas = node.getAttribute(tap);    c.clas = clas;    node.clicker = c;    addTouchedClass(node, clas);};function handMove(e){  var node = e.currentTarget;  if(node.disabled){ return; }   var c = node.clicker;  if(!c){  \treturn;  }  var x = e.touches[0].clientX, y = e.touches[0].clientY;  if (Math.abs(x - c.X) > MAX_MOVE || Math.abs(y - c.Y) > MAX_MOVE) {    reset(node, true);  }};function handEnd(e){  var node = e.currentTarget;  if(node.disabled){ return; }   reset(node);  fire(e, node);};function handCancel(e){  var node = e.currentTarget;  if(node.disabled){ return; }   reset(node, true);};function fire(e, node) {\tif(node.uzclick){\t  var c = node.clicker;\t  if(c){\t  \tnode.uzclick.call(node,e);\t\tnode.clicker = null;\t  }\t}};function reset(node, del) {   removeTouchedClass(node);   if(del){\t\tnode.clicker = null;\t}};api.parseTapmode = parseTapmode;})();";
    }

    public static void a(String script) {
        d = script;
    }

    public static String a() {
        if (d == null) {
            d = "";
        }

        if (e == null) {
            e = "";
        }

        String script = f + b() + d + e + b + c + g;
        return script;
    }

    private static String b() {
        return "window.api = {require: function(n,cb){if (" + a + "$md[n]) {return " + a + "$md[n];} if(cb){cb('undefined', {code:0, msg:n + ' module not found'})}" + a + ".R(n);}," + "version: " + a + ".A(0)," + "systemType: " + a + ".A(1)," + "systemVersion: " + a + ".A(2)," + "deviceId: " + a + ".A(3)," + "deviceModel: " + a + ".A(4)," + "deviceName: " + a + ".A(5)," + "connectionType: " + a + ".A(6)," + "wgtParam: JSON.parse(" + a + ".A(7))," + "pageParam: JSON.parse(" + a + ".A(8))," + "appParam: " + a + ".A(9)," + "wgtRootDir: " + a + ".A(10)," + "winName: " + a + ".A(11)," + "frameName: " + a + ".A(12)," + "winWidth: " + a + ".A(13)," + "winHeight: " + a + ".A(14)," + "frameWidth: " + a + ".A(15)," + "frameHeight: " + a + ".A(16)," + "appId: " + a + ".A(17)," + "appName: " + a + ".A(18)," + "wgtLoaderDir: " + a + ".A(19)," + "appVersion: " + a + ".A(20)," + "screenWidth: " + a + ".A(21)," + "screenHeight: " + a + ".A(22)," + "fsDir: " + a + ".A(23)," + "cacheDir: " + a + ".A(24)," + "operator: " + a + ".A(25)," + "deviceToken: " + a + ".A(26)," + "fingerPrint: " + a + ".A(27)," + "toLauncher: function(){" + a + ".toLauncher(" + a + "$e(arguments));}," + "installApp: function(){" + a + ".installApp(" + a + "$e(arguments));}," + "openApp: function(){" + a + ".openApp(" + a + "$e(arguments));}," + "openWidget: function(){" + a + ".openWidget(" + a + "$e(arguments));}," + "closeWidget: function(){" + a + ".closeWidget(" + a + "$e(arguments));}," + "getFsWidgets: function(){" + a + ".getFsWidgets(" + a + "$e(arguments));}," + "openWin: function(){" + a + ".openWin(" + a + "$e(arguments));}," + "openSlidLayout: function(){" + a + ".openSlidLayout(" + a + "$e(arguments));}," + "openSlidPane: function(){" + a + ".openSlidPane(" + a + "$e(arguments));}," + "closeSlidPane: function(){" + a + ".closeSlidPane(" + a + "$e(arguments));}," + "setWinAttr: function(){" + a + ".setWinAttr(" + a + "$e(arguments));}," + "closeWin: function(){" + a + ".closeWin(" + a + "$e(arguments));}," + "closeToWin: function(){" + a + ".closeToWin(" + a + "$e(arguments));}," + "execScript: function(){" + a + ".execScript(" + a + "$e(arguments));}," + "openFrame: function(){" + a + ".openFrame(" + a + "$e(arguments));}," + "setFrameAttr: function(){" + a + ".setFrameAttr(" + a + "$e(arguments));}," + "bringFrameToFront: function(){" + a + ".bringFrameToFront(" + a + "$e(arguments));}," + "sendFrameToBack: function(){" + a + ".sendFrameToBack(" + a + "$e(arguments));}," + "closeFrame: function(){" + a + ".closeFrame(" + a + "$e(arguments));}," + "animation: function(){" + a + ".animation(" + a + "$e(arguments));}," + "openFrameGroup: function(){" + a + ".openFrameGroup(" + a + "$e(arguments));}," + "setFrameGroupAttr: function(){" + a + ".setFrameGroupAttr(" + a + "$e(arguments));}," + "setFrameGroupIndex: function(){" + a + ".setFrameGroupIndex(" + a + "$e(arguments));}," + "closeFrameGroup: function(){" + a + ".closeFrameGroup(" + a + "$e(arguments));}," + "setBounces: function(){" + a + ".setBounces(" + a + "$e(arguments));}," + "setRefreshHeaderInfo: function(){" + a + ".setRefreshHeaderInfo(" + a + "$e(arguments));}," + "refreshHeaderLoadDone: function(){" + a + ".refreshHeaderLoadDone(" + a + "$e(arguments));}," + "addEventListener: function(){" + a + ".addEventListener(" + a + "$e(arguments));}," + "removeEventListener: function(){" + a + ".removeEventListener(" + a + "$e(arguments));}," + "log: function(){" + a + ".log(" + a + "$e(arguments));}," + "alert: function(){" + a + ".alert(" + a + "$e(arguments));}," + "confirm: function(){" + a + ".confirm(" + a + "$e(arguments));}," + "prompt: function(){" + a + ".prompt(" + a + "$e(arguments));}," + "showProgress: function(){" + a + ".showProgress(" + a + "$e(arguments));}," + "hideProgress: function(){" + a + ".hideProgress(" + a + "$e(arguments));}," + "setPrefs: function(){" + a + ".setPrefs(" + a + "$e(arguments));}," + "getPrefs: function(){" + a + ".getPrefs(" + a + "$e(arguments));}," + "loadSecureValue: function(){" + a + ".loadSecureValue(" + a + "$e(arguments));}," + "removePrefs: function(){" + a + ".removePrefs(" + a + "$e(arguments));}," + "getPicture: function(){" + a + ".getPicture(" + a + "$e(arguments));}," + "ajax: function(){" + a + ".ajax(" + a + "$e(arguments));}," + "call: function(){" + a + ".call(" + a + "$e(arguments));}," + "sms: function(){" + a + ".sms(" + a + "$e(arguments));}," + "mail: function(){" + a + ".mail(" + a + "$e(arguments));}," + "readFile: function(){" + a + ".readFile(" + a + "$e(arguments));}," + "writeFile: function(){" + a + ".writeFile(" + a + "$e(arguments));}," + "startRecord: function(){" + a + ".startRecord(" + a + "$e(arguments));}," + "stopRecord: function(){" + a + ".stopRecord(" + a + "$e(arguments));}," + "startPlay: function(){" + a + ".startPlay(" + a + "$e(arguments));}," + "stopPlay: function(){" + a + ".stopPlay(" + a + "$e(arguments));}," + "startLocation: function(){" + a + ".startLocation(" + a + "$e(arguments));}," + "stopLocation: function(){" + a + ".stopLocation(" + a + "$e(arguments));}," + "getLocation: function(){" + a + ".getLocation(" + a + "$e(arguments));}," + "startSensor: function(){" + a + ".startSensor(" + a + "$e(arguments));}," + "stopSensor: function(){" + a + ".stopSensor(" + a + "$e(arguments));}," + "setStatusBarStyle: function(){" + a + ".setStatusBarStyle(" + a + "$e(arguments));}," + "setFullScreen: function(){" + a + ".setFullScreen(" + a + "$e(arguments));}," + "removeLaunchView: function(){" + a + ".removeLaunchView(" + a + "$e(arguments));}," + "openContacts: function(){" + a + ".openContacts(" + a + "$e(arguments));}," + "openPicker: function(){" + a + ".openPicker(" + a + "$e(arguments));}," + "openVideo: function(){" + a + ".openVideo(" + a + "$e(arguments));}," + "download: function(){" + a + ".download(" + a + "$e(arguments));}," + "cancelDownload: function(){" + a + ".cancelDownload(" + a + "$e(arguments));}," + "clearCache: function(){" + a + ".clearCache(" + a + "$e(arguments));}," + "actionSheet: function(){" + a + ".actionSheet(" + a + "$e(arguments));}," + "toast: function(){" + a + ".toast(" + a + "$e(arguments));}," + "showFloatBox: function(){" + a + ".showFloatBox(" + a + "$e(arguments));}," + "notification: function(){" + a + ".notification(" + a + "$e(arguments));}," + "cancelNotification: function(){" + a + ".cancelNotification(" + a + "$e(arguments));}," + "lockSlidPane: function(){" + a + ".lockSlidPane(" + a + "$e(arguments));}," + "unlockSlidPane: function(){" + a + ".unlockSlidPane(" + a + "$e(arguments));}," + "setScreenOrientation: function(){" + a + ".setScreenOrientation(" + a + "$e(arguments));}," + "setKeepScreenOn: function(){" + a + ".setKeepScreenOn(" + a + "$e(arguments));}," + "sendEvent: function(){" + a + ".sendEvent(" + a + "$e(arguments));}," + "historyBack: function(){" + a + ".historyBack(" + a + "$e(arguments));}," + "historyForward: function(){" + a + ".historyForward(" + a + "$e(arguments));}," + "appInstalled: function(){" + a + ".appInstalled(" + a + "$e(arguments));}," + "imageCache: function(){" + a + ".imageCache(" + a + "$e(arguments));}," + "requestFocus: function(){" + a + ".requestFocus(" + a + "$e(arguments));}," + "onTvPeak: function(){" + a + ".onTvPeak(" + a + "$e(arguments));}," + "setTvFocusElement: function(){" + a + ".setTvFocusElement(" + a + "$e(arguments));}," + "pageDown: function(){" + a + ".pageDown(" + a + "$e(arguments));}," + "pageUp: function(){" + a + ".pageUp(" + a + "$e(arguments));}," + "scrollBy: function(){" + a + ".pageScrollBy(" + a + "$e(arguments));}," + "scrollTo: function(){" + a + ".pageScrollTo(" + a + "$e(arguments));}," + "saveMediaToAlbum: function(){" + a + ".saveMediaToAlbum(" + a + "$e(arguments));}," + "setScreenSecure: function(){" + a + ".setScreenSecure(" + a + "$e(arguments));}," + "setAppIconBadge: function(){" + a + ".setAppIconBadge(" + a + "$e(arguments));}," + "getCacheSize: function(){" + a + ".getCacheSize(" + a + "$e(arguments));}," + "getFreeDiskSpace: function(){" + a + ".getFreeDiskSpace(" + a + "$e(arguments));}," + "cancelAjax: function(){" + a + ".cancelAjax(" + a + "$e(arguments));}," + "refreshHeaderLoading: function(){" + a + ".refreshHeaderLoading(" + a + "$e(arguments));}," + "accessNative: function(){" + a + ".accessNative(" + a + "$e(arguments));}" + "};";
    }

    private static final String a(int l) {
        if (l <= 0) {
            l = 1;
        }

        String[] A = UZCoreUtil.dictArr;
        int max = A.length;
        if (l > max) {
            l = max;
        }

        Random r = new Random();
        String result = "";

        for (int i = 0; i < l; ++i) {
            result = result + A[r.nextInt(max)];
        }

        return result;
    }

    public static final void a(byte[] bte) {
        if (bte != null && bte.length != 0) {
            try {
                bte = AssetsFileUtil.a(bte);
                b = new String(bte);
            } catch (Exception var2) {
            }

        }
    }

    public static final void b(byte[] bte) {
        if (bte != null && bte.length != 0) {
            try {
                bte = AssetsFileUtil.b(bte);
                c = new String(bte);
            } catch (Exception var2) {
            }

        }
    }
}
