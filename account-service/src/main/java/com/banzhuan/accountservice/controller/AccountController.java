package com.banzhuan.accountservice.controller;

import com.banzhuan.accountservice.dto.Result;
import com.banzhuan.accountservice.entity.User;
import com.banzhuan.accountservice.service.AccountService;
import com.banzhuan.accountservice.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-service/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{userId}")
    public Result<User> getUser(@PathVariable("userId") Integer userId){

        return Result.success(accountService.getUser(userId));
    }

    @PostMapping
    public Result<User> updateUser(@RequestParam String user){
        User user1 = JacksonUtil.json2pojo(user,User.class);
        return Result.success(accountService.save(user1));
    }
}
