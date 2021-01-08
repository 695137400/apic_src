package com.uzmap.pkg.uzkit.data;

import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;
import android.webkit.URLUtil;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.aa.j;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import com.uzmap.pkg.uzcore.uzmodule.aa.r;
import com.uzmap.pkg.uzcore.uzmodule.e;
import com.uzmap.pkg.uzkit.UZUtility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.*;

public class d {
    static String a = "";

    public static e a(String appId, boolean s) {
        r args = new r(null, null, false);
        args.d = appId;
        return a(args, s);
    }

    public static e a(r args, boolean s) {
        String appId = args.d;
        if (TextUtils.isEmpty(appId)) {
            return null;
        } else {
            e resultInfo = null;
            if (com.uzmap.pkg.uzapp.b.o()) {
                resultInfo = e(appId, s);
                if (resultInfo != null) {
                    resultInfo.U = args;
                }

                return resultInfo;
            } else {
                boolean smode = com.uzmap.pkg.uzapp.b.n();
                if (smode) {
                    resultInfo = c(appId, smode);
                    if (resultInfo != null) {
                        if (resultInfo != null) {
                            resultInfo.U = args;
                        }

                        return resultInfo;
                    }
                }

                String aseert = "widget/wgt/";
                String absolutePath = aseert + appId + File.separator;
                resultInfo = d(absolutePath, s);
                if (resultInfo == null) {
                    resultInfo = e(appId, s);
                }

                if (resultInfo != null) {
                    resultInfo.U = args;
                }

                return resultInfo;
            }
        }
    }

    public static JSONObject a(UZModuleContext args) {
        String uzpath = UZFileSystem.get().getWidgetLoadPath();
        File wgtFile = new File(uzpath);
        if (!wgtFile.exists()) {
            wgtFile.mkdirs();
        }

        File[] dirFiles = wgtFile.listFiles();
        String[] dirs = wgtFile.list();
        JSONObject retJson = new JSONObject();
        if (dirFiles != null) {
            try {
                JSONArray data = new JSONArray();

                for (int i = 0; i < dirFiles.length; ++i) {
                    e info = a(dirFiles[i], false);
                    if (info != null) {
                        JSONObject item = new JSONObject();
                        item.put("name", info.t);
                        String wgtId = info.r;
                        String dir = dirs[i];
                        if (!dir.equals(wgtId)) {
                            wgtId = dir;
                        }

                        item.put("widgetId", wgtId);
                        String iconPath = info.C.replaceFirst("file://", "");
                        item.put("iconPath", iconPath);
                        data.put(item);
                    }
                }

                retJson.put("data", data);
                retJson.put("status", true);
            } catch (Exception var13) {
                var13.printStackTrace();
            }

            return retJson;
        } else {
            return retJson;
        }
    }

    public static e a(boolean s) {
        e resultInfo = null;
        String path = "widget" + File.separator;
        resultInfo = d(path, s);
        if (resultInfo != null) {
            a = resultInfo.r;
        }

        return resultInfo;
    }

    public static e b(String widgetId, boolean s) {
        e resultInfo = null;
        String path = UZFileSystem.get().getAssetPath();
        File wgtDir = new File(path);
        resultInfo = a(wgtDir, s);
        return resultInfo;
    }

    private static e c(String widgetId, boolean s) {
        e resultInfo = null;
        String path = UZFileSystem.get().getAssetPath();
        path = path + "wgt/" + widgetId + File.separator;
        File wgtDir = new File(path);
        resultInfo = a(wgtDir, s);
        return resultInfo;
    }

    private static e d(String path, boolean s) {
        e resultInfo = null;
        String config = path + "config.xml";

        try {
            Context context = com.uzmap.pkg.uzcore.b.a().b();
            InputStream input = context.getAssets().open(config);
            resultInfo = a(input, s);
            input.close();
            if (resultInfo != null) {
                String content = resultInfo.z;
                String widgetRoot = "file:///android_asset/" + path;
                if (content != null && !URLUtil.isValidUrl(content)) {
                    String abs = widgetRoot + "index.html";
                    content = UZUtility.makeAbsUrl(abs, content);
                }

                resultInfo.D = widgetRoot;
                resultInfo.z = content;
                resultInfo.y = content;
                resultInfo.C = widgetRoot + "icon";
                resultInfo.j();
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return resultInfo;
    }

    private static e a(File wgtDir, boolean s) {
        e resultInfo = null;

        try {
            File config = new File(wgtDir, "config.xml");
            if (!config.exists()) {
                return null;
            } else {
                InputStream input = new FileInputStream(config);
                resultInfo = a(input, s);
                input.close();
                if (resultInfo != null) {
                    String content = resultInfo.z;
                    String wgtPath = wgtDir.getAbsolutePath();
                    String widgetRoot = "file://" + wgtPath + File.separator;
                    if (content != null && !URLUtil.isValidUrl(content)) {
                        String abs = widgetRoot + "index.html";
                        content = UZUtility.makeAbsUrl(abs, content);
                    }

                    resultInfo.z = content;
                    resultInfo.y = content;
                    resultInfo.D = widgetRoot;
                    resultInfo.C = "file://" + wgtPath + File.separator + "icon";
                }

                resultInfo.j();
                return resultInfo;
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    private static e a(InputStream in, boolean s) {
        e resultInfo = new e();

        try {
            XmlPullParser parser = Xml.newPullParser();
            String curFeature;
            if (!s) {
                parser.setInput(in, "UTF-8");
            } else {
                byte[] c = UZCoreUtil.readByte(in);
                c = j.b(c);
                curFeature = new String(c);
                StringReader r = new StringReader(curFeature);
                parser.setInput(r);
            }

            curFeature = null;
            boolean needContinue = true;

            do {
                int tokenType = parser.next();
                switch (tokenType) {
                    case 1:
                        needContinue = false;
                        break;
                    case 2:
                        String localName = parser.getName().toLowerCase();
                        String name;
                        String value;
                        if ("widget".equals(localName)) {
                            resultInfo.r = parser.getAttributeValue(null, "id");
                            resultInfo.s = parser.getAttributeValue(null, "version");
                            name = parser.getAttributeValue(null, "security");
                            if (TextUtils.isEmpty(name)) {
                                resultInfo.Q = s;
                            } else {
                                resultInfo.Q = "true".equalsIgnoreCase(name);
                            }

                            value = parser.getAttributeValue(null, "loader");
                            resultInfo.S = "true".equalsIgnoreCase(value);
                        } else if ("name".equals(localName)) {
                            resultInfo.t = parser.nextText();
                        } else if ("description".equals(localName)) {
                            resultInfo.u = parser.nextText();
                        } else if ("author".equals(localName)) {
                            resultInfo.w = parser.getAttributeValue(null, "email");
                            resultInfo.x = parser.getAttributeValue(null, "href");
                            resultInfo.v = parser.nextText();
                        } else if ("content".equals(localName)) {
                            resultInfo.z = parser.getAttributeValue(null, "src");
                        } else if ("access".equals(localName)) {
                            resultInfo.B = parser.getAttributeValue(null, "origin");
                        } else if ("preference".equals(localName)) {
                            name = parser.getAttributeValue(null, "name");
                            value = parser.getAttributeValue(null, "value");
                            resultInfo.a(name, value);
                        } else if ("permission".equals(localName)) {
                            name = parser.getAttributeValue(null, "name");
                            resultInfo.b(name);
                        } else if ("feature".equals(localName)) {
                            curFeature = parser.getAttributeValue(null, "name");
                            if (!TextUtils.isEmpty(curFeature)) {
                                resultInfo.a(curFeature, null, null);
                            }
                        } else if ("param".equals(localName)) {
                            name = parser.getAttributeValue(null, "name");
                            value = parser.getAttributeValue(null, "value");
                            resultInfo.a(curFeature, name, value);
                        }
                }
            } while (needContinue);

            return resultInfo;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    private static e e(String appId, boolean s) {
        e resultInfo = null;
        if (appId != null) {
            String loaderPath = UZFileSystem.get().getWidgetLoadPath();
            String separator = File.separator;
            String absolutePath = loaderPath + a + separator + "wgt" + separator + appId + separator;
            resultInfo = a(new File(absolutePath), s);
            if (resultInfo == null) {
                absolutePath = loaderPath + appId + separator;
                resultInfo = a(new File(absolutePath), s);
            }
        }

        return resultInfo;
    }
}
