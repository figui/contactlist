package com.solstice.codechallenge.contactlist.helpers;

import com.squareup.otto.Bus;

/**
 * Created by Snakepit on 23/10/2015.
 */
public class EventHelper {
    private static Bus instance = new Bus();

    public static void post(Object o) {
        instance.post(o);
    }

    public static void register(Object o) {
        instance.register(o);
    }

    public static void unregister(Object o) {
        instance.unregister(o);
    }
}
