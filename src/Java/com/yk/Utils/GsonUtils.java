package com.yk.Utils;

import com.google.gson.Gson;

public class GsonUtils {
    private static Gson gson;

    private GsonUtils(){}

    public static Gson getGson() {
        if (gson==null){
            synchronized (GsonUtils.class){
                if (gson==null){
                    gson = new Gson();
                }
            }
        }
        return gson;
    }
}