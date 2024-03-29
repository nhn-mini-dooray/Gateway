package com.nhnacademy.mini_dooray.gateway.advice;

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

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public String internalAuthenticationServiceExceptionHandler(InternalAuthenticationServiceException exception,
                                                                Model model) {
        model.addAttribute("title", exception.getCause());
        model.addAttribute("body", exception.getMessage());
        return "error";
    }
}
