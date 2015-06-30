package com.solstice.codechallenge.contactlist.converters;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by snakepit on 29/06/2015.
 */
public interface Converter<T> {

    public T deserialize(InputStream in) throws JSONException, IOException;
}
