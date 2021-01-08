package com.uzmap.pkg.uzcore.external;

import android.app.DatePickerDialog;
import android.content.Context;

public class n extends DatePickerDialog {
    public n(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
        this.setCancelable(true);
        this.setTitle("请选择日期");
    }
}
