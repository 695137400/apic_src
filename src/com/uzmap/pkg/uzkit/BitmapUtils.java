//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzkit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BitmapUtils {
    private BitmapUtils() {
    }

    public static int computeSampleSize(int width, int height, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(width, height, minSideLength, maxNumOfPixels);
        return initialSize <= 8 ? nextPowerOf2(initialSize) : (initialSize + 7) / 8 * 8;
    }

    private static int computeInitialSampleSize(int w, int h, int minSideLength, int maxNumOfPixels) {
        if (maxNumOfPixels == -1 && minSideLength == -1) {
            return 1;
        } else {
            int lowerBound = maxNumOfPixels == -1 ? 1 : (int)Math.ceil(Math.sqrt((double)(w * h) / (double)maxNumOfPixels));
            if (minSideLength == -1) {
                return lowerBound;
            } else {
                int sampleSize = Math.min(w / minSideLength, h / minSideLength);
                return Math.max(sampleSize, lowerBound);
            }
        }
    }

    public static int computeSampleSizeLarger(int w, int h, int minSideLength) {
        int initialSize = Math.max(w / minSideLength, h / minSideLength);
        if (initialSize <= 1) {
            return 1;
        } else {
            return initialSize <= 8 ? prevPowerOf2(initialSize) : initialSize / 8 * 8;
        }
    }

    public static int computeSampleSizeLarger(float scale) {
        int initialSize = (int)Math.floor((double)(1.0F / scale));
        if (initialSize <= 1) {
            return 1;
        } else {
            return initialSize <= 8 ? prevPowerOf2(initialSize) : initialSize / 8 * 8;
        }
    }

    public static int computeSampleSize(float scale) {
        assertTrue(scale > 0.0F);
        int initialSize = Math.max(1, (int)Math.ceil((double)(1.0F / scale)));
        return initialSize <= 8 ? nextPowerOf2(initialSize) : (initialSize + 7) / 8 * 8;
    }

    public static Bitmap resizeDownToPixels(Bitmap bitmap, int targetPixels, boolean recycle) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scale = (float)Math.sqrt((double)targetPixels / (double)(width * height));
        return scale >= 1.0F ? bitmap : resizeBitmapByScale(bitmap, scale, recycle);
    }

    public static Bitmap resizeBitmapByScale(Bitmap bitmap, float scale, boolean recycle) {
        int width = Math.round((float)bitmap.getWidth() * scale);
        int height = Math.round((float)bitmap.getHeight() * scale);
        if (width == bitmap.getWidth() && height == bitmap.getHeight()) {
            return bitmap;
        } else {
            Bitmap target = Bitmap.createBitmap(width, height, getConfig(bitmap));
            Canvas canvas = new Canvas(target);
            canvas.scale(scale, scale);
            Paint paint = new Paint(6);
            canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
            if (recycle) {
                bitmap.recycle();
            }

            return target;
        }
    }

    private static Config getConfig(Bitmap bitmap) {
        Config config = bitmap.getConfig();
        if (config == null) {
            config = Config.ARGB_8888;
        }

        return config;
    }

    public static Bitmap resizeDownBySideLength(Bitmap bitmap, int maxLength, boolean recycle) {
        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();
        float scale = Math.min((float)maxLength / (float)srcWidth, (float)maxLength / (float)srcHeight);
        return scale >= 1.0F ? bitmap : resizeBitmapByScale(bitmap, scale, recycle);
    }

    public static Bitmap resizeDownIfTooBig(Bitmap bitmap, int targetSize, boolean recycle) {
        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();
        float scale = Math.max((float)targetSize / (float)srcWidth, (float)targetSize / (float)srcHeight);
        return scale > 0.5F ? bitmap : resizeBitmapByScale(bitmap, scale, recycle);
    }

    public static Bitmap cropCenter(Bitmap bitmap, boolean recycle) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == height) {
            return bitmap;
        } else {
            int size = Math.min(width, height);
            Bitmap target = Bitmap.createBitmap(size, size, getConfig(bitmap));
            Canvas canvas = new Canvas(target);
            canvas.translate((float)((size - width) / 2), (float)((size - height) / 2));
            Paint paint = new Paint(2);
            canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
            if (recycle) {
                bitmap.recycle();
            }

            return target;
        }
    }

    public static Bitmap resizeDownAndCropCenter(Bitmap bitmap, int size, boolean recycle) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int minSide = Math.min(w, h);
        if (w == h && minSide <= size) {
            return bitmap;
        } else {
            size = Math.min(size, minSide);
            float scale = Math.max((float)size / (float)bitmap.getWidth(), (float)size / (float)bitmap.getHeight());
            Bitmap target = Bitmap.createBitmap(size, size, getConfig(bitmap));
            int width = Math.round(scale * (float)bitmap.getWidth());
            int height = Math.round(scale * (float)bitmap.getHeight());
            Canvas canvas = new Canvas(target);
            canvas.translate((float)(size - width) / 2.0F, (float)(size - height) / 2.0F);
            canvas.scale(scale, scale);
            Paint paint = new Paint(6);
            canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
            if (recycle) {
                bitmap.recycle();
            }

            return target;
        }
    }

    public static void recycleSilently(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                bitmap.recycle();
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        }
    }

    public static Bitmap rotateBitmap(Bitmap source, int rotation, boolean recycle) {
        if (rotation == 0) {
            return source;
        } else {
            int w = source.getWidth();
            int h = source.getHeight();
            Matrix m = new Matrix();
            m.postRotate((float)rotation);
            Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, w, h, m, true);
            if (recycle) {
                source.recycle();
            }

            return bitmap;
        }
    }

    public static Bitmap createVideoThumbnail(String filePath) {
        Class<?> clazz = null;
        Object instance = null;

        Bitmap var7;
        try {
            clazz = Class.forName("android.media.MediaMetadataRetriever");
            instance = clazz.newInstance();
            Method method = clazz.getMethod("setDataSource", String.class);
            method.invoke(instance, filePath);
            if (VERSION.SDK_INT <= 9) {
                var7 = (Bitmap)clazz.getMethod("captureFrame").invoke(instance);
                return var7;
            }

            byte[] data = (byte[])clazz.getMethod("getEmbeddedPicture").invoke(instance);
            if (data != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (bitmap != null) {
                    var7 = bitmap;
                    return var7;
                }
            }

            var7 = (Bitmap)clazz.getMethod("getFrameAtTime").invoke(instance);
        } catch (IllegalArgumentException var29) {
            return null;
        } catch (RuntimeException var30) {
            return null;
        } catch (InstantiationException var31) {
            var31.printStackTrace();
            return null;
        } catch (InvocationTargetException var32) {
            var32.printStackTrace();
            return null;
        } catch (ClassNotFoundException var33) {
            var33.printStackTrace();
            return null;
        } catch (NoSuchMethodException var34) {
            var34.printStackTrace();
            return null;
        } catch (IllegalAccessException var35) {
            var35.printStackTrace();
            return null;
        } finally {
            try {
                if (instance != null) {
                    clazz.getMethod("release").invoke(instance);
                }
            } catch (Exception var28) {
            }

        }

        return var7;
    }

    public static byte[] compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 90, os);
        return os.toByteArray();
    }

    public static boolean isSupportedByRegionDecoder(String mimeType) {
        if (mimeType == null) {
            return false;
        } else {
            mimeType = mimeType.toLowerCase();
            return mimeType.startsWith("image/") && !mimeType.equals("image/gif") && !mimeType.endsWith("bmp");
        }
    }

    public static boolean isRotationSupported(String mimeType) {
        if (mimeType == null) {
            return false;
        } else {
            mimeType = mimeType.toLowerCase();
            return mimeType.equals("image/jpeg");
        }
    }

    public static byte[] compressToBytes(Bitmap bitmap, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(65536);
        bitmap.compress(CompressFormat.JPEG, quality, baos);
        return baos.toByteArray();
    }

    public static int nextPowerOf2(int n) {
        if (n > 0 && n <= 1073741824) {
            --n;
            n |= n >> 16;
            n |= n >> 8;
            n |= n >> 4;
            n |= n >> 2;
            n |= n >> 1;
            return n + 1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static int prevPowerOf2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            return Integer.highestOneBit(n);
        }
    }

    public static void assertTrue(boolean cond) {
        if (!cond) {
            throw new AssertionError();
        }
    }
}
