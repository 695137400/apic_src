package com.uzmap.pkg.uzcore;

import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.aa.AssetsFileUtil;
import org.json.JSONObject;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class n {
   public static final String a() {
      return com.uzmap.pkg.uzapp.b.j();
   }

   public static boolean b() {
      return com.uzmap.pkg.uzapp.b.n();
   }

   public static boolean a(com.uzmap.pkg.uzcore.uzmodule.e wgtInfo, int version, String zipPath) {
      boolean success = a(zipPath, c(), wgtInfo.Q);
      if (success) {
         success = a(version, wgtInfo.r);
      }

      return success;
   }

   private static boolean a(String archive, String targetPath, boolean reliable) {
      File archiveFile = new File(archive);
      if (!archiveFile.exists()) {
         return false;
      } else {
         ZipFile zipFile = null;

         try {
            zipFile = new ZipFile(archiveFile);
         } catch (Exception var17) {
            var17.printStackTrace();
         }

         if (zipFile == null) {
            return false;
         } else {
            Enumeration<? extends ZipEntry> entrys = zipFile.entries();
            if (!entrys.hasMoreElements()) {
               return false;
            } else {
               ZipEntry firstEntry = entrys.nextElement();
               String firstDirectory = null;
               String entryName;
               String finalfile;
               File p;
               String e;
               if (firstEntry.isDirectory()) {
                  firstDirectory = firstEntry.getName();
               } else {
                  String wt = "widget/";
                  entryName = firstEntry.getName();
                  if (entryName.startsWith(wt)) {
                     firstDirectory = wt;
                     entryName = entryName.replaceFirst(wt, "");
                  }

                  finalfile = targetPath + File.separator + entryName;
                  String fileDir = finalfile.substring(0, finalfile.lastIndexOf(File.separator));
                  p = new File(fileDir);
                  if (!p.exists()) {
                     p.mkdirs();
                  }

                  try {
                     e = targetPath + entryName;
                     e = AssetsFileUtil.getFileExtension(e);
                     boolean s = AssetsFileUtil.checkFileType(e);
                     if (s && reliable) {
                        a(zipFile, firstEntry, e);
                     } else {
                        b(zipFile, firstEntry, e);
                     }
                  } catch (Exception var19) {
                     var19.printStackTrace();
                     return false;
                  }
               }

               while (true) {
                  while (entrys.hasMoreElements()) {
                     ZipEntry entry = entrys.nextElement();
                     entryName = entry.getName();
                     if (firstDirectory != null) {
                        entryName = entryName.replaceFirst(firstDirectory, "");
                     }

                     finalfile = targetPath + entryName;
                     File dirs;
                     if (entry.isDirectory()) {
                        dirs = new File(finalfile);
                        if (!dirs.exists()) {
                           dirs.mkdirs();
                        }
                     } else {
                        dirs = new File(finalfile);
                        p = dirs.getParentFile();
                        if (!p.exists()) {
                           p.mkdirs();
                        }

                        try {
                           e = AssetsFileUtil.getFileExtension(entryName);
                           boolean s = AssetsFileUtil.checkFileType(e);
                           if ((s || b(entryName)) && reliable) {
                              a(zipFile, entry, finalfile);
                           } else {
                              b(zipFile, entry, finalfile);
                           }
                        } catch (Exception var18) {
                           var18.printStackTrace();
                           return false;
                        }
                     }
                  }

                  try {
                     zipFile.close();
                  } catch (IOException var16) {
                     var16.printStackTrace();
                  }

                  archiveFile.delete();
                  return true;
               }
            }
         }
      }
   }

   private static void a(ZipFile f, ZipEntry e, String t) throws Exception {
      FileOutputStream o = new FileOutputStream(t);
      BufferedOutputStream bo = new BufferedOutputStream(o);
      InputStream input = f.getInputStream(e);
      byte[] content = UZCoreUtil.readByte(input);
      if (content != null) {
         content = AssetsFileUtil.b(content);
         bo.write(content);
      }

      bo.flush();
      bo.close();
      input.close();
   }

   private static void b(ZipFile f, ZipEntry e, String t) throws Exception {
      FileOutputStream output = new FileOutputStream(t);
      BufferedOutputStream bos = new BufferedOutputStream(output);
      InputStream input = f.getInputStream(e);
      byte[] readBuffer = new byte[4096];

      for (int read = input.read(readBuffer); read != -1; read = input.read(readBuffer)) {
         bos.write(readBuffer, 0, read);
      }

      bos.flush();
      bos.close();
      input.close();
   }

   private static boolean a(int version, String widgetId) {
      boolean success = true;

      try {
         JSONObject json = a(widgetId);
         if (json.length() == 0) {
            success = false;
         } else {
            json.put("version", version);
            success = a(json, widgetId);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
         success = false;
      }

      return success;
   }

   private static JSONObject a(String widgetId) {
      JSONObject json = new JSONObject();
      String infoFile = c() + ".APIcloud";
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

   private static boolean a(JSONObject json, String widgetId) {
      boolean success = true;

      try {
         String infoFile = c() + ".APIcloud";
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
      } catch (Exception var7) {
         var7.printStackTrace();
         success = false;
      }

      return success;
   }

   private static String c() {
      return UZFileSystem.get().getAssetPath();
   }

   private static boolean b(String n) {
      return "config.xml".equals(n) || "key.xml".equals(n);
   }
}
