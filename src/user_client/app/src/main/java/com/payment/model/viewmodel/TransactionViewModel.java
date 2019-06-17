package com.payment.model.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.payment.model.PaymentMethods;
import com.payment.model.ServerResponse;
import com.payment.model.TransactionRequest;
import com.payment.model.TransactionResponse;
import com.payment.model.User;
import com.payment.model.internal.SignInRes;
import com.payment.util.RetrofitInstance;
import com.payment.util.RetrofitService;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionViewModel extends ViewModel {
    private static final int PASSWORD_LENGTH = 4;
    private static final String TAG = "TransactionViewModel";
    public MutableLiveData<ArrayList<String>> transactionNumList = new MutableLiveData<>();
    public MutableLiveData<String> transactionPassword = new MutableLiveData<>();
    public MutableLiveData<Integer> transactionPasswordLength = new MutableLiveData<>();
    public MutableLiveData<Boolean> buttonState = new MutableLiveData<>();
    public MutableLiveData<User> user = new MutableLiveData<>();
    public MutableLiveData<ServerResponse> successCode_Login = new MutableLiveData<>();
    public MutableLiveData<Integer> successCode_SignUp = new MutableLiveData<>();
    public MutableLiveData<PaymentMethods> cardInfo = new MutableLiveData<>();
    public MutableLiveData<Boolean> serverChecker = new MutableLiveData<>();
    public MutableLiveData<TransactionResponse> transactionLiveData = new MutableLiveData<>();
    public MutableLiveData<String> scanUrlLiveData = new MutableLiveData<>();

    public MutableLiveData<TransactionRequest> transactionRequestMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> transactionUrl = new MutableLiveData<>();
    public MutableLiveData<Boolean> recycleFragment = new MutableLiveData<>();
    public MutableLiveData<String> transactionResultMessage = new MutableLiveData<>();
    public MutableLiveData<Boolean> transactionResult = new MutableLiveData<>();

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
        serverChecker.setValue(false);
        recycleFragment.setValue(false);
        transactionResult.setValue(false);
    }

    public void sendTransaction(){
        RetrofitService tr_retrofit = RetrofitInstance.getTrRetrofitInstance(transactionUrl.getValue()).create(RetrofitService.class);
        tr_retrofit.transactionComplete((Long) successCode_Login.getValue().getBody(), transactionRequestMutableLiveData.getValue()).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getCode() == 0){
                            //성공
                            transactionResult.setValue(true);
                        }else{
                            transactionResultMessage.setValue(response.body().getMessage());
                            transactionResult.setValue(false);
                        }
                        Log.e(TAG, "sendTransaction-> " + response.body().toString());
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("sendTransaction Failure-> ",""+t.toString());
            }
        });
    }

    public void scanRequest(){
        RetrofitService qr_retrofit = RetrofitInstance.getQRRetrofitInstance(scanUrlLiveData.getValue()).create(RetrofitService.class);
        qr_retrofit.qrScanUrl((Long) successCode_Login.getValue().getBody()).enqueue(new Callback<ServerResponse<TransactionResponse>>() {
            @Override
            public void onResponse(Call<ServerResponse<TransactionResponse>> call, Response<ServerResponse<TransactionResponse>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        transactionLiveData.setValue(response.body().getBody());
                        userExistCard();
                        Log.e(TAG,""+transactionLiveData.getValue().toString());
                        transactionUrl.setValue(transactionLiveData.getValue().getUrl());
                        Log.e(TAG,""+transactionLiveData.getValue().getUrl()+" , "+transactionUrl.getValue());
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse<TransactionResponse>> call, Throwable t) {
                Log.e("Scan Request Failure",""+t.toString());
            }
        });
    }

    public void userExistCard(){
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        retrofit.userExistCard((Long) successCode_Login.getValue().getBody()).enqueue(new Callback<ServerResponse<PaymentMethods>>() {
            @Override
            public void onResponse(Call<ServerResponse<PaymentMethods>> call, Response<ServerResponse<PaymentMethods>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        cardInfo.setValue(new PaymentMethods());
                        cardInfo.getValue().setMethods(response.body().getBody().getMethods());
                        Log.e(TAG,""+response.body().getBody().getMethods());
                        serverChecker.setValue(true);
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse<PaymentMethods>> call, Throwable t) {
                Log.e("Response Card Fail",""+t.toString());
            }
        });
    }

    public void callSignUpServer(){
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        retrofit.signup(user.getValue()).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null && response.body().getCode() == 0){
                        successCode_SignUp.setValue(response.body().getCode());
                        serverChecker.setValue(true);
                        Log.e(TAG,"SignUp Success-> "+response.body().toString());
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("SignUp failure-> ",""+t.toString());
            }
        });
    }

    public void callSignInServer(User loginUser){
        ServerResponse serverResponse = new ServerResponse();
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        retrofit.signin(loginUser).enqueue(new Callback<ServerResponse<SignInRes>>() {
            @Override
            public void onResponse(Call<ServerResponse<SignInRes>> call, Response<ServerResponse<SignInRes>> response) {
                if (response.isSuccessful()){
                    if (response.body().getCode() == 0){
                        serverResponse.setMessage(response.body().getMessage());
                        serverResponse.setCode(response.body().getCode());
                        serverResponse.setBody(response.body().getBody().getUserIdx());
                        successCode_Login.setValue(serverResponse);
                    }else{
                        serverResponse.setBody(null);
                        serverResponse.setCode(response.body().getCode());
                        serverResponse.setMessage(response.body().getMessage());
                        successCode_Login.setValue(serverResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse<SignInRes>> call, Throwable t) {
                Log.e("SignIn onFailure",""+t.toString());
            }
        });
    }

    public void initTransaction(){
        transactionPassword.setValue("");
        transactionPasswordLength.setValue(0);
        user.setValue(new User());
        transactionResultMessage.setValue("");
        transactionResult.setValue(false);
    }

    public void failTransactionInit(){
        transactionPassword.setValue("");
        transactionPasswordLength.setValue(0);
    }

    public void setTransactionRequest(TransactionRequest transactionRequest){
        transactionRequestMutableLiveData.setValue(transactionRequest);
        Log.e(TAG,"Transaction Request viewmodel -> "+transactionRequest.getProductName() + " , "+transactionRequest.getMethodType()+ " , "+transactionRequest.getMethodNum());
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
        }
    }

    public void deletePassword(){
        if (transactionPasswordLength.getValue() > 0 && !buttonState.getValue()){
            transactionPassword.setValue(transactionPassword.getValue().substring(0,transactionPassword.getValue().length() - 1));
            transactionPasswordLength.setValue(transactionPassword.getValue().length());
        }
    }

    public void shuffleList(){
        Collections.shuffle(list);
        transactionNumList.setValue(list);
    }
}
