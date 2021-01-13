package com.uzmap.pkg.uzcore.external;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.uzmap.pkg.ui.b.dd.c;
import com.uzmap.pkg.uzkit.UZUtility;

import java.io.File;
import java.io.InputStream;

public class UzResourceCache {
    private final com.uzmap.pkg.ui.b.dd.c a;
    private static UzResourceCache b;

    private UzResourceCache(Context context) {
        this.a = com.uzmap.pkg.ui.b.dd.c.c(com.uzmap.pkg.ui.b.e.a(context));
    }

    public static UzResourceCache get() {
        if (b == null) {
            b = new UzResourceCache(com.uzmap.pkg.uzcore.b.a().b());
        }

        return b;
    }

    public final Bitmap getImage(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        } else {
            Bitmap bitmap = this.a.a(path);
            if (bitmap != null) {
                return bitmap;
            } else if (path.startsWith("http")) {
                return null;
            } else {
                try {
                    InputStream input = UZUtility.guessInputStream(path);
                    if (input != null) {
                        bitmap = BitmapFactory.decodeStream(input);
                        input.close();
                        if (bitmap != null) {
                            this.a.a(path, bitmap);
                        }

                        return bitmap;
                    }
                } catch (Exception var4) {
                    var4.printStackTrace();
                }

                return null;
            }
        }
    }

    public final void cacheImage(String key, Bitmap bitmap) {
        if (!TextUtils.isEmpty(key) && bitmap != null) {
            this.a.a(key, bitmap);
        }
    }

    public static final File transImageThumbPath(File image) {
        return com.uzmap.pkg.ui.b.dd.c.a(image);
    }

    public final c.a1 hasDiskCache(String url) {
        return this.a.b(url);
    }

    public final void clearDisk(long timeThreshold) {
        this.a.a(timeThreshold);
    }

    public final String makeDiskFile(String url) {
        return this.a.e(url);
    }

    public final c.a1 cacheDisk(String url, String local, String thumbnail) {
        return this.a.a(url, local, thumbnail);
    }

    public final Drawable getDrawable(String path, Context context) {
        Bitmap b = this.getImage(path);
        return b != null ? new BitmapDrawable(context.getResources(), b) : null;
    }
}
