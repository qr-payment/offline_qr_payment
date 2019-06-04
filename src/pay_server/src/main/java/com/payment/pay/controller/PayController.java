package com.payment.pay.controller;

import com.payment.pay.document.DocReserveRes;
import com.payment.pay.exception.InvalidDataException;
import com.payment.pay.model.request.Reserve;
import com.payment.pay.model.response.ReserveRes;
import com.payment.pay.model.response.wrapper.ResponseWrapper;
import com.payment.pay.model.response.wrapper.StatusCode;
import com.payment.pay.service.PayService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = DocReserveRes.class)
    })
    public ResponseEntity<ResponseWrapper> reserve(@RequestBody Reserve reserve, @RequestHeader(value = "merchant_id") Long merchantId,  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidDataException();
        }

        ReserveRes reserveRes = payService.reserve(reserve, merchantId);

        return new ResponseEntity<>(new ResponseWrapper(StatusCode.OK, reserveRes), HttpStatus.OK);

    }

}
