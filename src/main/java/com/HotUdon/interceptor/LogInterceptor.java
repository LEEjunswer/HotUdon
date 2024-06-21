package com.HotUdon.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response,Object handler) throws Exception{
        System.out.println("LogInterceptor - preHandle : " + request.getRequestURI() );

        return true;
    }
}
