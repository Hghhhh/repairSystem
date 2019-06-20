package com.banzhuan.accountservice.controller;

import com.banzhuan.accountservice.dto.CodeMsg;
import com.banzhuan.accountservice.dto.Result;
import com.banzhuan.accountservice.entity.User;
import com.banzhuan.accountservice.service.AccountService;
import com.banzhuan.accountservice.service.AuthService;
import com.banzhuan.accountservice.util.HmaCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


@RestController
@RequestMapping("/account-service/users")
public class AccountController {

    @Value("${hmac.secretKey}")
    private String secretKey;
    @Value("${hmac.algorithm}")
    private String enAlgorithm;

    @Autowired
    private AuthService authService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/{openId}")
    public Result<User> getUser(@PathVariable("openId") String openId){
        return Result.success(accountService.getUser(openId));
    }

    @PutMapping
    public Result<String> updateUser(@RequestParam String number,@RequestParam String name,Integer dormitoryId,@RequestParam String roleName,@RequestParam String openId){
        if(accountService.updateUserInfo( number, name, dormitoryId, roleName, openId)==1){
            Long current = System.currentTimeMillis() ;
            String clientKey = openId;// 客户端标识（用户名）
            String mix = String.valueOf(new Random().nextFloat());// 随机数，进行混淆
            String timeStamp = current.toString();// 时间戳
            String baseString = clientKey+mix+timeStamp;
            String digest = HmaCUtil.hmacDigest(baseString,enAlgorithm,secretKey);// 生成HMAC摘要
            RestTemplate client = new RestTemplate();
            Result<String> response = authService.applyToken(clientKey,mix,timeStamp,digest);
            return response;
        }else{
            return Result.error(CodeMsg.DOUBLE_DO_ERROR);
        }
    }





}
