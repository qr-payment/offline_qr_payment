package com.payment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Method {
    @SerializedName("nickName")
    @Expose
    private String nickName;
    @SerializedName("paymentMethodNum")
    @Expose
    private String paymentMethodNum;
    @SerializedName("paymentMethodType")
    @Expose
    private String paymentMethodType;
    @SerializedName("registAt")
    @Expose
    private int registAt;
    @SerializedName("bankName")
    @Expose
    private String bankName;
}
