package com.agroapp.mobile.agroapp.Utilities;

import com.agroapp.mobile.agroapp.Data.UserService;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://neilvla-001-site1.itempurl.com/AgroAppWS/api/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

}
