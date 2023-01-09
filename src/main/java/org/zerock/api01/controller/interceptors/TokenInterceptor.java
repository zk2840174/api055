package org.zerock.api01.controller.interceptors;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zerock.api01.util.JWTUtil;


import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;


@Log4j2
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getServletPath();

        log.info("TokenInterceptor preHandle..............." + path);

        String authHeader = request.getHeader("Authorization");

        //나중에 false로 변경필요
        if(authHeader == null ){
            log.info("auth header is null");
            //return true;
            makeErrorResponse(response, "AccessTokenNull", 401);
            return false;

        }



        //Bearer 7
        String accessToken = authHeader.substring(7);

        log.info(jwtUtil);
        log.info(accessToken);

        if(path.equals("/api/sample/member/refresh")) {

            log.info("try refresh.............");
            return true;
        }

        try {

            jwtUtil.validateToken(accessToken);


        }catch(ExpiredJwtException e){

            log.info("Expired Token");

            makeErrorResponse(response, "ExpiredAccess", 401);
            return false;
        }

        return true;
    }

    private void makeErrorResponse(HttpServletResponse response, String msg, int code)throws IOException {

        response.setContentType("application/json");

        response.setStatus(401);

        Writer out = response.getWriter();

        out.write("{ \"msg\" : \""+msg+"\" }");



    }



}
