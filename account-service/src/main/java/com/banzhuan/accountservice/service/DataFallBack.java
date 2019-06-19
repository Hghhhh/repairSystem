package com.banzhuan.accountservice.service;

import com.banzhuan.accountservice.dto.CodeMsg;
import com.banzhuan.accountservice.dto.Result;

public class DataFallBack implements AuthService {
    @Override
    public Result<String> applyToken(String clientKey, String mix, String timeStamp, String digest) {
        return Result.error(CodeMsg.SERVER_ERROR);
    }


}
