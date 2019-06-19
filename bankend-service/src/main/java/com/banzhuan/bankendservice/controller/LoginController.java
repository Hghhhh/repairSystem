package com.banzhuan.bankendservice.controller;

import com.banzhuan.bankendservice.dto.CodeMsg;
import com.banzhuan.bankendservice.dto.Result;
import com.banzhuan.bankendservice.service.AdminService;
import com.banzhuan.bankendservice.service.AuthService;
import com.banzhuan.bankendservice.util.HmaCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RefreshScope
public class LoginController {
    @Value("${hmac.secretKey}")
    private String secretKey;
    @Value("${hmac.algorithm}")
    private String enAlgorithm;

    @Autowired
    private AuthService authService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result<String> login(@RequestParam String account,@RequestParam String password){
        if(adminService.login(account,password)){
            Long current = System.currentTimeMillis() ;
            String clientKey = account;// 客户端标识（用户名）
            String mix = String.valueOf(new Random().nextFloat());// 随机数，进行混淆
            String timeStamp = current.toString();// 时间戳
            String baseString = clientKey+mix+timeStamp;
            String digest = HmaCUtil.hmacDigest(baseString,enAlgorithm,secretKey);// 生成HMAC摘要
            RestTemplate client = new RestTemplate();
            Result<String> response = authService.applyTokenForAdmin(clientKey,mix,timeStamp,digest);
            return response;
        }else{
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
    }
}
