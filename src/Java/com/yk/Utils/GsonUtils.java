package com.yk.Utils;

import com.google.gson.Gson;
import com.yk.response.ErrorCommonResponse;
import com.yk.response.SuccessCommonResponse;

public class GsonUtils {
    private static Gson gson;

    private GsonUtils(){}

    private static Gson getGson() {
        if (gson==null){
            synchronized (GsonUtils.class){
                if (gson==null){
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static String toJson(Object object){
        return getGson().toJson(object);
    }

    public static String responseSuccessJson(){
        return getGson().toJson(new SuccessCommonResponse());
    }


    public static String responseSuccessJson(Object object){
        return getGson().toJson(new SuccessCommonResponse(object));
    }

    public static String responseSuccessJson(String msg){
        return getGson().toJson(new SuccessCommonResponse(msg));
    }

    public static String responseErrorJson(){
        return getGson().toJson(new ErrorCommonResponse());
    }

    public static String responseErrorJson(Object object){
        return getGson().toJson(new ErrorCommonResponse(object));
    }

    public static String responseErrorJson(String msg){
        return getGson().toJson(new ErrorCommonResponse(msg));
    }
}
