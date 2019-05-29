package com.payment.auth.controller;

import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import com.payment.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity signUp(SignIn signIn) {

        return userService.signIn(signIn);

    }

}
