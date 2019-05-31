package com.payment.auth.controller;

import com.payment.auth.document.DocSignInRes;
import com.payment.auth.exception.InvalidDataException;
import com.payment.auth.model.request.IdCheck;
import com.payment.auth.model.request.SignIn;
import com.payment.auth.model.request.SignUp;
import com.payment.auth.model.response.SignInRes;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import com.payment.auth.model.response.wrapper.StatusCode;
import com.payment.auth.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseWrapper> signUp(@RequestBody @Valid SignUp signUp,  BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            throw new InvalidDataException();

        userService.signUp(signUp);

        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.OK), HttpStatus.CREATED);

    }

    @PostMapping("/id/check")
    public ResponseEntity<ResponseWrapper> idCheck(@RequestBody @Valid IdCheck idCheck, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            throw new InvalidDataException();

        userService.idCheck(idCheck);

        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.OK), HttpStatus.OK);

    }

    @PostMapping("/signin")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = DocSignInRes.class)
    })
    public ResponseEntity<ResponseWrapper> signIn(@RequestBody @Valid SignIn signIn, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            throw new InvalidDataException();

        SignInRes resBody = userService.signIn(signIn);

        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.OK, resBody), HttpStatus.OK);

    }


}
