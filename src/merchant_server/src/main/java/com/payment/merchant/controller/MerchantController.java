package com.payment.merchant.controller;

import com.payment.merchant.Document.QrScanRes;
import com.payment.merchant.exception.InvalidDataException;
import com.payment.merchant.model.request.Payment;
import com.payment.merchant.model.response.QRScanRes;
import com.payment.merchant.model.wrapper.ResponseWrapper;
import com.payment.merchant.model.wrapper.StatusCode;
import com.payment.merchant.service.OrderService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/qrscan")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = QrScanRes.class)
    })
    public ResponseEntity<ResponseWrapper> qrScan(@RequestParam(value = "orderName")String encodedOrderName, @RequestParam(value = "amount")int amount, @RequestParam(value = "count")int count, @RequestHeader(value = "userIdx", required = false)Long userIdx) {

        QRScanRes qrScanRes = orderService.reserve(encodedOrderName, amount, count, userIdx);

        return new ResponseEntity<>(new ResponseWrapper(StatusCode.OK, qrScanRes), HttpStatus.OK);

    }

    @PostMapping("/payment")
    public ResponseEntity<ResponseWrapper> payment(@RequestBody @Valid Payment payment, @RequestParam(value = "redisKey")String redisKey, @RequestParam(value = "reserveId")Long reserveId, @RequestHeader(value = "userIdx", required = false)Long userIdx, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new InvalidDataException();
        }

        ResponseWrapper response = orderService.payment(payment, redisKey, reserveId, userIdx);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
