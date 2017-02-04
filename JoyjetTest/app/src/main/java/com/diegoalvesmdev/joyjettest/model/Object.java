package com.diegoalvesmdev.joyjettest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by diegoalves on 03/02/2017.
 */

public class Object implements Parcelable {

    private int id;
    private String name;
    private String description;
    private String photo;
    private String category;
    private int favorite;

    public Object(int id, String name, String description, String photo, String category, int favorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.category = category;
        this.favorite = favorite;
    }

    public Object(String name, String description, String photo, String category, int favorite) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.category = category;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    protected Object(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        photo = in.readString();
        category = in.readString();
        favorite = in.readInt();
    }

    public static final Creator<Object> CREATOR = new Creator<Object>() {
        @Override
        public Object createFromParcel(Parcel in) {
            return new Object(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(photo);
        dest.writeString(category);
        dest.writeInt(favorite);
    }
}
