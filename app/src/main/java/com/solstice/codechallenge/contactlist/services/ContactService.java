package com.solstice.codechallenge.contactlist.services;

import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.entities.UserDetail;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by snakepit on 30/06/2015.
 */
public interface ContactService {

    @GET("/external/contacts.json")
    public List<User> getContacts();

    @GET("/external/Contacts/id/{id}.json")
    public UserDetail getDetails(@Path("id") int id);
}
