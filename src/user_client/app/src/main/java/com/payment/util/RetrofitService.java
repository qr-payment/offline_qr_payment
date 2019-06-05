package com.payment.util;

import com.payment.model.Account;
import com.payment.model.Card;
import com.payment.model.ServerResponse;
import com.payment.model.User;
import com.payment.model.internal.SignInRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    @POST("auth/signup")
    Call<ServerResponse> signup(@Body User user);

    @POST("auth/signin")
    Call<ServerResponse<SignInRes>> signin(@Body User user);

    @POST("method/regist")
    Call<ServerResponse> registCard(@Body Card card);

    @POST("method/regist")
    Call<ServerResponse> registAccount(@Body Account account);

    @POST("method/list/{userIdx}")
    Call<ServerResponse> callpay(@Path("userIdx") Long userIdx);

    @GET(" ")
    Call<ServerResponse> qrScanUrl();
}
