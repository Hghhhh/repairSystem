package com.banzhuan.accountservice.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;
import java.util.Random;

public class TencentSmsSender {

    // 短信应用SDK AppID
    private static final int APPID = 1400171279;

    // 短信应用SDK AppKey
    private static final String APPKEY = "84759fcbac90b75c4d3865f4505929ac";

    // 签名
    private static final String SMSSIGN = "鲨鲨服饰";

    private static final int TEMPLATEID = 247585;

    public static String sendMessage(String phoneNumber) {
        try {
            String[] params = {"", "" };
            params[0] = randomNum();
            params[1] = "5";
            SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, TEMPLATEID, params, SMSSIGN, "", "");
            return params[0];
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return null;
    }

    public static String randomNum() {
        Random random = new Random();
        StringBuffer num = new StringBuffer();
        num.append(random.nextInt(10));
        num.append(random.nextInt(10));
        num.append(random.nextInt(10));
        num.append(random.nextInt(10));
        return num.toString();
    }
}
