package com.HotUdon.config;

import com.HotUdon.interceptor.LogInterceptor;
import com.HotUdon.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Slf4j
@Configuration
//일반적인 웹 설정과 인터셉터를 관리
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/logo/**")
                .addResourceLocations("classpath:/static/logo/");
    }

     @Autowired
        private LogInterceptor logInterceptor;

        @Autowired
        private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .order(1); // 모든 경로에 대해 로깅을 수행하는 Interceptor  우선 순위를 정한다.
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/product/content/**","/product/search/**","/login", "/members/join", "/static/**", "/uploads/**", "/profile/**")
                .order(3);
    }
}
