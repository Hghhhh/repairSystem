package com.banzhuan.bankendservice.util;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    /**
     * JWT签发令牌
     * @param subject 用户ID
     * @param issuer 签发人
     * @param period 有效时间(毫秒)
     * @param roles 访问主张-角色
     * @param permissions 访问主张-权限
     * @param algorithm 加密算法(SignatureAlgorithm是enum)
     * @return json web token
     */
    public static String issueJwt(String jwtSecretKey
            ,String subject,String issuer,Long period,String roles
            ,String permissions,SignatureAlgorithm algorithm) {

        // 当前时间戳(精确到毫秒)
        long currentTimeMillis = System.currentTimeMillis();
        // 秘钥
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        JwtBuilder jwt  =  Jwts.builder();
        jwt.setId(UUID.randomUUID().toString());
        // 用户名
        jwt.setSubject(subject);
        // 签发者
        if(null!=issuer&&!"".equals(issuer)) jwt.setIssuer(issuer);
        // 签发时间
        jwt.setIssuedAt(new Date(currentTimeMillis));
        // 有效时间
        if(null != period){
            Date expiration = new Date(currentTimeMillis+period);
            jwt.setExpiration(expiration);
        }
        // 访问主张-角色
        if(null!=roles&&!"".equals(roles)) jwt.claim("roles", roles);
        // 访问主张-权限
        if(null!=permissions&&!"".equals(permissions)) jwt.claim("perms", permissions);
        jwt.compressWith(CompressionCodecs.DEFLATE);
        jwt.signWith(algorithm, secretKeyBytes);
        return jwt.compact();
    }
}
