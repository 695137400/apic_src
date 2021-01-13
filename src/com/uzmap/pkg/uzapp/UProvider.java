package com.uzmap.pkg.uzapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Process;
import android.os.*;
import com.uzmap.pkg.uzcore.aa.AssetFile;
import com.uzmap.pkg.uzcore.aa.AssetsUtil;
import com.uzmap.pkg.uzcore.aa.AssetsFileUtil;

import java.io.FileNotFoundException;

public final class UProvider extends ContentProvider {
    private int a;
    private int b;
    private com.uzmap.pkg.uzcore.external.aa.e c;

    public boolean onCreate() {
        this.c = new com.uzmap.pkg.uzcore.external.aa.e();
        this.c.c(this.getContext());
        this.a = Process.myPid();
        this.b = Process.myUid();
        return true;
    }

    public Uri insert(Uri uri, ContentValues values) {
        if (this.b() && this.c.a(uri)) {
            return this.c.a(this.getContext(), uri, values);
        } else {
            throw new IllegalArgumentException("no permission to acceess");
        }
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
        if (this.b() && this.c.a(uri)) {
            return this.c.a(this.getContext(), uri, projection, selection, selectionArgs, sort);
        } else {
            throw new IllegalArgumentException("no permission to acceess");
        }
    }

    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        if (this.b() && this.c.a(uri)) {
            return this.c.a(this.getContext(), uri, values, where, whereArgs);
        } else {
            throw new IllegalArgumentException("no permission to acceess");
        }
    }

    public int delete(Uri uri, String where, String[] whereArgs) {
        if (this.b() && this.c.a(uri)) {
            return this.c.a(this.getContext(), uri, where, whereArgs);
        } else {
            throw new IllegalArgumentException("no permission to acceess");
        }
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        if (this.b() && this.c.a(uri)) {
            return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
        } else {
            throw new IllegalArgumentException("no permission to acceess");
        }
    }

    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        if (!this.a()) {
            return null;
        } else {
            String url = AssetsUtil.getFinalDir(uri);
            String fileType = AssetsFileUtil.getFileExtension(url);
            boolean s = AssetsFileUtil.checkFileType(fileType);
            if (!s) {
                return AssetFile.getAssetFile(url);
            } else {
                throw new FileNotFoundException();
            }
        }
    }

    public String getType(Uri uri) {
        return this.b() && this.c.a(uri) ? this.c.b(uri) : "";
    }

    private boolean a() {
        int cp = Binder.getCallingPid();
        return this.a == cp;
    }

    private boolean b() {
        int up = Binder.getCallingUid();
        return this.b == up;
    }
}
