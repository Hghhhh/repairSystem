package com.banzhuan.authservice.service;

import com.banzhuan.authservice.dto.CodeMsg;
import com.banzhuan.authservice.dto.Result;
import com.banzhuan.authservice.entity.User;

public class DataFallBack implements AccountService {
    @Override
    public Result<User> getUser(String openId) {
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
