package com.navent.api.orders.controller;

import com.google.gson.GsonBuilder;

public class JSONUtils {

    private JSONUtils() {
    }

    public static String toJson(Object object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return new GsonBuilder().create().fromJson(json, classOfT);
    }
}
