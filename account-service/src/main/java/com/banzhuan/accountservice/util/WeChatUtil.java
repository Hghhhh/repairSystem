package com.banzhuan.accountservice.util;

import com.banzhuan.accountservice.pojo.WeChatResult;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import weixin.popular.api.BaseAPI;
import weixin.popular.client.LocalHttpClient;


public class WeChatUtil  extends BaseAPI {

    /**
     * 微信小程序登录，获取openId
     * @param appId
     * @param secret
     * @return
     */
    public static WeChatResult code2Session(String appId,String secret) {
        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(jsonHeader)
                .setUri("https://api.weixin.qq.com/sns/jscode2session")
                .addParameter("appid", appId)
                .addParameter("secret", secret)
                .addParameter("js_code", "JSCODE")
                .addParameter("grant_type", "authorization_code")
                .build();;
        return (WeChatResult) LocalHttpClient.executeJsonResult(httpUriRequest, WeChatResult.class);
    }
}
