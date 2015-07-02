package com.solstice.codechallenge.contactlist.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by snakepit on 27/06/2015.
 */
public class UserDetail implements Parcelable {

    private Integer employeeId;
    private Boolean favorite;
    private String largeImageURL;
    private String email;
    private String website;
    private Address address;

    // Default constructor
    public UserDetail() {}

    public UserDetail(Parcel in) {
        this.writeToParcel(in, 0);
    }

    public static final Parcelable.Creator<UserDetail> CREATOR
            = new Parcelable.Creator<UserDetail>() {
        public UserDetail createFromParcel(Parcel in) {
            return new UserDetail(in);
        }

        public UserDetail[] newArray(int size) {
            return new UserDetail[size];
        }
    };

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(employeeId);
        dest.writeBooleanArray(new boolean[]{favorite});
        dest.writeString(largeImageURL);
        dest.writeString(email);
        dest.writeString(website);
        dest.writeParcelable(address, flags);
    }
}