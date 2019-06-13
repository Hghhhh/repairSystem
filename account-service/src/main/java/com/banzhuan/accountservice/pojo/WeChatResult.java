package com.banzhuan.accountservice.pojo;

import lombok.Data;
import weixin.popular.bean.BaseResult;

@Data
public class WeChatResult extends BaseResult{
    private String openid;

}
