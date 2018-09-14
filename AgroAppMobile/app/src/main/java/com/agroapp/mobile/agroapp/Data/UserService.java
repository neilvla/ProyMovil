package com.agroapp.mobile.agroapp.Data;

import com.agroapp.mobile.agroapp.Entities.Result;
import com.agroapp.mobile.agroapp.Entities.User;
import com.agroapp.mobile.agroapp.Entities.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("authentication/validateuser")
    Call<Result<User>> validate(@Body User user);

    //@GET("values/{id}")
    //Call<String> getValue(@Path("id") int id);

    @GET("authentication/getstatuscropcontrol/{userid}")
    Call<Result<Short>> get_StatusCropControl(@Path("userid") int userid);
}
