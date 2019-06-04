package com.payment.pay.advice;

import com.payment.pay.exception.EmptyHeaderException;
import com.payment.pay.exception.InvalidDataException;
import com.payment.pay.model.response.wrapper.ResponseWrapper;
import com.payment.pay.model.response.wrapper.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> invalidDataExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INVALID_DATA), HttpStatus.OK);
    }

    @ExceptionHandler(EmptyHeaderException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> emptyHeaderExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.EMPTY_HEADER), HttpStatus.OK);
    }

}
