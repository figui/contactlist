package com.solstice.codechallenge.contactlist.converters.impl;

import android.util.Log;

import com.solstice.codechallenge.contactlist.utils.Utils;
import com.solstice.codechallenge.contactlist.converters.Converter;
import com.solstice.codechallenge.contactlist.entities.Phone;
import com.solstice.codechallenge.contactlist.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by snakepit on 29/06/2015.
 */
public class UserConverter implements Converter<List<User>> {

    private final String TAG = UserConverter.class.getSimpleName();

    @Override
    public List<User> deserialize(InputStream in) throws JSONException, IOException {
        List<User> result = new ArrayList<User>();
        try {
            JSONArray results = Utils.toJSONArray(in);
            for (int i = 0; i < results.length(); i++) {
                User user = new User();
                JSONObject response = (JSONObject) results.get(i);
                user.setName(response.getString("name"));
                user.setEmployeeId(response.getInt("employeeId"));
                user.setCompany(response.getString("company"));
                user.setDetailsURL(response.getString("detailsURL"));
                user.setSmallImageURL(response.getString("smallImageURL"));
                user.setBirthdate(new Date(response.getLong("birthdate")));

                JSONObject phoneResponse = response.getJSONObject("phone");
                Phone phone = new Phone();
                phone.setWork(phoneResponse.getString("work"));
                phone.setHome(phoneResponse.getString("home"));
                if(phoneResponse.has("mobile")) {   phone.setMobile(phoneResponse.getString("mobile")); }


                user.setPhone(phone);
                result.add(user);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }
}
