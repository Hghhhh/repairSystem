package com.banzhuan.accountservice.controller;

import com.banzhuan.accountservice.dto.Result;
import com.banzhuan.accountservice.entity.User;
import com.banzhuan.accountservice.service.AccountService;
import com.banzhuan.accountservice.service.CodeService;
import com.banzhuan.accountservice.util.JacksonUtil;
import com.banzhuan.accountservice.util.TencentSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/account-service/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CodeService codeService;

    @GetMapping("/{userId}")
    public Result<User> getUser(@PathVariable("userId") String number){

        return Result.success(accountService.getUser(number));
    }

    @PostMapping
    public Result<User> updateUser(@RequestBody String user){
        User user1 = JacksonUtil.json2pojo(user,User.class);
        return Result.success(accountService.save(user1));
    }

    /**
     * 获取验证码
     *
     * @param phone
     * @return 验证码
     */
    @GetMapping(value = "/getSms/{phone}")
    public Result<Boolean> getSms(@PathVariable(value = "phone") String phone) {
        // return TencentSmsSender.sendMessage(phone);
        String code = TencentSmsSender.sendMessage(phone);
        if (code == null) {
            return Result.success(false);
        }
        codeService.valuePut(phone, code);
        codeService.expirse(phone, 5, TimeUnit.MINUTES);
        return Result.success(true);
    }

    /**
     * 根据手机号去redis中判断code和输入的code是否一样
     *
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "/verifyCode")
    public Result<Boolean> verifyCode(@RequestParam String phone, @RequestParam String code) {
        String findCode = (String) codeService.getValue(phone);
        if (findCode == null) {
        } else {
            if (findCode.equals(code)) {
                codeService.remove(phone);
                return Result.success(true);
            }
        }
        return Result.success(false);
    }

}
