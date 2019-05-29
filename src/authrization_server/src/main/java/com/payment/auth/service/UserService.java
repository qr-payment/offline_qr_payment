package com.payment.auth.service;

import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ResponseWrapper> signIn(SignIn signIn);

}
