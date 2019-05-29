package com.payment.auth.controller;

import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.response.SignUpRes;
import com.payment.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpRes> signUp(SignIn signIn) {
         SignUpRes newUser = userService.signUp(signIn);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
