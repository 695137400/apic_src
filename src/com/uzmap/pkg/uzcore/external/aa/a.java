//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.external.aa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class a extends SQLiteOpenHelper {
    public a(Context context, String dbname, int dbversion) {
        super(context, dbname, (CursorFactory)null, dbversion);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alarms (_id INTEGER PRIMARY KEY,hour INTEGER, minutes INTEGER, daysofweek INTEGER, alarmtime INTEGER, enabled INTEGER, vibrate INTEGER, message TEXT, alert TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarms");
        this.onCreate(db);
    }
}
