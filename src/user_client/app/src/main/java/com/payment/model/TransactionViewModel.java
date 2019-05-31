package com.payment.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.payment.util.RetrofitInstance;
import com.payment.util.RetrofitService;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionViewModel extends ViewModel {
    private static final int PASSWORD_LENGTH = 4;
    public MutableLiveData<ArrayList<String>> transactionNumList = new MutableLiveData<>();
    public MutableLiveData<String> transactionPassword = new MutableLiveData<>();
    public MutableLiveData<Integer> transactionPasswordLength = new MutableLiveData<>();
    public MutableLiveData<Boolean> buttonState = new MutableLiveData<>();
    public MutableLiveData<User> user = new MutableLiveData<>();
    public MutableLiveData<String> successCode = new MutableLiveData<>();

    private ArrayList<String> list = new ArrayList<>();


    public TransactionViewModel() {
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        Collections.shuffle(list);
        transactionNumList.setValue(list);
        transactionPassword.setValue("");
        transactionPasswordLength.setValue(0);
        buttonState.setValue(false);
        user.setValue(new User());
        successCode.setValue("9999");
    }

    public void callSignUpServer(){
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        retrofit.signup(user.getValue()).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.e("signup","success");
                        successCode.setValue(response.body().getCode());
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("failure-> ",""+t.toString());
            }
        });
    }

    public void callSignInServer(User loginUser){
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        retrofit.signin(loginUser).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        successCode.setValue(response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("signup failure",""+t.toString());
            }
        });
    }

    public void initViewModels(){
        transactionPassword.setValue("");
        transactionPasswordLength.setValue(0);
        buttonState.setValue(false);
        shuffleList();
    }

    public void setPassword(String clickedNumber){
        if (transactionPasswordLength.getValue() < PASSWORD_LENGTH && buttonState.getValue()){
            transactionPassword.setValue(transactionPassword.getValue().concat(clickedNumber));
            transactionPasswordLength.setValue(transactionPassword.getValue().length());
            Log.e("at ViewModel",""+transactionPassword.getValue());
            Log.e("pass length",""+transactionPasswordLength.getValue());
        }
    }

    public void deletePassword(){
        if (transactionPasswordLength.getValue() > 0 && !buttonState.getValue()){
            transactionPassword.setValue(transactionPassword.getValue().substring(0,transactionPassword.getValue().length() - 1));
            transactionPasswordLength.setValue(transactionPassword.getValue().length());
            Log.e("delete at ViewModel",""+transactionPassword.getValue());
            Log.e("delete pass length",""+transactionPasswordLength.getValue());
        }
    }

    public void shuffleList(){
        Collections.shuffle(list);
        transactionNumList.setValue(list);
    }
}
