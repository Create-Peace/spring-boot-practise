package com.ming.practise.common.config;

import com.ming.practise.common.config.bean.ResponseView;
import com.ming.practise.common.enums.OperationStatus;
import com.ming.practise.common.validateException.ValidateException;
import javassist.NotFoundException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice implements ResponseBodyAdvice<Object> {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthException.class)
    public Object authHandler(AuthException e) {
        return new ResponseView<>(OperationStatus.FAILURE, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    public Object notFoundHandler(Exception e) {
        return new ResponseView<>(OperationStatus.FAILURE, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public Object handler(Exception e) {
        e.printStackTrace();
        return new ResponseView<>(OperationStatus.FAILURE, e.getMessage());
    }

    @ExceptionHandler(ValidateException.class)
    public Object handleConstraintViolationException(ValidateException e) {
        System.out.println(e.getMessages());
        return new ResponseView<>(OperationStatus.FAILURE, e.getMessages());
    }

    @Override
    public boolean supports(
        MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response) {
        if (!(body instanceof ResponseView)) {
            return new ResponseView<>(OperationStatus.SUCCESS, body);
        } else {
            return body;
        }
    }
}

