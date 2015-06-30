package com.solstice.codechallenge.contactlist.services;

import android.graphics.Bitmap;

import com.solstice.codechallenge.contactlist.converters.Converter;
import com.solstice.codechallenge.contactlist.entities.User;
import com.solstice.codechallenge.contactlist.entities.UserDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by snakepit on 27/06/2015.
 */
public abstract class ContactService {

    public abstract List<User> getContacts();

    public abstract Bitmap getImage(String url);

    public abstract UserDetail getDetails(String url);

    protected InputStream connect(String url, String method) throws IOException{
        InputStream result = null;
        URL u = new URL(url);
        HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
        huc.setRequestMethod(method);
        huc.connect();
        result = huc.getInputStream();
        return result;
    }


}
