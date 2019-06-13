package com.banzhuan.bankendservice.controller;

import com.banzhuan.bankendservice.dto.CodeMsg;
import com.banzhuan.bankendservice.dto.Result;
import com.banzhuan.bankendservice.service.AdminService;
import com.banzhuan.bankendservice.util.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class LoginController {


    @Value("${jwt.secret-key}")
    private String secretKey;

    //加密算法
    @Value("${jwt.algorithm}")
    private String jwtEncodeAlgo;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result<String> login(@RequestParam String account,@RequestParam String password){
        if(adminService.login(account,password)){
            //后台系统，自己签发jwt token即可
            String jwt = JwtUtil.issueJwt(secretKey, account,
                    "banzhuan", null, "admin", "/*",getSignatureAlgorithm());
            return Result.success(jwt);
        }else{
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
    }

    public SignatureAlgorithm getSignatureAlgorithm(){
        switch (jwtEncodeAlgo){
            case "NONE": return  SignatureAlgorithm.NONE;
            case "HS256": return  SignatureAlgorithm.HS256;
            case "HS384": return  SignatureAlgorithm.HS384;
            case "HS512": return  SignatureAlgorithm.HS512;
            case "RS256": return  SignatureAlgorithm.RS256;
            case "RS384": return  SignatureAlgorithm.RS384;
            case "RS512": return  SignatureAlgorithm.RS512;
            case "ES256": return  SignatureAlgorithm.ES256;
            case "ES384": return  SignatureAlgorithm.ES384;
            case "ES512": return  SignatureAlgorithm.ES512;
            case "PS256": return  SignatureAlgorithm.PS256;
            case "PS384": return  SignatureAlgorithm.PS384;
            case "PS512": return  SignatureAlgorithm.PS512;
            default: return  SignatureAlgorithm.HS512;
        }
    }
}
