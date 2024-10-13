package com.example.saudeapiback.infra.security;

import com.example.saudeapiback.utils.UpdateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Component
public class UpdateInterceptor implements HandlerInterceptor {

    @Autowired
    private UpdateStatus updateStatus;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (updateStatus.isUpdating()) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.setContentType("text/plain; charset=UTF-8");
            response.getOutputStream().write("O banco de dados está sendo atualizado. Tente novamente mais tarde.".getBytes(StandardCharsets.UTF_8));
            return false;  // Bloqueia a requisição
        }
        return true;  // Prossegue com a requisição
    }
}