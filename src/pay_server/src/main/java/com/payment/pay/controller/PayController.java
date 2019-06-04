package com.payment.pay.controller;

import com.payment.pay.model.response.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseWrapper> reserve(@RequestBody )

}
