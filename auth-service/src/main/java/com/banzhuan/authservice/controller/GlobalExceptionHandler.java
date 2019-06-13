package com.banzhuan.authservice.controller;

import com.banzhuan.authservice.dto.CodeMsg;
import com.banzhuan.authservice.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    public Result exceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(new CodeMsg(500,e.getMessage()));
    }
}