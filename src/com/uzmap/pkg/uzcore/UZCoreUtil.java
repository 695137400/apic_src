package com.uzmap.pkg.uzcore;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.os.*;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzkit.UZUtility;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UZCoreUtil {
   public static final boolean a = com.uzmap.pkg.uzapp.b.l();
   public static final String[] e = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
   static String b = null;
   static String c = null;
   static String d = null;
   static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   static SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
   static DecimalFormat h;
   static SimpleDateFormat i = new SimpleDateFormat("yyyy-MM-dd");
   static SimpleDateFormat j = new SimpleDateFormat("yyyy-MM-dd HH:mm");
   static SimpleDateFormat k = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   static boolean l = true;
   private static MessageDigest m = null;
   private static MessageDigest n = null;

   public static void logi(String msg) {
      if (a) {
         Log.i("ldx", msg);
      }
   }

   public static void logd(String msg) {
      if (a) {
         Log.d("ldx", msg);
      }
   }

   public static void loge(String msg) {
      if (a) {
         Log.e("ldx", msg);
      }
   }

   public static void logw(String msg) {
      if (a) {
         Log.w("ldx", msg);
      }
   }

   public static String getDefaultUserAgent() {
      return r.a();
   }

   public static boolean checkPermission(String permission) {
      return com.uzmap.pkg.uzcore.b.a().b().checkPermission(permission, Process.myPid(), Process.myUid()) == 0;
   }

   public static boolean appExist(Intent intent) {
      PackageManager pmgr = com.uzmap.pkg.uzcore.b.a().b().getPackageManager();
      List<ResolveInfo> list = pmgr.queryIntentActivities(intent, 0);
      return list != null && list.size() > 0;
   }

   public static boolean appExist(String packageName) {
      if (TextUtils.isEmpty(packageName)) {
         return false;
      } else {
         try {
            PackageManager pmgr = com.uzmap.pkg.uzcore.b.a().b().getPackageManager();
            ApplicationInfo info = pmgr.getApplicationInfo(packageName, 8192);
            if (info != null) {
               return true;
            }
         } catch (NameNotFoundException var3) {
         }

         return false;
      }
   }

   public static String formatDate(long time) {
      String result = "";

      try {
         result = f.format(time);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return result;
   }

   public static String formatToDate(long time) {
      String result = "";

      try {
         result = g.format(time);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return result;
   }

   public static String random() {
      String result = "";

      try {
         String random = "" + System.currentTimeMillis();
         result = Integer.toHexString(random.hashCode());
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      return result;
   }

   public static String random(String random) {
      String result = "";

      try {
         result = Integer.toHexString(random.hashCode());
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      return result;
   }

   public static final String randomChar(int needLength) {
      if (needLength <= 0) {
         needLength = 1;
      }

      int max = e.length;
      if (needLength > max) {
         needLength = max;
      }

      Random random = new Random();
      String result = "";

      for (int i = 0; i < needLength; ++i) {
         result = result + e[random.nextInt(max)];
      }

      return result;
   }

   public static String formatNumber(Object time) {
      if (h == null) {
         h = new DecimalFormat();
         h.setMaximumFractionDigits(2);
         h.setMinimumFractionDigits(0);
      }

      if (time == null) {
         time = "0.00";
      }

      return h.format(time);
   }

   public static String toMD5(String inStr) {
      if (m == null) {
         try {
            m = MessageDigest.getInstance("MD5");
         } catch (Exception var10) {
            var10.printStackTrace();
         }
      }

      if (TextUtils.isEmpty(inStr)) {
         return inStr;
      } else {
         String ruslt = null;
         if (m != null) {
            byte[] kb = inStr.getBytes();
            m.reset();
            m.update(kb);
            byte[] md5Bytes = m.digest();
            StringBuffer hexValue = new StringBuffer();
            byte[] var8 = md5Bytes;
            int var7 = md5Bytes.length;

            for (int var6 = 0; var6 < var7; ++var6) {
               byte d5b = var8[var6];
               int val = d5b & 255;
               if (val < 16) {
                  hexValue.append("0");
               }

               hexValue.append(Integer.toHexString(val));
            }

            ruslt = hexValue.toString();
         }

         return ruslt;
      }
   }

   public static String toSHA1(String inStr) {
      if (n == null) {
         try {
            n = MessageDigest.getInstance("SHA-1");
         } catch (Exception var10) {
            var10.printStackTrace();
         }
      }

      if (TextUtils.isEmpty(inStr)) {
         return inStr;
      } else {
         String ruslt = null;
         if (n != null) {
            byte[] kb = inStr.getBytes();
            n.reset();
            n.update(kb);
            byte[] sha1Bytes = n.digest();
            StringBuffer hexValue = new StringBuffer();
            byte[] var8 = sha1Bytes;
            int var7 = sha1Bytes.length;

            for (int var6 = 0; var6 < var7; ++var6) {
               byte sha1b = var8[var6];
               int val = sha1b & 255;
               if (val < 16) {
                  hexValue.append("0");
               }

               hexValue.append(Integer.toHexString(val));
            }

            ruslt = hexValue.toString();
         }

         return ruslt;
      }
   }

   public static void hideSoftKeyboard(Context context, View view) {
      try {
         InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
         if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static int parseColor(String inColor) {
      int reColor = com.uzmap.pkg.uzcore.external.l.c;

      try {
         if (inColor != null && inColor.length() != 0) {
            inColor = inColor.toLowerCase();
            inColor = inColor.replace(" ", "");
            if (inColor.charAt(0) == 'r') {
               int start = inColor.indexOf(40) + 1;
               int off = inColor.indexOf(41);
               inColor = inColor.substring(start, off);
               String[] rgba = inColor.split(",");
               int r = Integer.parseInt(rgba[0]);
               int g = Integer.parseInt(rgba[1]);
               int b = Integer.parseInt(rgba[2]);
               int a = 255;
               if (4 == rgba.length) {
                  float al = Float.parseFloat(rgba[3]);
                  if (al <= 1.0F) {
                     a = (int) (al * (float) a);
                  } else {
                     a = (int) al;
                  }
               }

               reColor = a << 24 | r << 16 | g << 8 | b;
            } else {
               inColor = inColor.substring(1);
               if (3 == inColor.length()) {
                  char[] t = new char[]{inColor.charAt(0), inColor.charAt(0), inColor.charAt(1), inColor.charAt(1), inColor.charAt(2), inColor.charAt(2)};
                  inColor = String.valueOf(t);
               } else if (6 == inColor.length()) {
               }

               long color = Long.parseLong(inColor, 16);
               reColor = (int) (color | -16777216L);
            }
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

      return reColor;
   }

   public static final int parseCssPixel(String px) {
      if (TextUtils.isEmpty(px)) {
         return 0;
      } else if ("auto".equals(px)) {
         return com.uzmap.pkg.uzcore.external.l.d;
      } else {
         float density = com.uzmap.pkg.uzcore.d.a().n;
         int index = px.indexOf("px");
         if (index > 0) {
            String t = px.substring(0, index);
            return (int) ((float) parseInt(t) * density);
         } else {
            return (int) ((float) parseInt(px) * density);
         }
      }
   }

   public static int parseInt(String in) {
      try {
         return (int) Float.parseFloat(in);
      } catch (Exception var2) {
         return 0;
      }
   }

   public static final double parseCssTime(String time) {
      if (TextUtils.isEmpty(time)) {
         return 0.0D;
      } else {
         int index = time.indexOf("s");
         if (index > 0) {
            String t = time.substring(0, index);
            return Double.parseDouble(t);
         } else {
            return Double.parseDouble(time);
         }
      }
   }

   public static final int parseCssColor(String color) {
      return parseColor(color);
   }

   public static final long parseDateToMills(String datestr) {
      if (TextUtils.isEmpty(datestr)) {
         return System.currentTimeMillis();
      } else {
         Date date = null;

         try {
            date = i.parse(datestr);
         } catch (Exception var5) {
         }

         if (date == null) {
            try {
               date = j.parse(datestr);
            } catch (Exception var4) {
            }
         }

         if (date == null) {
            try {
               date = k.parse(datestr);
            } catch (Exception var3) {
            }
         }

         return date != null ? date.getTime() : System.currentTimeMillis();
      }
   }

   public static final String getCookie(String url) {
      return com.uzmap.pkg.uzapp.e.a().a(url);
   }

   public static final void setCookie(String url, String cookie) {
      if (cookie != null) {
         com.uzmap.pkg.uzapp.e.a().a(url, cookie);
      }
   }

   public static int dipToPix(int dip) {
      return Math.round(com.uzmap.pkg.uzcore.d.a().n * (float) dip);
   }

   public static int pixToDip(int pix) {
      return Math.round((float) pix / com.uzmap.pkg.uzcore.d.a().n);
   }

   public static boolean checkPermission() {
      return false;
   }

   public static String getMimeType(String path) {
      if (TextUtils.isEmpty(path)) {
         return null;
      } else {
         String extension = path;
         int lastDot = path.lastIndexOf(46);
         if (lastDot != -1) {
            extension = path.substring(lastDot + 1);
         }

         extension = extension.toLowerCase(Locale.getDefault());
         if (extension.equals("3ga")) {
            return "audio/3gpp";
         } else {
            return extension.equals("js") ? "text/javascript" : MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
         }
      }
   }

   public static String getExtension(String mimeType) {
      if (TextUtils.isEmpty(mimeType)) {
         return "file";
      } else {
         String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
         return extension != null ? extension : "file";
      }
   }

   public static String extension(String url) {
      String cleanUrl = url;
      int index = url.indexOf(63);
      if (index > 0) {
         cleanUrl = url.substring(0, index);
      } else {
         index = url.indexOf(35);
         if (index > 0) {
            cleanUrl = url.substring(0, index);
         }
      }

      String extension = MimeTypeMap.getFileExtensionFromUrl(cleanUrl);
      extension = extension.toLowerCase();
      return extension;
   }

   public static String guessFileNameBuyPath(String url) {
      String mType = getMimeType(url);
      if (mType != null) {
         int index = url.lastIndexOf(47);
         return url.substring(index + 1);
      } else {
         return null;
      }
   }

   public static String makeFileNameWidthMimeType(String mimeType, String url) {
      String extension = getExtension(mimeType);
      String name = random(url);
      return name + "." + extension;
   }

   public static String makeRealPath(String path, UZWidgetInfo wgtInfo) {
      return UZUtility.makeRealPath(path, wgtInfo);
   }

   public static String getDeviceId() {
      checkPermission();
      if (!TextUtils.isEmpty(b)) {
         return b;
      } else {
         String result = null;
         com.uzmap.pkg.uzkit.data.a uzsp = com.uzmap.pkg.uzkit.data.a.a();
         result = uzsp.b("device_id", null);
         if (!TextUtils.isEmpty(result)) {
            b = result;
            return result;
         } else {
            Context context = com.uzmap.pkg.uzcore.b.a().b();

            try {
               TelephonyManager tMgr = null;
               tMgr = (TelephonyManager) context.getSystemService("phone");
               result = tMgr.getDeviceId();
            } catch (Exception var10) {
            }

            if (!TextUtils.isEmpty(result)) {
               int l = result.length();
               char first = result.charAt(0);
               boolean check = false;

               for (int i = 1; i < l; ++i) {
                  char c = result.charAt(i);
                  if (c != first) {
                     check = true;
                     break;
                  }
               }

               if (!check) {
                  result = null;
               }
            }

            if (TextUtils.isEmpty(result)) {
               try {
                  WifiManager wifi = (WifiManager) context.getSystemService("wifi");
                  WifiInfo info = wifi.getConnectionInfo();
                  result = info.getMacAddress();
               } catch (Exception var9) {
               }
            }

            if (TextUtils.isEmpty(result)) {
               try {
                  result = Secure.getString(context.getContentResolver(), "android_id");
               } catch (Exception var8) {
               }
            }

            if (!TextUtils.isEmpty(result) && result.toLowerCase().contains("unknown")) {
               result = null;
            }

            if (TextUtils.isEmpty(result)) {
               result = "APICloud_" + UUID.randomUUID().toString();
            }

            if (!TextUtils.isEmpty(result)) {
               uzsp.a("device_id", result);
            }

            b = result;
            return result;
         }
      }
   }

   public static String getUUID() {
      return getDeviceId();
   }

   public static String getAppVersionName() {
      return com.uzmap.pkg.uzcore.d.a().t.versionName;
   }

   public static String getAppName() {
      if (d != null) {
         return d;
      } else {
         d = UZResourcesIDFinder.getString("app_name");
         return d;
      }
   }

   public static int getAppVersionCode() {
      return com.uzmap.pkg.uzcore.d.a().t.versionCode;
   }

   public static String getUzVersion() {
      if (!TextUtils.isEmpty(c)) {
         return c;
      } else {
         c = getMetaStringData("uz_version");
         return c;
      }
   }

   public static String getMetaStringData(String key) {
      Bundle metaData = com.uzmap.pkg.uzcore.d.a().u.metaData;
      return metaData != null ? metaData.getString(key) : null;
   }

   public static int getConnectedType() {
      checkPermission();
      Context context = com.uzmap.pkg.uzcore.b.a().b();
      ConnectivityManager cManager = (ConnectivityManager) context.getSystemService("connectivity");
      NetworkInfo nInfo = cManager.getActiveNetworkInfo();
      if (nInfo != null && nInfo.isAvailable()) {
         int type = nInfo.getType();
         int subType = nInfo.getSubtype();
         switch (type) {
            case 0:
               switch (subType) {
                  case 1:
                  case 2:
                  case 4:
                  case 7:
                  case 11:
                     return 3;
                  case 3:
                  case 5:
                  case 6:
                  case 8:
                  case 9:
                  case 10:
                  case 12:
                  case 14:
                  case 15:
                     return 4;
                  case 13:
                     return 5;
                  default:
                     return 0;
               }
            case 1:
               return 2;
            case 9:
               return 1;
            default:
               return 0;
         }
      } else {
         return 6;
      }
   }

   public static String getConnectedTypeString() {
      int ctype = getConnectedType();
      return getConnectedTypeString(ctype);
   }

   public static String getConnectedTypeString(int ctype) {
      String result = "unknown";
      switch (ctype) {
         case 0:
            result = "unknown";
            break;
         case 1:
            result = "ethernet";
            break;
         case 2:
            result = "wifi";
            break;
         case 3:
            result = "2G";
            break;
         case 4:
            result = "3G";
            break;
         case 5:
            result = "4G";
            break;
         case 6:
            result = "none";
      }

      return result;
   }

   public static boolean networkEnable() {
      int type = getConnectedType();
      return 6 != type && type != 0;
   }

   public static boolean wifiEnable() {
      return 2 == getConnectedType();
   }

   public static long getAvailableSpace() {
      String state = Environment.getExternalStorageState();
      if ("checking".equals(state)) {
         return -2L;
      } else if (!"mounted".equals(state)) {
         return -1L;
      } else {
         String DIRECTORY = UZFileSystem.get().getVirtualFsDir();
         File dir = new File(DIRECTORY);
         dir.mkdirs();
         if (dir.isDirectory() && dir.canWrite()) {
            try {
               StatFs stat = new StatFs(DIRECTORY);
               long available = com.uzmap.pkg.uzcore.external.l.a >= 18 ? stat.getAvailableBlocksLong() : (long) stat.getAvailableBlocks();
               long blockSize = com.uzmap.pkg.uzcore.external.l.a >= 18 ? stat.getBlockSizeLong() : (long) stat.getBlockSize();
               return available * blockSize;
            } catch (Exception var8) {
               var8.printStackTrace();
               return -3L;
            }
         } else {
            return -1L;
         }
      }
   }

   public static long computeDirOrFileSize(File f) {
      if (f != null && f.exists()) {
         if (f.isFile()) {
            return f.length();
         } else {
            long length = 0L;
            File[] list = f.listFiles();
            if (list != null) {
               File[] var7 = list;
               int var6 = list.length;

               for (int var5 = 0; var5 < var6; ++var5) {
                  File item = var7[var5];
                  if (item.isFile()) {
                     length += item.length();
                  } else {
                     length += computeDirOrFileSize(item);
                  }
               }
            }

            return length;
         }
      } else {
         return 0L;
      }
   }

   public static final boolean SIMCardReady() {
      checkPermission();
      Context context = com.uzmap.pkg.uzcore.b.a().b();
      TelephonyManager telephonyMgr = (TelephonyManager) context.getSystemService("phone");
      int simState = telephonyMgr.getSimState();
      switch (simState) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
            return false;
         case 5:
            return true;
         default:
            return true;
      }
   }

   public static String transcoding(String in) {
      if (TextUtils.isEmpty(in)) {
         return in;
      } else {
         char[] bt = in.toCharArray();
         StringBuilder out = new StringBuilder((int) ((double) bt.length * 1.3D));
         char[] var6 = bt;
         int var5 = bt.length;

         for (int var4 = 0; var4 < var5; ++var4) {
            char c = var6[var4];
            switch (c) {
               case '\b':
                  out.append("\\b");
                  break;
               case '\t':
                  out.append("\\t");
                  break;
               case '\n':
                  out.append("\\n");
                  break;
               case '\f':
                  out.append("\\f");
                  break;
               case '\r':
                  out.append("\\r");
                  break;
               case '"':
               case '\'':
               case '/':
               case '\\':
                  out.append('\\').append(c);
                  break;
               default:
                  if (c <= 31) {
                     out.append(String.format("\\u%04x", Integer.valueOf(c)));
                  } else {
                     out.append(c);
                  }
            }
         }

         String result = out.toString();
         return result;
      }
   }

   public static String getMobileOperatorName() {
      String name = "unknown";
      Context context = com.uzmap.pkg.uzcore.b.a().b();
      TelephonyManager telephonyMgr = (TelephonyManager) context.getSystemService("phone");
      if (telephonyMgr.getSimState() == 5) {
         String imsi = telephonyMgr.getNetworkOperator();
         if (!imsi.equals("46000") && !imsi.equals("46002")) {
            if (imsi.equals("46001")) {
               name = "中国联通";
            } else if (imsi.equals("46003")) {
               name = "中国电信";
            } else {
               name = telephonyMgr.getSimOperatorName();
            }
         } else {
            name = "中国移动";
         }
      }

      return name;
   }

   public static void delete(File file) {
      if (file.isFile()) {
         file.delete();
      } else {
         File[] list = file.listFiles();
         if (list != null && list.length != 0) {
            File[] var5 = list;
            int var4 = list.length;

            for (int var3 = 0; var3 < var4; ++var3) {
               File item = var5[var3];
               delete(item);
            }

            file.delete();
         } else {
            file.delete();
         }
      }
   }

   public static void delete(File file, long timeThreshold) {
      if (file.isFile()) {
         long lm = file.lastModified();
         if (lm > 0L && lm < timeThreshold) {
            file.delete();
         }

      } else {
         File[] list = file.listFiles();
         if (list != null && list.length != 0) {
            File[] var7 = list;
            int var6 = list.length;

            for (int var5 = 0; var5 < var6; ++var5) {
               File item = var7[var5];
               delete(item, timeThreshold);
            }

            file.delete();
         } else {
            file.delete();
         }
      }
   }

   public static final void createShortCuts(Context context, Class<?> cls, String shortCutName) {
      Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
      Parcelable icon = ShortcutIconResource.fromContext(context, com.uzmap.pkg.uzcore.d.a().w);
      addIntent.putExtra("duplicate", false);
      Intent targetIntent = new Intent(context, cls);
      targetIntent.setFlags(268435456);
      addIntent.putExtra("android.intent.extra.shortcut.NAME", shortCutName);
      addIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", icon);
      addIntent.putExtra("android.intent.extra.shortcut.INTENT", targetIntent);
      context.sendBroadcast(addIntent);
   }

   public static void installApp(Context context, String appPath) {
      if (!TextUtils.isEmpty(appPath)) {
         String reallyPath = "";
         File file = new File(appPath.replace("file://", ""));
         if (file.exists()) {
            reallyPath = appPath;
         }

         String action = "android.intent.action.VIEW";
         int var10000 = com.uzmap.pkg.uzcore.external.l.a;
         Intent intent = new Intent(action);
         String mime = getMimeType("apk");
         reallyPath = reallyPath.startsWith("file://") ? reallyPath : "file://" + reallyPath;
         intent.setDataAndType(Uri.parse(reallyPath), mime);
         intent.setFlags(268435456);

         try {
            context.startActivity(intent);
         } catch (Exception var8) {
         }

      }
   }

   public static void uninstallApp(Context context, String packageName) {
      Uri packageURI = Uri.parse("package:" + packageName);
      Intent uninstallIntent = new Intent("android.intent.action.DELETE", packageURI);

      try {
         context.startActivity(uninstallIntent);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   public static String execRootCmd(String cmd) {
      String result = "result : ";

      try {
         java.lang.Process p = Runtime.getRuntime().exec("su ");
         OutputStream outStream = p.getOutputStream();
         DataOutputStream dOutStream = new DataOutputStream(outStream);
         InputStream inStream = p.getInputStream();
         DataInputStream dInStream = new DataInputStream(inStream);
         String str1 = String.valueOf(cmd);
         String str2 = str1 + "\n";
         dOutStream.writeBytes(str2);
         dOutStream.flush();
         String str3 = null;

         for (String line = ""; (line = dInStream.readLine()) != null; str3 = str3 + line) {
            Log.d("result", str3);
         }

         dOutStream.writeBytes("exit\n");
         dOutStream.flush();
         p.waitFor();
         return result;
      } catch (Exception var11) {
         var11.printStackTrace();
         return result;
      }
   }

   public static int execRootCmdSilent(String cmd) {
      try {
         java.lang.Process p = Runtime.getRuntime().exec("su ");
         Object obj = p.getOutputStream();
         DataOutputStream dOutStream = new DataOutputStream((OutputStream) obj);
         String str = String.valueOf(cmd);
         str += "\n";
         dOutStream.writeBytes((String) obj);
         dOutStream.flush();
         dOutStream.writeBytes("exit\n");
         dOutStream.flush();
         p.waitFor();
         int result = p.exitValue();
         return Integer.valueOf(result);
      } catch (Exception var6) {
         var6.printStackTrace();
         return -1;
      }
   }

   public static boolean deviceBeRoot() {
      String key = "device_be_root";
      com.uzmap.pkg.uzkit.data.a sp = com.uzmap.pkg.uzkit.data.a.a();
      boolean contains = sp.a("device_be_root");
      if (contains) {
         return sp.b("device_be_root", false);
      } else if (l) {
         boolean root = isRootSystem();
         sp.a("device_be_root", root);
         return root;
      } else {
         int i = execRootCmdSilent("echo test");
         if (i != -1) {
            sp.a("device_be_root", true);
            return true;
         } else {
            sp.a("device_be_root", false);
            return false;
         }
      }
   }

   public static boolean isRootSystem() {
      String[] paths = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};

      try {
         File file = null;
         String su = "su";
         String[] var6 = paths;
         int var5 = paths.length;

         for (int var4 = 0; var4 < var5; ++var4) {
            String dir = var6[var4];
            file = new File(dir + su);
            if (file != null && file.exists()) {
               return true;
            }
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return false;
   }

   public static String readString(InputStream input) {
      byte[] buffer = null;

      try {
         buffer = readByte(input);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      return buffer != null ? new String(buffer) : "";
   }

   public static String readString(InputStream input, String charset) {
      byte[] buffer = null;

      try {
         buffer = readByte(input);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      if (!TextUtils.isEmpty(charset)) {
         try {
            return buffer != null ? new String(buffer, charset) : "";
         } catch (UnsupportedEncodingException var5) {
         }
      }

      return buffer != null ? new String(buffer) : "";
   }

   public static byte[] readByte(InputStream input) throws IOException {
      return readByte(input, 8192);
   }

   public static byte[] readByte(InputStream input, int buffersize) throws IOException {
      if (input == null) {
         throw new IOException("InputStream can not be null");
      } else {
         ByteArrayBuffer buffer = new ByteArrayBuffer(buffersize);
         byte[] buf = new byte[8192];

         while (true) {
            int readl = input.read(buf);
            if (-1 == readl) {
               return buffer.toByteArray();
            }

            buffer.append(buf, 0, readl);
         }
      }
   }

   public static Object parseToJson(String json) {
      Object o = null;

      try {
         o = new JSONObject(json);
      } catch (Exception var4) {
      }

      if (o == null) {
         try {
            o = new JSONArray(json);
         } catch (Exception var3) {
         }
      }

      return o;
   }

   public static String parseAppParam(Intent data) {
      if (data == null) {
         return "{}";
      } else {
         String appParam = data.getStringExtra("appParam");
         if (appParam != null) {
            return appParam;
         } else {
            Bundle all = data.getExtras();
            if (all == null) {
               return null;
            } else {
               JSONObject json = new JSONObject();
               Set<String> keys = all.keySet();
               Iterator var6 = keys.iterator();

               while (true) {
                  while (var6.hasNext()) {
                     String key = (String) var6.next();
                     Object value = all.get(key);
                     if (value.getClass().isArray()) {
                        Object[] array = (Object[]) value;
                        JSONArray jsa = new JSONArray();
                        Object[] var13 = array;
                        int var12 = array.length;

                        for (int var11 = 0; var11 < var12; ++var11) {
                           Object a = var13[var11];

                           try {
                              jsa.put(a);
                           } catch (Exception var16) {
                              var16.printStackTrace();
                           }
                        }

                        try {
                           json.put(key, jsa);
                        } catch (JSONException var15) {
                           var15.printStackTrace();
                        }
                     } else {
                        try {
                           json.put(key, value);
                        } catch (JSONException var17) {
                           var17.printStackTrace();
                        }
                     }
                  }

                  return json.toString();
               }
            }
         }
      }
   }

   public static boolean isMainProcess(Context context) {
      try {
         int pid = Process.myPid();
         ActivityManager am = (ActivityManager) context.getSystemService("activity");
         List<RunningAppProcessInfo> rInfos = am.getRunningAppProcesses();
         String packageName = context.getPackageName();
         Iterator var6 = rInfos.iterator();

         while (var6.hasNext()) {
            RunningAppProcessInfo appProcess = (RunningAppProcessInfo) var6.next();
            if (appProcess.pid == pid) {
               String processName = appProcess.processName;
               if (packageName.equals(processName)) {
                  return true;
               }
            }
         }
      } catch (Exception var8) {
      }

      return false;
   }

   public static boolean isEmptyIntent(Intent intent) {
      if (intent == null) {
         return true;
      } else if (intent.getExtras() == null && intent.getData() == null) {
         return true;
      } else {
         return intent.hasExtra("profile");
      }
   }

   public static boolean isExtendScheme(String url) {
      if (TextUtils.isEmpty(url)) {
         return false;
      } else {
         return url.startsWith("widget://") || url.startsWith("fs://");
      }
   }
}
