package com.solstice.codechallenge.contactlist.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by snakepit on 27/06/2015.
 */
public class User implements Parcelable {

    private String name;
    private Integer employeeId;
    private String company;
    private String detailsURL;
    private String smallImageURL;
    private Long birthdate;
    private Phone phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public void setDetailsURL(String detailsURL) {
        this.detailsURL = detailsURL;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public Long getBirthdate() {
        return birthdate;
    }

    public String getBirthdateAsString() {
        SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        return spd.format(new Date(this.birthdate));
    }

    public void setBirthdate(Long birthdate) {
        this.birthdate = birthdate;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(employeeId);
        dest.writeString(company);
        dest.writeString(detailsURL);
        dest.writeString(smallImageURL);
        dest.writeLong(birthdate);
        dest.writeParcelable(phone, flags);
    }
}
