package com.payment.merchant;

public enum EndPoint {

    Reserve("/merchant/qrscan");

    String endPoint;

    EndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

}
