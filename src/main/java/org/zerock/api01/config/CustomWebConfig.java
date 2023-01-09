package org.zerock.api01.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zerock.api01.controller.interceptors.TokenInterceptor;
import org.zerock.api01.util.JWTUtil;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class CustomWebConfig implements WebMvcConfigurer {

    private final JWTUtil jwtUtil;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new TokenInterceptor(jwtUtil)).addPathPatterns("/api/sample/todos/*");
    }
}

