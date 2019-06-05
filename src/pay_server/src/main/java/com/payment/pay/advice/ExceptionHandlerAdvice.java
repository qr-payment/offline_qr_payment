package com.payment.pay.advice;

import com.payment.pay.exception.*;
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

    @ExceptionHandler(AlreadyStatusChangeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> alreadyStatusChangeExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.ALREADY_STATUS_CHANGE), HttpStatus.OK);
    }

    @ExceptionHandler(NotMatchReserveInfoException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> notMatchReserveInfoExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.NOT_MATCH_RESERVE_INFO), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidReserveException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> invalidReserveExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INVALID_RESERVE), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidPayMethodException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<ResponseWrapper> invalidPayMethodExceptionHandler() {
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INVALID_PAY_METHOD), HttpStatus.OK);
    }


}
