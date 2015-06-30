package com.solstice.codechallenge.contactlist.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by snakepit on 29/06/2015.
 */
public class Utils {

    public static JSONObject toJSONObject(InputStream in) throws IOException, JSONException {
        return new JSONObject(streamToString(in));
    }

    public static JSONArray toJSONArray(InputStream in) throws IOException, JSONException {
        return new JSONArray(streamToString(in));
    }

    private static String streamToString(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
