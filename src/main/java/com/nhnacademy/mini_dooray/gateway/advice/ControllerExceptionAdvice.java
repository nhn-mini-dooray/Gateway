package com.nhnacademy.mini_dooray.gateway.advice;

import com.nhnacademy.mini_dooray.exception.ValidationFailedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public String httpClientErrorExceptionHandler(HttpClientErrorException exception, Model model) {
        model.addAttribute("title", exception.getStatusCode());
        model.addAttribute("body", exception.getResponseBodyAsString());
        return "error";
    }

    @ExceptionHandler(value = {
            InternalAuthenticationServiceException.class,
            ValidationFailedException.class })
    public String internalAuthenticationServiceExceptionHandler(Exception exception,
                                                                Model model) {
        model.addAttribute("title", exception.getCause());
        model.addAttribute("body", exception.getMessage());
        return "error";
    }
}
