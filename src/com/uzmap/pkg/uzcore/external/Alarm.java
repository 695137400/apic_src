//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.uzcore.external;

import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Calendar;

public final class Alarm implements Parcelable {
    public int a;
    public boolean b;
    public int c;
    public int d;
    public Alarm.a e;
    public long f;
    public boolean g;
    public String h;
    public Uri i;
    public boolean j;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel p, int flags) {
        p.writeInt(this.a);
        p.writeInt(this.b ? 1 : 0);
        p.writeInt(this.c);
        p.writeInt(this.d);
        p.writeInt(this.e.a());
        p.writeLong(this.f);
        p.writeInt(this.g ? 1 : 0);
        p.writeString(this.h);
        p.writeParcelable(this.i, flags);
        p.writeInt(this.j ? 1 : 0);
    }

    public Alarm(Cursor c) {
        this.a = c.getInt(0);
        this.b = c.getInt(5) == 1;
        this.c = c.getInt(1);
        this.d = c.getInt(2);
        this.e = new Alarm.a(c.getInt(3));
        this.f = c.getLong(4);
        this.g = c.getInt(6) == 1;
        this.h = c.getString(7);
        String alertString = c.getString(8);
        if ("silent".equals(alertString)) {
            this.j = true;
        } else {
            if (alertString != null && alertString.length() != 0) {
                this.i = Uri.parse(alertString);
            }

            if (this.i == null) {
                this.i = RingtoneManager.getDefaultUri(4);
            }
        }

    }

    public Alarm(Parcel p) {
        this.a = p.readInt();
        this.b = p.readInt() == 1;
        this.c = p.readInt();
        this.d = p.readInt();
        this.e = new Alarm.a(p.readInt());
        this.f = p.readLong();
        this.g = p.readInt() == 1;
        this.h = p.readString();
        this.i = (Uri)p.readParcelable((ClassLoader)null);
        this.j = p.readInt() == 1;
    }

    public Alarm() {
        this.a = -1;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        this.c = c.get(11);
        this.d = c.get(12);
        this.g = true;
        this.e = new Alarm.a(0);
        this.i = RingtoneManager.getDefaultUri(4);
    }

    public static final class a {
        private static int[] a = new int[]{2, 3, 4, 5, 6, 7, 1};
        private int b;

        public a(int days) {
            this.b = days;
        }

        private boolean a(int day) {
            return (this.b & 1 << day) > 0;
        }

        public void a(int day, boolean set) {
            if (set) {
                this.b |= 1 << day;
            } else {
                this.b &= ~(1 << day);
            }

        }

        public int a() {
            return this.b;
        }

        public boolean b() {
            return this.b != 0;
        }

        public int a(Calendar c) {
            if (this.b == 0) {
                return -1;
            } else {
                int today = (c.get(7) + 5) % 7;

                int dayCount;
                for(dayCount = 0; dayCount < 7; ++dayCount) {
                    int day = (today + dayCount) % 7;
                    if (this.a(day)) {
                        break;
                    }
                }

                return dayCount;
            }
        }
    }
}
