package com.banzhuan.authservice.controller;

import com.banzhuan.authservice.dto.Result;
import com.banzhuan.authservice.entity.Resource;
import com.banzhuan.authservice.entity.Role;
import com.banzhuan.authservice.entity.User;
import com.banzhuan.authservice.service.AccountService;
import com.banzhuan.authservice.util.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RefreshScope
public class JwtController {

    @Value("${jwt.secret-key}")
    private String secretKey;

    //加密算法
    @Value("${jwt.algorithm}")
    private String jwtEncodeAlgo;

    @Autowired
    private AccountService accountService;

    @PostMapping("apply-token")
    public Result<String> applyToken(@RequestParam(name = "clientKey") String clientKey,@RequestParam(name = "mix") String mix,@RequestParam(name = "timeStamp") String timeStamp,@RequestParam(name = "digest") String digest) {
        User user = accountService.getUser(clientKey).getData();
        System.out.println("         "+clientKey);
        Role role = user.getRole();
        Set<Resource> resources = role.getResources();
        StringBuffer stringBuffer = new StringBuffer();
        for(Resource resource: resources){
            stringBuffer.append(resource.getUrl()+",");
        }
        stringBuffer.subSequence(0,stringBuffer.length()-1);
        // 签发一个Json Web Token
        String jwt = JwtUtil.issueJwt(secretKey, clientKey,
                "banzhuan", null, role.getName(), stringBuffer.toString(),getSignatureAlgorithm());
        return Result.success(jwt);
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
