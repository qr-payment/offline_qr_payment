package com.payment.auth.advice;

import com.payment.auth.exception.AlreadyExistIdException;
import com.payment.auth.exception.InvalidDataException;
import com.payment.auth.exception.InvalidIdException;
import com.payment.auth.exception.InvalidPasswordException;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import com.payment.auth.model.response.wrapper.StatusCode;
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

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handleInvalidDataException(){
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INVALID_DATA),HttpStatus.OK);
    }

    @ExceptionHandler(AlreadyExistIdException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handleAlreadyExistUserException(){
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.ALREADY_EXIST_ID),HttpStatus.OK);
    }

    @ExceptionHandler(InvalidIdException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handleInvalidIdException(){
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INVALID_ID),HttpStatus.OK);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity handleInvalidPasswordException() {
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INVALID_PASSWORD), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity handleException(Exception ex) {
        log.error("Exception", ex);
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INTERNAL_SERVER_ERROR),HttpStatus.OK);
    }

}
