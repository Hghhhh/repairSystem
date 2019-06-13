package com.banzhuan.bankendservice.shiro;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT令牌
 */
@Data
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -790191688300000066L;

    private String jwt;// json web token
    private String host;// 客户端IP

    public JwtToken(String jwt,String host){
        this.jwt = jwt;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }

}
