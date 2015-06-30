package com.solstice.codechallenge.contactlist.converters.impl;

import com.solstice.codechallenge.contactlist.utils.Utils;
import com.solstice.codechallenge.contactlist.converters.Converter;
import com.solstice.codechallenge.contactlist.entities.Address;
import com.solstice.codechallenge.contactlist.entities.UserDetail;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by snakepit on 29/06/2015.
 */
public class UserDetailConverter implements Converter<UserDetail> {

    @Override
    public UserDetail deserialize(InputStream in) throws JSONException, IOException {
        UserDetail result = new UserDetail();
        JSONObject o = Utils.toJSONObject(in);
        result.setEmployeeId(o.getInt("employeeId"));
        result.setFavorite(o.getBoolean("favorite"));
        result.setLargeImageURL(o.getString("largeImageURL"));
        result.setEmail(o.getString("email"));
        result.setWebsite(o.getString("website"));

        JSONObject address = o.getJSONObject("address");
        Address a = new Address();
        a.setStreet(address.getString("street"));
        a.setCity(address.getString("city"));
        a.setState(address.getString("state"));
        a.setCountry(address.getString("country"));
        a.setZip(address.getString("zip"));
        a.setLatitude(address.getDouble("latitude"));
        a.setLongitude(address.getDouble("longitude"));
        result.setAddress(a);

        return result;
    }
}
