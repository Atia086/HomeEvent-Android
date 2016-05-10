package com.yolo.michael.yolo.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Michael on 08-04-16.
 */
public class Contact implements Parcelable{

    private String nom;
    private String prenom;
    private boolean added = false;
    private String imageUrl;

    public Contact() {
    }

    public Contact(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    protected Contact(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        added = in.readInt()==1;
        imageUrl = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isAdded() {
        return added;
    }

    public void setIsAdded(boolean isAdded) {
        this.added = isAdded;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeInt(added ? 1 : 0);
        dest.writeString(imageUrl);
    }
}
