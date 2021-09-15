package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Parcelable {

    private String expressionString;
    private Float memoryValue = 0f;


    protected Calculator(Parcel in) {
        expressionString = in.readString();
        if (in.readByte() == 0) {
            memoryValue = null;
        } else {
            memoryValue = in.readFloat();
        }
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expressionString);
        if (memoryValue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(memoryValue);
        }
    }
}
