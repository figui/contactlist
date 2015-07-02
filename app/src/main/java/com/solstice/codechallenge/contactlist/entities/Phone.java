package com.solstice.codechallenge.contactlist.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by snakepit on 27/06/2015.
 */
public class Phone implements Parcelable {

    private String home;
    private String work;
    private String mobile;

    // Default constructor
    public Phone() {}

    public Phone(Parcel in) {
        this.writeToParcel(in, 0);
    }

    public static final Parcelable.Creator<Phone> CREATOR
            = new Parcelable.Creator<Phone>() {
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(work);
        dest.writeString(home);
        dest.writeString(mobile);

    }
}
