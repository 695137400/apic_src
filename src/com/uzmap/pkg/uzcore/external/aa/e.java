package com.uzmap.pkg.uzcore.external.aa;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class e {
    private static final UriMatcher b = new UriMatcher(-1);
    public static Uri a = null;
    private SQLiteOpenHelper c;

    public static String a(Context context) {
        return context.getPackageName() + ".ups";
    }

    public static Uri b(Context context) {
        if (a != null) {
            return a;
        } else {
            a = Uri.parse("content://" + a(context) + "/alarm");
            return a;
        }
    }

    public boolean c(Context context) {
        String authorities = a(context);
        b.addURI(authorities, "alarm", 1);
        b.addURI(authorities, "alarm/#", 2);
        b(context);
        return true;
    }

    public boolean a(Uri url) {
        return -1 != b.match(url);
    }

    public Cursor a(Context context, Uri url, String[] projectionIn, String selection, String[] selectionArgs, String sort) {
        this.d(context);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        int match = b.match(url);
        switch (match) {
            case 1:
                qb.setTables("alarms");
                break;
            case 2:
                qb.setTables("alarms");
                qb.appendWhere("_id=");
                qb.appendWhere(url.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URL " + url);
        }

        SQLiteDatabase db = this.c.getReadableDatabase();
        Cursor ret = qb.query(db, projectionIn, selection, selectionArgs, null, null, sort);
        if (ret != null) {
            ret.setNotificationUri(context.getContentResolver(), url);
        }

        return ret;
    }

    public String b(Uri url) {
        int match = b.match(url);
        switch (match) {
            case 1:
                return "vnd.android.cursor.dir/alarms";
            case 2:
                return "vnd.android.cursor.item/alarms";
            default:
                throw new IllegalArgumentException("Unknown URL");
        }
    }

    public int a(Context context, Uri url, ContentValues values, String where, String[] whereArgs) {
        this.d(context);
        long rowId = 0L;
        int match = b.match(url);
        SQLiteDatabase db = this.c.getWritableDatabase();
        switch (match) {
            case 2:
                String segment = url.getPathSegments().get(1);
                rowId = Long.parseLong(segment);
                int count = db.update("alarms", values, "_id=" + rowId, null);
                context.getContentResolver().notifyChange(url, null);
                return count;
            default:
                throw new UnsupportedOperationException("Cannot update URL: " + url);
        }
    }

    public Uri a(Context context, Uri url, ContentValues initialValues) {
        if (b.match(url) != 1) {
            throw new IllegalArgumentException("Cannot insert into URL: " + url);
        } else {
            this.d(context);
            ContentValues values = new ContentValues(initialValues);
            SQLiteDatabase db = this.c.getWritableDatabase();
            long rowId = db.insert("alarms", "message", values);
            if (rowId < 0L) {
                throw new SQLException("Failed to insert row into " + url);
            } else {
                Uri newUrl = ContentUris.withAppendedId(b(context), rowId);
                context.getContentResolver().notifyChange(newUrl, null);
                return newUrl;
            }
        }
    }

    public int a(Context context, Uri url, String where, String[] whereArgs) {
        this.d(context);
        SQLiteDatabase db = this.c.getWritableDatabase();
        long rowId = 0L;
        int count;
        switch (b.match(url)) {
            case 1:
                count = db.delete("alarms", where, whereArgs);
                break;
            case 2:
                String segment = url.getPathSegments().get(1);
                rowId = Long.parseLong(segment);
                if (TextUtils.isEmpty(where)) {
                    where = "_id=" + segment;
                } else {
                    where = "_id=" + segment + " AND (" + where + ")";
                }

                count = db.delete("alarms", where, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Cannot delete from URL: " + url);
        }

        Log.d("alarm", "rowId: " + rowId);
        context.getContentResolver().notifyChange(url, null);
        return count;
    }

    private void d(Context context) {
        if (this.c == null) {
            this.c = new a1(context, "notification.db", 1);
        }

    }
}
