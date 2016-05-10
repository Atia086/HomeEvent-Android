package com.yolo.michael.yolo.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.yolo.michael.yolo.R;


/**
 * Created by Michael on 08-04-16.
 */

public enum TypeEvent implements Parcelable  {

    REPAS(0)
    ,SOIREE(1);

    private final int code;


    TypeEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public int getTextId(){
        int temp=0;
        switch (code){
            case 0:
                temp= R.string.meal;
                break;
            case 1:
                temp = R.string.hangout;
                break;
        }
        return temp;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TypeEvent> CREATOR = new Creator<TypeEvent>() {
        @Override
        public TypeEvent createFromParcel(Parcel in) {
            TypeEvent ev = TypeEvent.values()[in.readInt()];
            return  ev;
        }

        @Override
        public TypeEvent[] newArray(int size) {
            return new TypeEvent[size];
        }
    };
}
