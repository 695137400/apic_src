package com.uzmap.pkg.uzapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.uzmap.pkg.uzcore.ApplicationProcess;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.UZUtility;
import org.json.JSONObject;

import java.io.*;

public class UZFileSystem {
    static String a = "UZMap/";
    static final String[] b = new String[]{"wgt/", "log/", "asset/", "store/"};
    private String c;
    private String d;
    private String e;
    private String f;
    private static boolean g = false;
    private static boolean h = false;
    private static UZFileSystem i;

    private UZFileSystem(Context context) {
        this.init(context);
    }

    public static synchronized boolean assetLocked() {
        return h;
    }

    public static synchronized void setAssetLock(boolean flag) {
        h = flag;
    }

    public static final UZFileSystem get() {
        if (i == null) {
            throw new RuntimeException("UZ FileSystem not initialize!");
        } else {
            return i;
        }
    }

    public static final void initialize(Context context) {
        String sandbox = PropertiesUtil.k();
        if (!TextUtils.isEmpty(sandbox)) {
            if (!sandbox.endsWith(File.separator)) {
                sandbox = sandbox + File.separator;
            }

            a = sandbox;
            g = true;
        }

        i = new UZFileSystem(context);
    }

    private void init(Context context) {
        if (UZUtility.SDCardOnWork()) {
            this.d = UZUtility.getExternaStoragePath();

            try {
                String maindirs = this.d + a;
                File file = new File(maindirs);
                if (!file.exists()) {
                    file.mkdirs();
                }

                String subdir = "";
                String[] var8;
                int var7 = (var8 = b).length;

                String nomediafile;
                for (int var6 = 0; var6 < var7; ++var6) {
                    nomediafile = var8[var6];
                    subdir = maindirs + nomediafile;
                    file = new File(subdir);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                }

                nomediafile = maindirs + ".nomedia";
                File nomedia = new File(nomediafile);
                if (!nomedia.exists()) {
                    nomedia.createNewFile();
                }

                this.c = maindirs;
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        } else {
            this.c = context.getFilesDir().getAbsolutePath() + File.separator;
        }

        this.e = com.uzmap.pkg.uzcore.d.a().applicationInfo.dataDir + File.separator;
        this.f = this.e + "widget" + File.separator;
        File wgtsandbox = new File(this.f);
        if (!wgtsandbox.exists()) {
            wgtsandbox.mkdirs();
        }

    }

    public final String getExtStorageDir() {
        return this.d;
    }

    public final String getAppDataDir() {
        return this.e;
    }

    public final String getVirtualFsDir() {
        return this.c;
    }

    private final String getWidgetSandbox() {
        return this.f;
    }

    public final String getWidgetLoadPath() {
        return this.getVirtualFsDir() + "wgt/";
    }

    public final String getAppStorePath() {
        return this.getVirtualFsDir() + "store/";
    }

    public final String getLogPath() {
        return this.getVirtualFsDir() + "log/";
    }

    public final String getAssetPath() {
        return this.getWidgetSandbox();
    }

    public final String getWidgetRootPath(String appId) {
        String path = this.getVirtualFsDir() + (g ? "" : appId + File.separator);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + File.separator;
    }

    public final String getWidgetCachePath(String appId) {
        String path = this.getWidgetRootPath(appId) + "cache/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + File.separator;
    }

    public final String getWidgetMediaPath(String appId) {
        String path = this.getWidgetRootPath(appId) + "media/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + File.separator;
    }

    public final String getWidgetDownloadPath(String appId) {
        String path = this.getWidgetRootPath(appId) + "download/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + File.separator;
    }

    public final String getWidgetPicturePath(String appId) {
        String path = this.getWidgetRootPath(appId) + "picture/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + File.separator;
    }

    public final boolean mapExtStoragePath(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        } else {
            return path.contains(this.d) || path.contains(this.e) || path.contains("/storage/");
        }
    }

    public final void clearAllCache(String appId) {
        File cache = new File(this.getWidgetCachePath(appId));
        File download = new File(this.getWidgetDownloadPath(appId));
        File media = new File(this.getWidgetMediaPath(appId));
        File picture = new File(this.getWidgetPicturePath(appId));
        UZCoreUtil.delete(cache);
        UZCoreUtil.delete(download);
        UZCoreUtil.delete(media);
        UZCoreUtil.delete(picture);
    }

    public final long computeCacheSize(String appId) {
        long size = 0L;
        File cache = new File(this.getWidgetCachePath(appId));
        size += UZCoreUtil.computeDirOrFileSize(cache);
        File download = new File(this.getWidgetDownloadPath(appId));
        size += UZCoreUtil.computeDirOrFileSize(download);
        File media = new File(this.getWidgetMediaPath(appId));
        size += UZCoreUtil.computeDirOrFileSize(media);
        File picture = new File(this.getWidgetPicturePath(appId));
        size += UZCoreUtil.computeDirOrFileSize(picture);
        return size;
    }

    public boolean synchronizedAsset() {
        boolean isNewVersion = false;
        JSONObject versionInfo = this.readVersionInfo();
        String oldVersion = versionInfo.optString("app", "0.0.0");
        final String curVersion = UZCoreUtil.getAppVersionName();
        int oldVersionCode = versionInfo.optInt("version_code", 0);
        final int curVersionCode = UZCoreUtil.getAppVersionCode();
        boolean nameEquals = oldVersion != null && !curVersion.equals(oldVersion);
        boolean codeEquals = oldVersionCode > 0 && curVersionCode > oldVersionCode;
        if (nameEquals || codeEquals) {
            isNewVersion = true;
        }

        if (isNewVersion) {
            setAssetLock(true);
            (new Thread("UZ-WidgeCopy") {
                public void run() {
                    boolean success = UZFileSystem.this.recurAsset("widget", UZFileSystem.this.getAssetPath());
                    if (success) {
                        UZFileSystem.this.resetAppVersionInfo(curVersion, curVersionCode);
                    }

                    UZFileSystem.setAssetLock(false);
                }
            }).start();
            return false;
        } else {
            int version = versionInfo.optInt("version", -1);
            return version >= 0 ? true : this.recurAsset("widget", this.getAssetPath());
        }
    }

    private boolean recurAsset(String assetDir, String dir) {
        Context context = ApplicationProcess.initialize().b();
        AssetManager assetManager = context.getAssets();
        String[] files = null;

        try {
            files = assetManager.list(assetDir);
        } catch (IOException var16) {
            var16.printStackTrace();
            return false;
        }

        File path = new File(dir);
        if (!path.exists()) {
            path.mkdirs();
        }

        for (int i = 0; i < files.length; ++i) {
            String fileName = files[i];
            if (!fileName.contains(".")) {
                if (assetDir.length() == 0) {
                    this.recurAsset(fileName, dir + fileName + File.separator);
                } else {
                    this.recurAsset(assetDir + File.separator + fileName, dir + fileName + File.separator);
                }
            } else {
                File outFile = new File(path, fileName);
                if (outFile.exists()) {
                    outFile.delete();
                }

                InputStream inputStream = null;
                if (assetDir.length() != 0) {
                    try {
                        inputStream = assetManager.open(assetDir + File.separator + fileName);
                    } catch (IOException var15) {
                        var15.printStackTrace();
                    }
                } else {
                    try {
                        inputStream = assetManager.open(fileName);
                    } catch (IOException var14) {
                        var14.printStackTrace();
                    }
                }

                try {
                    OutputStream outStream = new FileOutputStream(outFile);
                    byte[] buf = new byte[8192];

                    int len;
                    while ((len = inputStream.read(buf)) > 0) {
                        outStream.write(buf, 0, len);
                    }

                    outStream.flush();
                    inputStream.close();
                    outStream.close();
                } catch (Exception var17) {
                    var17.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }

    public int readIncrementVersion() {
        JSONObject json = this.readVersionInfo();
        int version = json.optInt("version", -1);
        if (assetLocked()) {
            version = -1;
        }

        return version;
    }

    private void resetAppVersionInfo(String versionName, int versionCode) {
        try {
            JSONObject json = new JSONObject();
            json.put("app", versionName);
            json.put("version_code", versionCode);
            json.put("version", 0);
            this.writeVersionInfo(json);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private JSONObject readVersionInfo() {
        JSONObject json = new JSONObject();
        String infoFile = this.getAssetPath() + ".APIcloud";
        File file = new File(infoFile);
        if (file.exists()) {
            String code = null;

            try {
                FileInputStream finput = new FileInputStream(file);
                code = UZCoreUtil.readString(finput);
                finput.close();
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            if (code != null) {
                try {
                    json = new JSONObject(code);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
            }
        }

        return json;
    }

    private void writeVersionInfo(JSONObject json) {
        try {
            String infoFile = this.getAssetPath() + ".APIcloud";
            File file = new File(infoFile);
            File p = file.getParentFile();
            if (!p.exists()) {
                p.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream output = new FileOutputStream(file);
            output.write(json.toString().getBytes());
            output.flush();
            output.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }
}
