package com.banzhuan.repairservice.controller;

import com.banzhuan.repairservice.dto.CodeMsg;
import com.banzhuan.repairservice.dto.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RepairExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> handler(Exception e){
        e.printStackTrace();
        return Result.error(CodeMsg.SERVER_ERROR);
    }

}
