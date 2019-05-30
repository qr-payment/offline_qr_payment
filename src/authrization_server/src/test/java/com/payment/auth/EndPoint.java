package com.payment.auth;

public enum EndPoint {

    SignIn("/signin"),
    SignUp("/signup"),
    IdCheck("/id/check");

    String endPoint;
    final String base = "/auth";

    EndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return base + endPoint;
    }

}
