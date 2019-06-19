package com.banzhuan.bankendservice.service;

import com.banzhuan.bankendservice.dto.CodeMsg;
import com.banzhuan.bankendservice.dto.Result;

public class DataFallBack implements AuthService {

    @Override
    public Result<String> applyTokenForAdmin(String clientKey, String mix, String timeStamp, String digest) {
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
