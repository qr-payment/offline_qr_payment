package com.payment.merchant.advice;

import com.payment.merchant.exception.InvalidDataException;
import com.payment.merchant.exception.InvalidHeaderException;
import com.payment.merchant.exception.NotMatchOrderException;
import com.payment.merchant.exception.PayServerException;
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

    @ExceptionHandler(InvalidHeaderException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity invalidHeaderExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.INVALID_HEADER), HttpStatus.OK);
    }

    @ExceptionHandler(NotMatchOrderException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity notMatchOrderExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.NOT_MATCH_ORDER), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity invalidDataExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper<>(StatusCode.INVALID_DATA), HttpStatus.OK);
    }

    @ExceptionHandler(PayServerException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity payServerExceptionHandler(PayServerException e) {
        return new ResponseEntity<>(new ResponseWrapper<>(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

}
