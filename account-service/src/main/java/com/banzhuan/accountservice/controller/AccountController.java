package com.banzhuan.accountservice.controller;

import com.banzhuan.accountservice.dto.Result;
import com.banzhuan.accountservice.entity.User;
import com.banzhuan.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("{openId}")
    public Result<User> getUser(@PathVariable("openId") String openId){
        return Result.success(accountService.getUser(openId));
    }

    @PostMapping("")
    public Result<Integer> updateUser(String number,String name,Integer dormitoryId,String roleName,String openId){
        return Result.success(accountService.updateUserInfo( number, name, dormitoryId, roleName, openId));
    }

}
