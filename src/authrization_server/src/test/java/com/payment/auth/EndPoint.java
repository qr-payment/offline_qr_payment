package com.payment.auth;

public enum EndPoint {

    SignIn("/auth/signin"),
    SignUp("/auth/signup"),
    IdCheck("/auth/id/check"),

    RegistPayMethod("/method/regist");

    String endPoint;

    EndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

}
