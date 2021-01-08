package com.uzmap.pkg.uzcore.external;

import java.util.HashMap;
import java.util.Locale;

public class f {
    private static final HashMap<String, f.a> a = new HashMap();
    private static final HashMap<String, Integer> b = new HashMap();
    private static final HashMap<String, Integer> c = new HashMap();
    private static final HashMap<String, Integer> d = new HashMap();
    private static final HashMap<Integer, String> e = new HashMap();

    static {
        a("MP3", 1, "audio/mpeg", 12297);
        a("MPGA", 1, "audio/mpeg", 12297);
        a("M4A", 2, "audio/mp4", 12299);
        a("WAV", 3, "audio/x-wav", 12296);
        a("AMR", 4, "audio/amr");
        a("AWB", 5, "audio/amr-wb");
        a("OGG", 7, "audio/ogg", 47362);
        a("OGG", 7, "application/ogg", 47362);
        a("OGA", 7, "application/ogg", 47362);
        a("AAC", 8, "audio/aac", 47363);
        a("AAC", 8, "audio/aac-adts", 47363);
        a("MKA", 9, "audio/x-matroska");
        a("MID", 11, "audio/midi");
        a("MIDI", 11, "audio/midi");
        a("XMF", 11, "audio/midi");
        a("RTTTL", 11, "audio/midi");
        a("SMF", 12, "audio/sp-midi");
        a("IMY", 13, "audio/imelody");
        a("RTX", 11, "audio/midi");
        a("OTA", 11, "audio/midi");
        a("MXMF", 11, "audio/midi");
        a("MPEG", 21, "video/mpeg", 12299);
        a("MPG", 21, "video/mpeg", 12299);
        a("MP4", 21, "video/mp4", 12299);
        a("M4V", 22, "video/mp4", 12299);
        a("3GP", 23, "video/3gpp", 47492);
        a("3GPP", 23, "video/3gpp", 47492);
        a("3G2", 24, "video/3gpp2", 47492);
        a("3GPP2", 24, "video/3gpp2", 47492);
        a("MKV", 27, "video/x-matroska");
        a("WEBM", 30, "video/webm");
        a("TS", 28, "video/mp2ts");
        a("AVI", 29, "video/avi");
        a("JPG", 31, "image/jpeg", 14337);
        a("JPEG", 31, "image/jpeg", 14337);
        a("GIF", 32, "image/gif", 14343);
        a("PNG", 33, "image/png", 14347);
        a("BMP", 34, "image/x-ms-bmp", 14340);
        a("WBMP", 35, "image/vnd.wap.wbmp");
        a("WEBP", 36, "image/webp");
        a("M3U", 41, "audio/x-mpegurl", 47633);
        a("M3U", 41, "application/x-mpegurl", 47633);
        a("PLS", 42, "audio/x-scpls", 47636);
        a("WPL", 43, "application/vnd.ms-wpl", 47632);
        a("M3U8", 44, "application/vnd.apple.mpegurl");
        a("M3U8", 44, "audio/mpegurl");
        a("M3U8", 44, "audio/x-mpegurl");
        a("FL", 51, "application/x-android-drm-fl");
        a("TXT", 100, "text/plain", 12292);
        a("HTM", 101, "text/html", 12293);
        a("HTML", 101, "text/html", 12293);
        a("PDF", 102, "application/pdf");
        a("DOC", 104, "application/msword", 47747);
        a("XLS", 105, "application/vnd.ms-excel", 47749);
        a("PPT", 106, "application/mspowerpoint", 47750);
        a("FLAC", 10, "audio/flac", 47366);
        a("ZIP", 107, "application/zip");
        a("MPG", 200, "video/mp2p");
        a("MPEG", 200, "video/mp2p");
    }

    static void a(String extension, int fileType, String mimeType) {
        a.put(extension, new f.a(fileType, mimeType));
        b.put(mimeType, fileType);
    }

    static void a(String extension, int fileType, String mimeType, int mtpFormatCode) {
        a(extension, fileType, mimeType);
        c.put(extension, mtpFormatCode);
        d.put(mimeType, mtpFormatCode);
        e.put(mtpFormatCode, mimeType);
    }

    public static f.a a(String path) {
        int lastDot = path.lastIndexOf(46);
        return lastDot < 0 ? null : a.get(path.substring(lastDot + 1).toUpperCase(Locale.ROOT));
    }

    public static class a {
        public final int a;
        public final String b;

        a(int fileType, String mimeType) {
            this.a = fileType;
            this.b = mimeType;
        }
    }
}
