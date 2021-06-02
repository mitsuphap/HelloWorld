package com.example.helloworld;

import android.os.Parcel;
import android.os.Parcelable;

public class CooridinateParcelable implements Parcelable {

    public int x, y, z;

    //Empty constructor
    public CooridinateParcelable() {

    }

    protected CooridinateParcelable(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
    }

    public static final Creator<CooridinateParcelable> CREATOR = new Creator<CooridinateParcelable>() {
        @Override
        public CooridinateParcelable createFromParcel(Parcel in) {
            return new CooridinateParcelable(in);
        }

        @Override
        public CooridinateParcelable[] newArray(int size) {
            return new CooridinateParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(z);
    }
}
