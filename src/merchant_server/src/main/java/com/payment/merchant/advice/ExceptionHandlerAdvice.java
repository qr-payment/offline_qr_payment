package com.payment.merchant.advice;

import com.payment.merchant.model.wrapper.ResponseWrapper;
import com.payment.merchant.model.wrapper.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Transactional
public class ExceptionHandlerAdvice {



    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity handleException(Exception ex) {
        log.error("Exception", ex);
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INTERNAL_SERVER_ERROR),HttpStatus.OK);
    }

}
