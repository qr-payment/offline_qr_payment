package com.payment.auth.aop;

import com.payment.auth.exception.AlreadyExistIdException;
import com.payment.auth.exception.InvalidIdException;
import com.payment.auth.exception.InvalidPasswordException;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import com.payment.auth.model.response.wrapper.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExceptionHandleAdvice {

    @Autowired
    private MessageSource messageSource;

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
    public ResponseEntity handleInvalidPasswordException(){
        return new ResponseEntity<>(new ResponseWrapper(StatusCode.INVALID_PASSWORD),HttpStatus.OK);
    }
}
