package com.uzmap.pkg.uzcore.external;

import android.app.TimePickerDialog;
import android.content.Context;

public class q extends TimePickerDialog {
    public q(Context context, OnTimeSetListener callBack, int hourOfDay, int minutOfHour) {
        super(context, callBack, hourOfDay, minutOfHour, true);
        this.setCancelable(true);
        this.setTitle("请选择时间");
    }
}
