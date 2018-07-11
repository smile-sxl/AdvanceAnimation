package com.sxl.animation;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: smile .
 * date: On 2018/7/1
 */
public class Hero implements Parcelable {

    private int resId;
    private String title;
    private String about;

    public Hero(int resId, String title, String about) {
        this.resId = resId;
        this.title = title;
        this.about = about;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.resId);
        dest.writeString(this.title);
        dest.writeString(this.about);
    }

    protected Hero(Parcel in) {
        this.resId = in.readInt();
        this.title = in.readString();
        this.about = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
