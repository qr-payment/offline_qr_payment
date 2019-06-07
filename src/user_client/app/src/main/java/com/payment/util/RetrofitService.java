package com.payment.util;

import com.payment.model.Account;
import com.payment.model.Card;
import com.payment.model.PaymentMethods;
import com.payment.model.ServerResponse;
import com.payment.model.TransactionRequest;
import com.payment.model.TransactionResponse;
import com.payment.model.User;
import com.payment.model.internal.SignInRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("method/methods/{userIdx}")
    Call<ServerResponse<PaymentMethods>> userExistCard(@Path("userIdx") Long userIdx);

    @GET(" ")
    Call<ServerResponse<TransactionResponse>> qrScanUrl(@Header("userIdx") Long userIdx);

    @POST("/")
    Call<ServerResponse> transactionComplete(@Body TransactionRequest transactionRequest);
}
