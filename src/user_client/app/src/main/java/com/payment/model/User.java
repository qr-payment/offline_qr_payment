package com.payment.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String password;
    private String name;
    private String transactionPw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransactionPw() {
        return transactionPw;
    }

    public void setTransactionPw(String transactionPw) {
        this.transactionPw = transactionPw;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", transactionPw='" + transactionPw + '\'' +
                '}';
    }
}
