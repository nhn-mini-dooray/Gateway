package com.nhnacademy.mini_dooray.gateway.auth.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DaoFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        request.setAttribute("statusCode", "400 BAD Request");
        request.setAttribute("responseBody", "ID, PW가 일치하지 않습니다.");
        request.getRequestDispatcher("/error").forward(request, response);
    }
}