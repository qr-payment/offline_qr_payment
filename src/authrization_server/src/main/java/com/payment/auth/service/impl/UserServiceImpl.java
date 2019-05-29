package com.payment.auth.service.impl;

import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import com.payment.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<ResponseWrapper> signIn(SignIn signIn) {
        return null;
    }

}
