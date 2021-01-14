package com.uzmap.pkg.uzapp;

import android.text.TextUtils;
import android.util.Log;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import com.uzmap.pkg.uzkit.UZUtility;

import java.io.*;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiExceptionHandler implements UncaughtExceptionHandler {
    private static ApiExceptionHandler handler;
    private final UncaughtExceptionHandler exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private ApiExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static void initialize() {
        if (handler == null) {
            handler = new ApiExceptionHandler();
        }

    }

    public void uncaughtException(Thread thread, Throwable ex) {
        if (!this.isSuccessful(ex)) {
            this.exceptionHandler.uncaughtException(thread, ex);
        }

    }

    private boolean isSuccessful(Throwable ex) {
        this.saveLog(ex);
        return false;
    }

    public void saveLog(Throwable ex) {
        if (ex != null) {
            StringBuffer sb = new StringBuffer();
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);

            Throwable cause;
            for (cause = ex.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }

            printWriter.close();
            String result = writer.toString();
            sb.append(result);
            String content = sb.toString();
            cause = ex.getCause();
            if (cause == null) {
                cause = ex;
            }

            String title = cause.getClass().getSimpleName();
            StackTraceElement[] se = cause.getStackTrace();
            String causeName = se[0].getClassName();
            String methodName = se[0].getMethodName();
            int lineNumber = se[0].getLineNumber();
            title = title + "[" + causeName + ".java," + methodName + "," + lineNumber + "]";
            if (TextUtils.isEmpty(title)) {
                title = "RuntimeException";
            }

            content = content.replace("|", "~~~");
            com.uzmap.pkg.uzkit.a.aa.a.a().a(title, UZCoreUtil.transcoding(content));
            if (UZUtility.SDCardOnWork()) {
                String path = UZFileSystem.get().getLogPath() + "crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String time = this.dateFormat.format(new Date());
                String fileName = time + ".log";

                try {
                    FileOutputStream fos = new FileOutputStream(path + fileName);
                    fos.write(content.getBytes());
                    fos.flush();
                    fos.close();
                } catch (Exception var19) {
                    var19.printStackTrace();
                }
            }

            Log.e("app3c", "sorry, we have catch a crash log", ex);
        }
    }
}
