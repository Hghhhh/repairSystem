package com.banzhuan.accountservice.controller;

import com.banzhuan.accountservice.dto.CodeMsg;
import com.banzhuan.accountservice.dto.Result;
import com.banzhuan.accountservice.entity.User;
import com.banzhuan.accountservice.pojo.WeChatResult;
import com.banzhuan.accountservice.service.AccountService;
import com.banzhuan.accountservice.util.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController("/account-service")
public class WeChatController {
    //appId
    @Value("${wechat.app_id}")
    private String appId;
    //小程序密钥
    @Value("${wechat.secret}")
    private String secret;

    @Autowired
    private AccountService accountService;

    /**
     * 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     */
    @RequestMapping("/wxLogin")
    public Result<String> login(@RequestParam String code) {
        WeChatResult weChatResult = WeChatUtil.code2Session(appId,secret);
        if(StringUtils.isNotBlank(weChatResult.getOpenid())){
            User user = new User();
            user.setOpenId(weChatResult.getOpenid());
            accountService.save(user);
            return Result.success(weChatResult.getOpenid());
        }
        else{
            return Result.error(new CodeMsg(Integer.parseInt(weChatResult.getErrcode()),weChatResult.getErrmsg()));
        }
    }
}
