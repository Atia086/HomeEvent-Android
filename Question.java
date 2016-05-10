package com.yolo.michael.yolo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by student5305 on 4/05/16.
 */
public class Question implements Parcelable {

    private String content;
    private ArrayList<Contact> contactArrayList;

    public Question() {
        this.contactArrayList = new ArrayList<>();
    }

    public Question(String content, ArrayList<Contact> contactArrayList) {
        this.content = content;
        this.contactArrayList = contactArrayList;
    }

    protected Question(Parcel in) {
        content = in.readString();
        contactArrayList = in.createTypedArrayList(Contact.CREATOR);
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Contact> getContactArrayList() {
        return contactArrayList;
    }

    public void setContactArrayList(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
    }

    public void addContact(Contact contact) {
        this.contactArrayList.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contactArrayList.remove(contact);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeTypedList(contactArrayList);
    }
}
