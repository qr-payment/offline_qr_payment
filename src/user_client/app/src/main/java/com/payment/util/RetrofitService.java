package com.payment.util;

import com.payment.model.ServerResponse;
import com.payment.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("auth/signup")
    Call<ServerResponse> signup(@Body User user);

    @POST("auth/signin")
    Call<ServerResponse> signin(@Body User user);

}
