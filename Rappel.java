package com.yolo.michael.yolo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.GregorianCalendar;

/**
 * Created by student5305 on 29/04/16.
 */
public class Rappel implements Parcelable {

    private GregorianCalendar date;
    private String content;

    public Rappel() {
    }

    private Rappel(GregorianCalendar date, String content) {
        this.date = date;
        this.content = content;
    }

    protected Rappel(Parcel in) {
        content = in.readString();
        long temp = in.readLong();
        date.setTimeInMillis(temp);
    }

    public static final Creator<Rappel> CREATOR = new Creator<Rappel>() {
        @Override
        public Rappel createFromParcel(Parcel in) {
            return new Rappel(in);
        }

        @Override
        public Rappel[] newArray(int size) {
            return new Rappel[size];
        }
    };

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeLong(date.getTimeInMillis());
    }
}
