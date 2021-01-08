//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package compile;

public class Properties {
    static final String MAM_HOST = "https://a.apicloud.com";
    static final String MCM_HOST = "https://d.apicloud.com";
    static final String MSM_HOST = "http://s.apicloud.com";
    static final String PUSH_HOST = "http://p.apicloud.com";
    static final String REPORT_HOST = "http://r.apicloud.com";
    static final String STORE_HOST = "http://as.apicloud.com";
    static final String APPID = "A6907937999534";
    static final String DESCRIPTOR = "sdk";
    static final String WIDGET_KEY = "Q+wU4Oby+7GALeG/Wu2oAVEPmBNlwOGaml4tszLHwnyxBgCr";
    static final String CLOUD_KEY = "04yD0N5y6d1ndfmSw8LF";
    static final boolean S_MODE = false;
    static final long CLOUD_STAMP = 1452484708230L;
    static final boolean LOADER = false;
    static final String LOADER_VER = "1.1.32";
    static final String CERT_PSW = null;
    static final String SAND_BOX = null;
    static final boolean DEVELOPER_MODE = false;
    static final boolean AUTH = !"https://a.apicloud.com".contains("apicloud") && !"https://a.apicloud.com".contains("192.168.13.183");
    static final boolean TELEVISION = false;
    static final boolean PROMOTION = false;

    public Properties() {
    }

    public static boolean promotion() {
        return false;
    }

    public static boolean television() {
        return false;
    }

    public static String mamHost() {
        return validUrl("https://a.apicloud.com");
    }

    public static String mcmHost() {
        return validUrl("https://d.apicloud.com");
    }

    public static String msmHost() {
        return validUrl("http://s.apicloud.com");
    }

    public static String pushHost() {
        return validUrl("http://p.apicloud.com");
    }

    public static String storeHost() {
        return validUrl("http://as.apicloud.com");
    }

    public static String analysisHost() {
        return validUrl("http://r.apicloud.com");
    }

    public static String widgetKey() {
        return "Q+wU4Oby+7GALeG/Wu2oAVEPmBNlwOGaml4tszLHwnyxBgCr";
    }

    public static String certPsw() {
        return CERT_PSW;
    }

    public static String sandbox() {
        return SAND_BOX;
    }

    public static boolean developerMode() {
        return false;
    }

    public static boolean auth() {
        return AUTH;
    }

    public static String cloudKey() {
        return "04yD0N5y6d1ndfmSw8LF";
    }

    private static String validUrl(String var0) {
        String var1 = var0;
        if (null != var0 && var0.endsWith("/")) {
            var1 = var0.substring(0, var0.length() - 1);
        }

        if (null != var0 && !var0.startsWith("http")) {
            var1 = "http://" + var0;
        }

        return var1;
    }

    public static boolean smode() {
        return false;
    }

    public static long cloudStamp() {
        return 1452484708230L;
    }

    public static boolean loader() {
        return false;
    }

    public static String loaderVer() {
        return "1.1.32";
    }

    public static String appId() {
        return "A6907937999534";
    }

    public static String descriptor() {
        return "sdk";
    }
}
