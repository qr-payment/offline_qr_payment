package com.payment.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit QR_retrofit;
    private static Retrofit retrofit;
    private static Retrofit Tr_retrofit;
    private static final String BASE_URL = "http://223.194.156.223:8080/";

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getQRRetrofitInstance(String url) {
        if (QR_retrofit == null) {
            QR_retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return QR_retrofit;
    }

    public static Retrofit getTrRetrofitInstance(String transactionUrl){
        if (Tr_retrofit == null){
            Tr_retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(transactionUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return Tr_retrofit;
    }
}
