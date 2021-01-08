//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;
import com.uzmap.pkg.a.b.d;
import com.uzmap.pkg.a.b.i;
import com.uzmap.pkg.a.b.j;
import com.uzmap.pkg.a.b.l;
import com.uzmap.pkg.a.b.p;
import com.uzmap.pkg.a.b.a1.e;
import com.uzmap.pkg.a.b.l.a;
import com.uzmap.pkg.a.b.l.b;

public class h extends j<Bitmap> {
    private final b<Bitmap> a;
    private final Config b;
    private final int c;
    private final int d;
    private ScaleType e;
    private static final Object f = new Object();

    public h(String url, b<Bitmap> listener, int maxWidth, int maxHeight, ScaleType scaleType, Config decodeConfig, a errorListener) {
        super(0, url, errorListener);
        this.setRetryPolicy(new d(10000, 2, 2.0F));
        this.a = listener;
        this.b = decodeConfig;
        this.c = maxWidth;
        this.d = maxHeight;
        this.e = scaleType;
    }

    public com.uzmap.pkg.a.b.j.aa getPriority() {
        return com.uzmap.pkg.a.b.j.aa.a;
    }

    private static int a(int maxPrimary, int maxSecondary, int actualPrimary, int actualSecondary, ScaleType scaleType) {
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary;
        } else if (scaleType == ScaleType.FIT_XY) {
            return maxPrimary == 0 ? actualPrimary : maxPrimary;
        } else {
            double ratio;
            if (maxPrimary == 0) {
                ratio = (double)maxSecondary / (double)actualSecondary;
                return (int)((double)actualPrimary * ratio);
            } else if (maxSecondary == 0) {
                return maxPrimary;
            } else {
                ratio = (double)actualSecondary / (double)actualPrimary;
                int resized = maxPrimary;
                if (scaleType == ScaleType.CENTER_CROP) {
                    if ((double)maxPrimary * ratio < (double)maxSecondary) {
                        resized = (int)((double)maxSecondary / ratio);
                    }

                    return resized;
                } else {
                    if ((double)maxPrimary * ratio > (double)maxSecondary) {
                        resized = (int)((double)maxSecondary / ratio);
                    }

                    return resized;
                }
            }
        }
    }

    protected l<Bitmap> parseNetworkResponse(i response) {
        synchronized(f) {
            l var10000;
            try {
                var10000 = this.a(response);
            } catch (OutOfMemoryError var4) {
                p.c("Caught OOM for %d byte image, url=%s", new Object[]{response.b.length, this.getUrl()});
                return l.a(new e(var4));
            }

            return var10000;
        }
    }

    private l<Bitmap> a(i response) {
        byte[] data = response.b;
        Options decodeOptions = new Options();
        Bitmap bitmap = null;
        if (this.c == 0 && this.d == 0) {
            decodeOptions.inPreferredConfig = this.b;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);
        } else {
            decodeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);
            int actualWidth = decodeOptions.outWidth;
            int actualHeight = decodeOptions.outHeight;
            int desiredWidth = a(this.c, this.d, actualWidth, actualHeight, this.e);
            int desiredHeight = a(this.d, this.c, actualHeight, actualWidth, this.e);
            decodeOptions.inJustDecodeBounds = false;
            decodeOptions.inSampleSize = a(actualWidth, actualHeight, desiredWidth, desiredHeight);
            Bitmap tempBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);
            if (tempBitmap == null || tempBitmap.getWidth() <= desiredWidth && tempBitmap.getHeight() <= desiredHeight) {
                bitmap = tempBitmap;
            } else {
                bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth, desiredHeight, true);
                tempBitmap.recycle();
            }
        }

        return bitmap == null ? l.a(new e(response)) : l.a(bitmap, com.uzmap.pkg.a.b.c1.e.a(response));
    }

    protected void a(Bitmap response) {
        this.a.a(response);
    }

    static int a(int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
        double wr = (double)actualWidth / (double)desiredWidth;
        double hr = (double)actualHeight / (double)desiredHeight;
        double ratio = Math.min(wr, hr);

        float n;
        for(n = 1.0F; (double)(n * 2.0F) <= ratio; n *= 2.0F) {
        }

        return (int)n;
    }
}
