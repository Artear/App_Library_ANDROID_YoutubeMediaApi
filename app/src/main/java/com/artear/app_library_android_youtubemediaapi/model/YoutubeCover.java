package com.artear.app_library_android_youtubemediaapi.model;


import android.os.Parcel;
import android.os.Parcelable;

public class YoutubeCover implements Parcelable {

    public static final Creator<YoutubeCover> CREATOR = new Creator<YoutubeCover>() {
        @Override
        public YoutubeCover createFromParcel(Parcel in) {
            return new YoutubeCover(in.readString(), in.readString(), in.readString());
        }

        @Override
        public YoutubeCover[] newArray(int size) {
            return new YoutubeCover[size];
        }
    };
    private String id;
    private String title;
    private String description;

    public YoutubeCover(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
    }
}
