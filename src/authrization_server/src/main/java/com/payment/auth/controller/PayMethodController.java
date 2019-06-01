package com.payment.auth.controller;

import com.payment.auth.exception.InvalidDataException;
import com.payment.auth.model.request.RegistPayMethod;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import com.payment.auth.model.response.wrapper.StatusCode;
import com.payment.auth.service.PaymentMethodService;
import com.payment.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
public class PayMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/regist")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseWrapper> registPayMethod(@RequestBody RegistPayMethod registPayMethod, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            throw new InvalidDataException();

        paymentMethodService.registPayMethod(registPayMethod);

        return new ResponseEntity<>(new ResponseWrapper(StatusCode.OK), HttpStatus.CREATED);

    }

}
