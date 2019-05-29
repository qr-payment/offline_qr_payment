package com.payment.model;

public class User {
    private String id;
    private String password;
    private String name;
    private int transaction_pw;

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

    public int getTransaction_pw() {
        return transaction_pw;
    }

    public void setTransaction_pw(int transaction_pw) {
        this.transaction_pw = transaction_pw;
    }
}
