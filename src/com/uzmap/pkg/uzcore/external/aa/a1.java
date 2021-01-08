package com.uzmap.pkg.uzcore.external.aa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class a1 extends SQLiteOpenHelper {
    public a1(Context context, String dbname, int dbversion) {
        super(context, dbname, null, dbversion);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alarms (_id INTEGER PRIMARY KEY,hour INTEGER, minutes INTEGER, daysofweek INTEGER, alarmtime INTEGER, enabled INTEGER, vibrate INTEGER, message TEXT, alert TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarms");
        this.onCreate(db);
    }
}
