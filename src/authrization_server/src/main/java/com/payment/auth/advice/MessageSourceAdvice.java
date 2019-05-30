package com.payment.auth.advice;

import com.payment.auth.controller.AuthController;
import com.payment.auth.model.response.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Locale;


@ControllerAdvice(basePackages = "com.payment.auth")
@Slf4j
public class MessageSourceAdvice implements ResponseBodyAdvice<ResponseWrapper> {

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public ResponseWrapper beforeBodyWrite(ResponseWrapper wrapper, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        wrapper.setMessage(messageSource.getMessage(wrapper.getMessage(), null, Locale.getDefault()));
        return wrapper;
    }
}
