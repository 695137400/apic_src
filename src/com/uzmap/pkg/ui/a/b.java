package com.uzmap.pkg.ui.a;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaRecorder;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzcore.external.l;

import java.io.*;

public class b {
   static boolean a = false;
   private MediaPlayer b;
   private d c;
   private MediaRecorder d;
   private String e;
   private long f;
   private int g;
   private FileInputStream h;

   public void a(d listener) {
      this.c = listener;
   }

   public boolean a(Context context, String fileName) {
      if (this.b != null) {
         return false;
      } else {
         this.a();
         this.b = new MediaPlayer();

         try {
            int question = fileName.indexOf("?");
            if (question > -1) {
               fileName = fileName.substring(0, question);
            }

            boolean network = a && fileName.startsWith("http");
            if (network) {
               this.b.setDataSource(fileName);
            } else {
               FileDescriptor fd = null;
               long offset = 0L;
               long length = 0L;
               if (fileName.contains("android_asset")) {
                  if (fileName.startsWith("file://")) {
                     fileName = fileName.substring(7);
                  }

                  String relativePath = fileName.substring(15);

                  try {
                     AssetFileDescriptor afd = context.getAssets().openFd(relativePath);
                     fd = afd.getFileDescriptor();
                     offset = afd.getStartOffset();
                     length = afd.getLength();
                  } catch (FileNotFoundException var12) {
                     var12.printStackTrace();
                  }
               } else {
                  if (fileName.startsWith("file://")) {
                     fileName = fileName.substring(7);
                  }

                  this.h = new FileInputStream(fileName);
                  fd = this.h.getFD();
                  length = this.h.available();
               }

               this.b.setDataSource(fd, offset, length);
            }

            this.b.setOnErrorListener(new OnErrorListener() {
               public boolean onError(MediaPlayer mp, int what, int extra) {
                  b.this.a();
                  b.this.a(what, extra);
                  return true;
               }
            });
            this.b.setOnCompletionListener(new OnCompletionListener() {
               public void onCompletion(MediaPlayer mp) {
                  b.this.a();
                  if (b.this.c != null) {
                     b.this.c.a();
                  }

               }
            });
            if (!network) {
               this.b.prepare();
               this.b.start();
            } else {
               this.b.setOnPreparedListener(new OnPreparedListener() {
                  public void onPrepared(MediaPlayer mp) {
                     b.this.b.start();
                  }
               });
               this.b.prepareAsync();
            }

            return true;
         } catch (IOException var13) {
            var13.printStackTrace();
            this.a();
            this.a(-1, 0);
            UZCoreUtil.loge("prepare() failed");
            return false;
         }
      }
   }

   public void a() {
      if (this.b != null) {
         try {
            this.b.release();
            this.b = null;
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      if (this.h != null) {
         try {
            this.h.close();
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      }

   }

   public void a(String fileName) {
      if (this.d == null) {
         try {
            this.b(fileName);
            this.e = fileName;
            this.d = new MediaRecorder();
            this.d.setAudioSource(1);
            this.d.setOutputFormat(l.f);
            this.d.setAudioEncoder(1);
            this.d.setOutputFile(fileName);
            this.d.setOnErrorListener(new android.media.MediaRecorder.OnErrorListener() {
               public void onError(MediaRecorder mr, int what, int extra) {
                  b.this.b();
               }
            });
            this.d.prepare();
            this.d.start();
            this.f = System.currentTimeMillis();
         } catch (IOException var3) {
            var3.printStackTrace();
            this.b();
         }

      }
   }

   public void b() {
      if (this.d != null) {
         try {
            this.d.stop();
            long now = System.currentTimeMillis();
            this.d.release();
            this.g = (int) ((now - this.f) / 1000L);
            this.d = null;
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   public String c() {
      return this.e != null ? this.e : "";
   }

   public int d() {
      return this.g;
   }

   private void b(String path) {
      File file = new File(path);
      File parent = file.getParentFile();
      if (parent != null && !parent.exists()) {
         parent.mkdirs();
      }

   }

   public void e() {
      this.a();
      this.b();
   }

   private void a(int what, int extra) {
      if (this.c != null) {
         String msg = "";
         if (what == 1) {
            msg = "MEDIA_ERROR_UNKNOWN";
         } else if (what == 100) {
            msg = "MEDIA_ERROR_SERVER_DIED";
         } else {
            msg = "UNKNOWN";
         }

         switch (extra) {
            case -1010:
               msg = msg + ">MEDIA_ERROR_UNSUPPORTED";
               break;
            case -1007:
               msg = msg + ">MEDIA_ERROR_MALFORMED";
               break;
            case -1004:
               msg = msg + ">MEDIA_ERROR_IO";
               break;
            case -110:
               msg = msg + ">MEDIA_ERROR_TIMED_OUT";
               break;
            default:
               msg = msg + ">MEDIA_ERROR_IO";
         }

         this.c.a(msg);
      }

   }
}
