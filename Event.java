package com.yolo.michael.yolo.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by Michael on 08-04-16.
 */
public class Event implements Parcelable{



    private String name;
    private String description;
    private TypeEvent typeEvent;
    private ArrayList<Date> dates ;
    private ArrayList<Contact> guests ;
    private ArrayList<ShoppingArticle> articles ;
    private Place place;

    public Event(){}

    public Event(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
        this.dates = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.articles = new ArrayList<>();
    }

    protected Event(Parcel in) {
        name = in.readString();
        description = in.readString();
        typeEvent = in.readParcelable(TypeEvent.class.getClassLoader());
        guests = in.createTypedArrayList(Contact.CREATOR);
        articles = in.createTypedArrayList(ShoppingArticle.CREATOR);
        place = in.readParcelable(Place.class.getClassLoader());
        long[] dateTemp = in.createLongArray();

        for (Long time : dateTemp){
            dates.add(new Date(time));

        }

    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public ArrayList<ShoppingArticle> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ShoppingArticle> articles) {
        this.articles = articles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    public void addDate(Date date){
        this.dates.add(date);
    }

    public ArrayList<Contact> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Contact> guests) {
        this.guests = guests;
    }

    public void addGuest(Contact contact) {
        this.guests.add(contact);
    }

    public void removeGuest(Contact contact) {
        this.guests.remove(contact);
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }




    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeParcelable(typeEvent, flags);
        dest.writeTypedList(guests);
        dest.writeTypedList(articles);
        dest.writeParcelable(place, flags);
        ArrayList<Long> dateTemp = new ArrayList<Long>();
        for (Date date : dates) {
            dateTemp.add(date.getTime());
        }
            dest.writeList(dateTemp);
    }

}
