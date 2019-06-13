package com.banzhuan.authclient.service;

import com.banzhuan.authclient.dto.CodeMsg;
import com.banzhuan.authclient.dto.Result;

public class DataFallBack implements AuthService {
    @Override
    public Result<String> applyToken(String clientKey, String mix, String timeStamp, String digest) {
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
