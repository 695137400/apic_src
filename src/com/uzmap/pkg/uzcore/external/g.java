package com.uzmap.pkg.uzcore.external;

import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

public class g {
    public static Bitmap a(String filePath, int kind) {
        boolean wantMini = kind == 1;
        int targetSize = wantMini ? 250 : 96;
        int maxPixels = wantMini ? 196608 : 19200;
        g.a sizedThumbnailBitmap = new g.a();
        Bitmap bitmap = null;
        f.a fileType = f.a(filePath);
        if (fileType != null && fileType.a == 31) {
            a(filePath, targetSize, maxPixels, sizedThumbnailBitmap);
            bitmap = sizedThumbnailBitmap.b;
        }

        if (bitmap == null) {
            label185:
            {
                FileInputStream stream = null;

                try {
                    stream = new FileInputStream(filePath);
                    FileDescriptor fd = stream.getFD();
                    Options options = new Options();
                    options.inSampleSize = 1;
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(fd, null, options);
                    if (!options.mCancel && options.outWidth != -1 && options.outHeight != -1) {
                        options.inSampleSize = a(options, targetSize, maxPixels);
                        options.inJustDecodeBounds = false;
                        options.inDither = false;
                        options.inPreferredConfig = Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeFileDescriptor(fd, null, options);
                        break label185;
                    }
                } catch (IOException var22) {
                    break label185;
                } catch (OutOfMemoryError var23) {
                    break label185;
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException var21) {
                    }

                }

                return null;
            }
        }

        if (kind == 3) {
            bitmap = a(bitmap, 96, 96, 2);
        }

        return bitmap;
    }

    public static Bitmap a(Bitmap source, int width, int height, int options) {
        if (source == null) {
            return null;
        } else {
            float scale;
            if (source.getWidth() < source.getHeight()) {
                scale = (float) width / (float) source.getWidth();
            } else {
                scale = (float) height / (float) source.getHeight();
            }

            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            Bitmap thumbnail = a(matrix, source, width, height, 1 | options);
            return thumbnail;
        }
    }

    private static int a(Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = b(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            for (roundedSize = 1; roundedSize < initialSize; roundedSize <<= 1) {
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int b(Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = maxNumOfPixels == -1 ? 1 : (int) Math.ceil(Math.sqrt(w * h / (double) maxNumOfPixels));
        int upperBound = minSideLength == -1 ? 128 : (int) Math.min(Math.floor(w / (double) minSideLength), Math.floor(h / (double) minSideLength));
        if (upperBound < lowerBound) {
            return lowerBound;
        } else if (maxNumOfPixels == -1 && minSideLength == -1) {
            return 1;
        } else {
            return minSideLength == -1 ? lowerBound : upperBound;
        }
    }

    private static Bitmap a(Matrix scaler, Bitmap source, int targetWidth, int targetHeight, int options) {
        boolean scaleUp = (options & 1) != 0;
        boolean recycle = (options & 2) != 0;
        int deltaX = source.getWidth() - targetWidth;
        int deltaY = source.getHeight() - targetHeight;
        int dx1;
        int dy1;
        if (scaleUp || deltaX >= 0 && deltaY >= 0) {
            float bitmapWidthF = (float) source.getWidth();
            float bitmapHeightF = (float) source.getHeight();
            float bitmapAspect = bitmapWidthF / bitmapHeightF;
            float viewAspect = (float) targetWidth / (float) targetHeight;
            float scale;
            if (bitmapAspect > viewAspect) {
                scale = (float) targetHeight / bitmapHeightF;
                if (scale >= 0.9F && scale <= 1.0F) {
                    scaler = null;
                } else {
                    scaler.setScale(scale, scale);
                }
            } else {
                scale = (float) targetWidth / bitmapWidthF;
                if (scale >= 0.9F && scale <= 1.0F) {
                    scaler = null;
                } else {
                    scaler.setScale(scale, scale);
                }
            }

            Bitmap b1;
            if (scaler != null) {
                b1 = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), scaler, true);
            } else {
                b1 = source;
            }

            if (recycle && b1 != source) {
                source.recycle();
            }

            dx1 = Math.max(0, b1.getWidth() - targetWidth);
            dy1 = Math.max(0, b1.getHeight() - targetHeight);
            Bitmap b2 = Bitmap.createBitmap(b1, dx1 / 2, dy1 / 2, targetWidth, targetHeight);
            if (b2 != b1 && (recycle || b1 != source)) {
                b1.recycle();
            }

            return b2;
        } else {
            Bitmap b2 = Bitmap.createBitmap(targetWidth, targetHeight, Config.ARGB_8888);
            Canvas c = new Canvas(b2);
            int deltaXHalf = Math.max(0, deltaX / 2);
            int deltaYHalf = Math.max(0, deltaY / 2);
            Rect src = new Rect(deltaXHalf, deltaYHalf, deltaXHalf + Math.min(targetWidth, source.getWidth()), deltaYHalf + Math.min(targetHeight, source.getHeight()));
            dx1 = (targetWidth - src.width()) / 2;
            dy1 = (targetHeight - src.height()) / 2;
            Rect dst = new Rect(dx1, dy1, targetWidth - dx1, targetHeight - dy1);
            c.drawBitmap(source, src, dst, null);
            if (recycle) {
                source.recycle();
            }

            c.setBitmap(null);
            return b2;
        }
    }

    private static void a(String filePath, int targetSize, int maxPixels, g.a sizedThumbBitmap) {
        if (filePath != null) {
            ExifInterface exif = null;
            byte[] thumbData = null;

            try {
                exif = new ExifInterface(filePath);
                thumbData = exif.getThumbnail();
            } catch (IOException var12) {
            }

            Options fullOptions = new Options();
            Options exifOptions = new Options();
            int exifThumbWidth = 0;
            if (thumbData != null) {
                exifOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(thumbData, 0, thumbData.length, exifOptions);
                exifOptions.inSampleSize = a(exifOptions, targetSize, maxPixels);
                exifThumbWidth = exifOptions.outWidth / exifOptions.inSampleSize;
            }

            fullOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, fullOptions);
            fullOptions.inSampleSize = a(fullOptions, targetSize, maxPixels);
            int fullThumbWidth = fullOptions.outWidth / fullOptions.inSampleSize;
            if (thumbData != null && exifThumbWidth >= fullThumbWidth) {
                int width = exifOptions.outWidth;
                int height = exifOptions.outHeight;
                exifOptions.inJustDecodeBounds = false;
                sizedThumbBitmap.b = BitmapFactory.decodeByteArray(thumbData, 0, thumbData.length, exifOptions);
                if (sizedThumbBitmap.b != null) {
                    sizedThumbBitmap.a = thumbData;
                    sizedThumbBitmap.c = width;
                    sizedThumbBitmap.d = height;
                }
            } else {
                fullOptions.inJustDecodeBounds = false;
                sizedThumbBitmap.b = BitmapFactory.decodeFile(filePath, fullOptions);
            }

        }
    }

    public static int a(String imgPath) {
        short degree = 0;

        try {
            ExifInterface exifInfo = new ExifInterface(imgPath);
            int orientation = exifInfo.getAttributeInt("Orientation", 1);
            switch (orientation) {
                case 3:
                    degree = 180;
                case 4:
                case 5:
                case 7:
                default:
                    break;
                case 6:
                    degree = 90;
                    break;
                case 8:
                    degree = 270;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return degree;
    }

    public static Bitmap a(Bitmap bitmap, int degree) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate((float) degree);
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
            return resizedBitmap;
        } catch (Exception var6) {
            return bitmap;
        }
    }

    static class a {
        public byte[] a;
        public Bitmap b;
        public int c;
        public int d;
    }
}
