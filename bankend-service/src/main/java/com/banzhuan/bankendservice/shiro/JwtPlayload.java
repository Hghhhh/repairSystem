package com.banzhuan.bankendservice.shiro;

import lombok.Data;

import java.util.Date;

@Data
public class JwtPlayload {

    private String id;
    private String userId;
    private String issuer;
    private Date issuedAt;
    private String audience;
    private String roles;
    private String perms;

}
