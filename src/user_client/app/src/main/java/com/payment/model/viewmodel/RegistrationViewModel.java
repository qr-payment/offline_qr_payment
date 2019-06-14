package com.payment.model.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.payment.model.Account;
import com.payment.model.Card;
import com.payment.model.ServerResponse;
import com.payment.util.RetrofitInstance;
import com.payment.util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationViewModel extends ViewModel {

    public MutableLiveData<Card> cardLiveData = new MutableLiveData<>();
    public MutableLiveData<Account> accountLiveData = new MutableLiveData<>();

    public RegistrationViewModel() {
    }

    public void registCard(){
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        retrofit.registCard(cardLiveData.getValue()).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.e("Registration Card success",""+response.body());
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Registration Card fail-> ",""+t.toString());
            }
        });
    }

    public void registAccount(){
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        retrofit.registAccount(accountLiveData.getValue()).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.e("Registration Account success",""+response.body());
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("Registration Account fail",""+t.toString());
            }
        });
    }
}

