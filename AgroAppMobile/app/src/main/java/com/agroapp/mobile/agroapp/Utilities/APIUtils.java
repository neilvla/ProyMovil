package com.agroapp.mobile.agroapp.Utilities;

import com.agroapp.mobile.agroapp.Data.UserService;

public class APIUtils {

    private APIUtils(){
    };

    public static UserService getUserService(){
        return RetrofitClient.getClient(UrlConnection.URL_BASE).create(UserService.class);
    }

}
