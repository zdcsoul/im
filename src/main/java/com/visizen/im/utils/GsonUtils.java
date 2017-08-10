package com.visizen.im.utils;

import com.google.gson.Gson;

public class GsonUtils<T> {

    private static final Gson gson = new Gson();

    public static<T> T toBean(String json,Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

    public static String toJson(Object object){
        return gson.toJson(object);
    }

}
