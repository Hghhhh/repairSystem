package com.banzhuan.authclient;

import com.banzhuan.authclient.dto.Result;
import com.banzhuan.authclient.service.AuthService;
import com.banzhuan.authclient.util.HmaCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RefreshScope
public class ApplyController {

    @Value("${hmac.secretKey}")
    private String secretKey;
    @Value("${hmac.algorithm}")
    private String enAlgorithm;

    @Autowired
    private AuthService authService;

    @GetMapping("/applyToken")
    public Result<String> getToken(@RequestParam String openId){
        Long current = System.currentTimeMillis() ;
        String clientKey = openId;// 客户端标识（用户名）
        String mix = String.valueOf(new Random().nextFloat());// 随机数，进行混淆
        String timeStamp = current.toString();// 时间戳
        String baseString = clientKey+mix+timeStamp;
        String digest = HmaCUtil.hmacDigest(baseString,enAlgorithm,secretKey);// 生成HMAC摘要
        RestTemplate client = new RestTemplate();
        Result<String> response = authService.applyToken(clientKey,mix,timeStamp,digest);
        return response;
    }
}
