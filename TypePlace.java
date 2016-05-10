package com.yolo.michael.yolo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.yolo.michael.yolo.R;

/**
 * Created by student5305 on 27/04/16.
 */
public enum TypePlace implements Parcelable {

    HOUSE(0),
    RESTAURANT(1),
    FRIENDSPLACE(2),
    DISCO(3);

    private final int code;

    TypePlace(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public int getTextId(){
        int temp=0;
        switch (code){
            case 0:
                temp= R.string.house;
                break;
            case 1:
                temp = R.string.restaurant;
                break;
            case 2:
                temp = R.string.friends_place;
                break;
            case 3:
                temp = R.string.disco;
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

    public static final Creator<TypePlace> CREATOR = new Creator<TypePlace>() {
        @Override
        public TypePlace createFromParcel(Parcel in) {
            TypePlace ev = TypePlace.values()[in.readInt()];
            return  ev;
        }

        @Override
        public TypePlace[] newArray(int size) {
            return new TypePlace[size];
        }
    };

}
