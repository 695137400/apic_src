//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Base64;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.b;
import com.uzmap.pkg.uzcore.external.UzResourceCache;
import com.uzmap.pkg.uzcore.external.l;
import com.uzmap.pkg.uzkit.data.UZWidgetInfo;
import com.uzmap.pkg.uzkit.fineHttp.v;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

public class UZUtility {
    public UZUtility() {
    }

    public static boolean SDCardOnWork() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getDefaultUseragent() {
        return UZCoreUtil.getDefaultUserAgent();
    }

    public static String getExternalCacheDir() {
        File external = b.a().b().getExternalCacheDir();
        return external != null ? external.getAbsolutePath() + File.separator : null;
    }

    public static String getExternaStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public static String getExternaDownloadDir() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator;
    }

    public static String getCameraStoragePath() {
        File externalDataDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        if (!externalDataDir.exists()) {
            externalDataDir.mkdirs();
        }

        return externalDataDir.getAbsolutePath() + File.separator;
    }

    public static String makeAbsUrl(String baseUrl, String link) {
        return makeAbsUrl(baseUrl, link, false);
    }

    public static String makeAbsUrl(String base, String link, boolean strict) {
        String result;
        if (link != null && !"".equals(link)) {
            try {
                URL url = constructUrl(base, link, strict);
                String pro = url.getProtocol();
                if (pro.contains("http")) {
                    result = url.toExternalForm();
                } else {
                    String file = url.getFile();
                    result = pro + "://" + file;
                }
            } catch (Exception var7) {
                var7.printStackTrace();
                result = link;
            }
        } else {
            result = "";
        }

        return result;
    }

    public static URL constructUrl(String baseUrl, String link) throws Exception {
        return constructUrl(baseUrl, link, false);
    }

    private static URL constructUrl(String base, String link, boolean strict) throws Exception {
        URL url;
        if (!strict && '?' == link.charAt(0)) {
            int index = base.lastIndexOf(63);
            if (-1 != index) {
                base = base.substring(0, index);
            }

            url = new URL(base + link);
        } else {
            url = new URL(new URL(base), link);
        }

        String path = url.getFile();
        boolean modified = false;
        boolean absolute = link.startsWith("/");
        if (!absolute) {
            while(path.startsWith("/.")) {
                if (path.startsWith("/../")) {
                    path = path.substring(3);
                    modified = true;
                } else {
                    if (!path.startsWith("/./") && !path.startsWith("/.")) {
                        break;
                    }

                    path = path.substring(2);
                    modified = true;
                }
            }
        }

        int index;
        while(-1 != (index = path.indexOf("/\\"))) {
            path = path.substring(0, index + 1) + path.substring(index + 2);
            modified = true;
        }

        if (modified) {
            url = new URL(url, path);
        }

        return url;
    }

    public static String makeRealPath(String path, UZWidgetInfo wgtInfo) {
        if (TextUtils.isEmpty(path)) {
            return null;
        } else {
            path = com.uzmap.pkg.uzcore.aa.b.c(path);
            String realPath = null;
            Uri uri = Uri.parse(path);
            String schame = uri.getScheme();
            if (!"http".equals(schame) && !"https".equals(schame)) {
                String appId;
                if ("content".equals(schame)) {
                    appId = "_data";
                    String[] proj = new String[]{appId};
                    ContentResolver cr = b.a().b().getContentResolver();
                    Cursor cursor = cr.query(uri, proj, (String)null, (String[])null, (String)null);
                    if (cursor != null) {
                        try {
                            int column_index = cursor.getColumnIndexOrThrow(appId);
                            cursor.moveToFirst();
                            realPath = cursor.getString(column_index);
                            cursor.close();
                        } catch (Exception var10) {
                        }
                    }
                } else if ("file".equals(schame)) {
                    realPath = uri.getPath();
                    if (realPath == null || realPath.trim().length() == 0) {
                        return null;
                    }

                    if (realPath.startsWith("/android_asset/")) {
                        return path;
                    }
                } else {
                    String wgtroot;
                    if ("fs".equals(schame)) {
                        appId = uri.getSchemeSpecificPart();
                        realPath = appId.replaceFirst("//", "");
                        wgtroot = wgtInfo != null ? wgtInfo.id : "";
                        realPath = UZFileSystem.get().getWidgetRootPath(wgtroot) + realPath;
                    } else if ("cache".equals(schame)) {
                        appId = uri.getSchemeSpecificPart();
                        realPath = appId.replaceFirst("//", "");
                        wgtroot = wgtInfo != null ? wgtInfo.id : "";
                        realPath = UZFileSystem.get().getWidgetRootPath(wgtroot) + realPath;
                    } else if ("widget".equals(schame)) {
                        if (wgtInfo != null) {
                            appId = uri.getSchemeSpecificPart();
                            realPath = appId.replaceFirst("//", "");
                            wgtroot = wgtInfo.widgetPath;
                            realPath = wgtroot + realPath;
                        }
                    } else if ("store".equals(schame)) {
                        appId = uri.getSchemeSpecificPart();
                        realPath = appId.replaceFirst("//", "");
                        wgtroot = UZFileSystem.get().getWidgetLoadPath();
                        realPath = wgtroot + realPath;
                    } else if (!UZFileSystem.get().mapExtStoragePath(path)) {
                        appId = wgtInfo != null ? wgtInfo.id : "";
                        realPath = UZFileSystem.get().getWidgetRootPath(appId) + path;
                    } else {
                        realPath = path;
                    }
                }

                return realPath;
            } else {
                return path;
            }
        }
    }

    public static int guessVideoDuration(String uri) {
        if (TextUtils.isEmpty(uri)) {
            return 0;
        } else {
            int duration = 0;
            Uri video = Uri.parse(uri);
            String schame = video.getScheme();
            Context context = b.a().b();
            if ("content".equals(schame)) {
                String durationcolumns = "duration";
                String[] proj = new String[]{durationcolumns};
                ContentResolver cr = context.getContentResolver();
                Cursor cursor = cr.query(video, proj, (String)null, (String[])null, (String)null);
                int column_index = cursor.getColumnIndexOrThrow(durationcolumns);
                cursor.moveToFirst();
                duration = cursor.getInt(column_index);
                cursor.close();
            }

            return duration;
        }
    }

    public static InputStream guessInputStream(String resurl) throws IOException {
        if (TextUtils.isEmpty(resurl)) {
            return null;
        } else {
            Uri uri = Uri.parse(resurl);
            String schame = uri.getScheme();
            Context context = b.a().b();
            if ("content".equals(schame)) {
                return context.getContentResolver().openInputStream(uri);
            } else if ("file".equals(schame)) {
                resurl = uri.getPath();
                InputStream inputStream = null;
                if (resurl.startsWith("/android_asset/")) {
                    String relativePath = resurl.substring(15);
                    AssetFileDescriptor assetFd = null;

                    try {
                        assetFd = context.getAssets().openFd(relativePath);
                        inputStream = assetFd.createInputStream();
                    } catch (FileNotFoundException var10) {
                        var10.printStackTrace();
                    }

                    try {
                        inputStream = context.getAssets().open(relativePath);
                    } catch (Exception var9) {
                        var9.printStackTrace();
                    }
                } else {
                    try {
                        inputStream = new FileInputStream(resurl);
                    } catch (Exception var8) {
                        var8.printStackTrace();
                    }
                }

                return (InputStream)inputStream;
            } else {
                InputStream inputStream = null;
                resurl = makeRealPath(resurl, (UZWidgetInfo)null).replaceFirst("file://", "");

                try {
                    inputStream = new FileInputStream(resurl);
                } catch (Exception var11) {
                    var11.printStackTrace();
                }

                return inputStream;
            }
        }
    }

    public static String bitmapToBase64(String localPath) {
        String result = "";

        try {
            InputStream input = guessInputStream(localPath);
            if (input == null) {
                return result;
            }

            Bitmap bitmap = BitmapFactory.decodeStream(input);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            input.close();
            byte[] bytes = out.toByteArray();
            byte[] output = Base64.encode(bytes, 2);
            result = new String(output);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return "data:image/jpeg;base64," + result;
    }

    public static String getDeviceId() {
        return UZCoreUtil.getDeviceId();
    }

    public static String toMd5(String inStr) {
        return UZCoreUtil.toMD5(inStr);
    }

    public static Drawable makeDrawable(String drawableStr, UZWidgetInfo wgtInfo) {
        if (drawableStr != null) {
            char first = drawableStr.charAt(0);
            if ('#' == first || 'r' == first || 'R' == first) {
                int color = UZCoreUtil.parseColor(drawableStr);
                return new ColorDrawable(color);
            }

            String url = makeRealPath(drawableStr, wgtInfo);
            Bitmap bitmap = UzResourceCache.get().getImage(url);
            if (bitmap != null) {
                return new BitmapDrawable(b.a().b().getResources(), bitmap);
            }
        }

        return new ColorDrawable(l.c);
    }

    public static Bitmap makeBitmap(String pathName, int maxNumOfPixels) {
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pathName, options);
            if (!options.mCancel && options.outWidth != -1 && options.outHeight != -1) {
                options.inSampleSize = computeSampleSize(options, -1, maxNumOfPixels);
                options.inJustDecodeBounds = false;
                options.inDither = false;
                if (options.outMimeType != null && options.outMimeType.contains("jpeg")) {
                    options.inPreferredConfig = Config.RGB_565;
                }

                return BitmapFactory.decodeFile(pathName, options);
            } else {
                return null;
            }
        } catch (OutOfMemoryError var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static int computeSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            for(roundedSize = 1; roundedSize < initialSize; roundedSize <<= 1) {
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
        double w = (double)options.outWidth;
        double h = (double)options.outHeight;
        int lowerBound = maxNumOfPixels < 0 ? 1 : (int)Math.ceil(Math.sqrt(w * h / (double)maxNumOfPixels));
        int upperBound = minSideLength < 0 ? 128 : (int)Math.min(Math.floor(w / (double)minSideLength), Math.floor(h / (double)minSideLength));
        if (upperBound < lowerBound) {
            return lowerBound;
        } else if (maxNumOfPixels < 0 && minSideLength < 0) {
            return 1;
        } else {
            return minSideLength < 0 ? lowerBound : upperBound;
        }
    }

    public static int getAppVersionCode() {
        return UZCoreUtil.getAppVersionCode();
    }

    public static String getAppVersionName() {
        return UZCoreUtil.getAppVersionName();
    }

    public static Context getBaseContext() {
        return b.a().b();
    }

    public static int dipToPix(int dip) {
        return UZCoreUtil.dipToPix(dip);
    }

    public static final int parseCssColor(String color) {
        return UZCoreUtil.parseColor(color);
    }

    public static final int parseCssPixel(String px) {
        if (px == null) {
            return 0;
        } else if ("auto".equals(px)) {
            return l.d;
        } else {
            px = px.replace("px", "");
            return parseInt(px);
        }
    }

    public static int parseInt(String in) {
        try {
            return Integer.parseInt(in);
        } catch (Exception var2) {
            return 0;
        }
    }

    public static String transcoding(String text) {
        return UZCoreUtil.transcoding(text);
    }

    public static JSONObject xmlToJsonObject(String xmlValue) {
        try {
            return v.b(xmlValue);
        } catch (JSONException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static JSONObject xmlToJsonObject(String xmlValue, String plainTextTag) {
        try {
            if (!TextUtils.isEmpty(plainTextTag)) {
                v.a = plainTextTag;
            }

            JSONObject json = v.b(xmlValue);
            v.a();
            return json;
        } catch (JSONException var3) {
            var3.printStackTrace();
            v.a();
            return null;
        }
    }

    public static String formatFileSize(long size) {
        return Formatter.formatFileSize(b.a().b(), size);
    }

    public static boolean unzip(String archive, String targetPath) {
        File archiveFile = new File(archive);
        if (!archiveFile.exists()) {
            return false;
        } else {
            ZipFile zipFile = null;

            try {
                zipFile = new ZipFile(archiveFile);
            } catch (Exception var16) {
                var16.printStackTrace();
            }

            if (zipFile == null) {
                return false;
            } else {
                Enumeration entrys = zipFile.entries();

                while(true) {
                    while(entrys.hasMoreElements()) {
                        ZipEntry entry = (ZipEntry)entrys.nextElement();
                        String entryName = entry.getName();
                        String path = targetPath + File.separator + entryName;
                        if (entry.isDirectory()) {
                            File dirs = new File(path);
                            if (!dirs.exists()) {
                                dirs.mkdirs();
                            }
                        } else {
                            String fileDir = path.substring(0, path.lastIndexOf(File.separator));
                            File fileDirFile = new File(fileDir);
                            if (!fileDirFile.exists()) {
                                fileDirFile.mkdirs();
                            }

                            try {
                                FileOutputStream output = new FileOutputStream(targetPath + File.separator + entryName);
                                BufferedOutputStream bos = new BufferedOutputStream(output);
                                BufferedInputStream inputBuffer = new BufferedInputStream(zipFile.getInputStream(entry));
                                byte[] readBuffer = new byte[4096];

                                for(int read = inputBuffer.read(readBuffer); read != -1; read = inputBuffer.read(readBuffer)) {
                                    bos.write(readBuffer, 0, read);
                                }

                                bos.close();
                            } catch (Exception var17) {
                                var17.printStackTrace();
                                return false;
                            }
                        }
                    }

                    try {
                        zipFile.close();
                    } catch (IOException var15) {
                        var15.printStackTrace();
                    }

                    return true;
                }
            }
        }
    }
}
