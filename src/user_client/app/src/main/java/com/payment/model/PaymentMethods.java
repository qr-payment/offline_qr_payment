package com.payment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethods {

    @SerializedName("methods")
    @Expose
    List<Method> methods;

}
