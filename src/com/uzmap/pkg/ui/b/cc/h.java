package com.uzmap.pkg.ui.b.cc;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.widget.ImageView.ScaleType;
import com.uzmap.pkg.ui.b.l;

public class h extends com.uzmap.pkg.ui.b.j<Bitmap> {
    private static final Object f = new Object();
    private final l.b<Bitmap> a;
    private final Config b;
    private final int c;
    private final int d;
    private final ScaleType e;

    public h(String url, l.b<Bitmap> listener, int maxWidth, int maxHeight, ScaleType scaleType, Config decodeConfig, com.uzmap.pkg.ui.b.l.a1 errorListener) {
        super(0, url, errorListener);
        this.setRetryPolicy(new com.uzmap.pkg.ui.b.d(10000, 2, 2.0F));
        this.a = listener;
        this.b = decodeConfig;
        this.c = maxWidth;
        this.d = maxHeight;
        this.e = scaleType;
    }

    private static int a(int maxPrimary, int maxSecondary, int actualPrimary, int actualSecondary, ScaleType scaleType) {
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary;
        } else if (scaleType == ScaleType.FIT_XY) {
            return maxPrimary == 0 ? actualPrimary : maxPrimary;
        } else {
            double ratio;
            if (maxPrimary == 0) {
                ratio = (double) maxSecondary / (double) actualSecondary;
                return (int) ((double) actualPrimary * ratio);
            } else if (maxSecondary == 0) {
                return maxPrimary;
            } else {
                ratio = (double) actualSecondary / (double) actualPrimary;
                int resized = maxPrimary;
                if (scaleType == ScaleType.CENTER_CROP) {
                    if ((double) maxPrimary * ratio < (double) maxSecondary) {
                        resized = (int) ((double) maxSecondary / ratio);
                    }

                    return resized;
                } else {
                    if ((double) maxPrimary * ratio > (double) maxSecondary) {
                        resized = (int) ((double) maxSecondary / ratio);
                    }

                    return resized;
                }
            }
        }
    }

    static int a(int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
        double wr = (double) actualWidth / (double) desiredWidth;
        double hr = (double) actualHeight / (double) desiredHeight;
        double ratio = Math.min(wr, hr);

        float n;
        for (n = 1.0F; (double) (n * 2.0F) <= ratio; n *= 2.0F) {
        }

        return (int) n;
    }

    public aaemnu getPriority() {
        return aaemnu.a;
    }

    protected l<Bitmap> parseNetworkResponse(com.uzmap.pkg.ui.b.i response) {
        synchronized (f) {
            l var10000;
            try {
                var10000 = this.a(response);
            } catch (OutOfMemoryError var4) {
                com.uzmap.pkg.ui.b.p.c("Caught OOM for %d byte image, url=%s", response.b.length, this.getUrl());
                return com.uzmap.pkg.ui.b.l.a(new com.uzmap.pkg.ui.b.aa.e(var4));
            }

            return var10000;
        }
    }

    private l<Bitmap> a(com.uzmap.pkg.ui.b.i response) {
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

        return bitmap == null ? com.uzmap.pkg.ui.b.l.a(new com.uzmap.pkg.ui.b.aa.e(response)) : com.uzmap.pkg.ui.b.l.a(bitmap, com.uzmap.pkg.ui.b.cc.e.a(response));
    }

    protected void a(Bitmap response) {
        this.a.a(response);
    }

    // $FF: synthetic method
    protected void deliverResponse(Bitmap var1) {
        this.a(var1);
    }
}
