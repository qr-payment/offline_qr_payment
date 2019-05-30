package com.payment.auth.controller;

import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import com.payment.auth.model.response.SignInRes;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import com.payment.auth.model.response.wrapper.StatusCode;
import com.payment.auth.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseWrapper> signUp(@RequestBody SignUp signUp) {
        userService.signUp(signUp);
        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.OK), HttpStatus.CREATED);
    }

    @PostMapping("/id/check")
    public ResponseEntity<ResponseWrapper> idCheck(@RequestBody IdCheck idCheck) {
        userService.idCheck(idCheck);
        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.OK), HttpStatus.OK);
    }

    @PostMapping("/signin")
    @ApiOperation(
            value = "Get all widgets.",
            response = SignInRes.class
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseWrapper> signin(@RequestBody SignIn signIn) {
        SignInRes resBody = userService.signIn(signIn);
        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.OK, resBody), HttpStatus.OK);
    }


}
