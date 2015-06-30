package com.solstice.codechallenge.contactlist.services.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.solstice.codechallenge.contactlist.converters.Converter;
import com.solstice.codechallenge.contactlist.converters.impl.UserConverter;
import com.solstice.codechallenge.contactlist.converters.impl.UserDetailConverter;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.entities.UserDetail;
import com.solstice.codechallenge.contactlist.services.ContactService;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by snakepit on 27/06/2015.
 */
public class ContactServiceImpl extends ContactService {

    private final String TAG = ContactService.class.getSimpleName();

    @Override
    public List<User> getContacts() {
        List<User> result = null;
        try {
            InputStream in = connect("https://solstice.applauncher.com/external/contacts.json", "GET");
            result = new UserConverter().deserialize(in);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch( JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Bitmap getImage(String url) {
        Bitmap result = null;
        try {
            InputStream in = connect(url, "GET");
            result = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }

    @Override
    public UserDetail getDetails(String url) {
        UserDetail result = null;
        try {
            InputStream in = connect(url, "GET");
            result = new UserDetailConverter().deserialize(in);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }
}
