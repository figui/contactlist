package com.solstice.codechallenge.contactlist.entities;

import java.io.Serializable;

/**
 * Created by snakepit on 27/06/2015.
 */
public class Phone implements Serializable {

    private String home;
    private String work;
    private String mobile;

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
}
