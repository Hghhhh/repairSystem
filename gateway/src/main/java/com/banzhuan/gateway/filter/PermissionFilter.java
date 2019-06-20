package com.banzhuan.gateway.filter;

import com.banzhuan.gateway.pojo.JwtPlayload;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
@Component
public class PermissionFilter extends ZuulFilter {

    @Value("${jwt.secret-key}")
    private String secretkey;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("Authorization");
        String method = request.getMethod();
        String url = request.getRequestURI();
        System.out.println("asdadada             "+url+" ");
        System.out.println("asdadada             "+method+" ");
        //对免验证接口直接通过
        if(url.equals("/account-service/users")&&method.equals("PUT") || url.equals("/account-service/wxLogin") || url.equals("/backend-service/login")){
            System.out.println("11111111111111");
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，可以在多个过滤器时使用
            return null;
        }
        JwtPlayload jwtPlayload;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretkey))
                    .parseClaimsJws(token)
                    .getBody();
            jwtPlayload = new JwtPlayload();
            jwtPlayload.setId(claims.getId());
            jwtPlayload.setUserId(claims.getSubject());// 用户名
            jwtPlayload.setIssuer(claims.getIssuer());// 签发者
            jwtPlayload.setIssuedAt(claims.getIssuedAt());// 签发时间
            jwtPlayload.setAudience(claims.getAudience());// 接收方
            jwtPlayload.setRoles(claims.get("roles", String.class));// 访问主张-角色
            jwtPlayload.setPerms(claims.get("perms", String.class));// 访问主张-权限
            if(jwtPlayload.getPerms().contains(url)||jwtPlayload.getPerms().equals("*")){
                ctx.setSendZuulResponse(true);// 对该请求进行路由
                ctx.setResponseStatusCode(200);
                ctx.set("isSuccess", true);// 设值，可以在多个过滤器时使用
                return null;
            }
            else{
                throw new Exception("403");
            }
        } catch (Exception e){
            e.printStackTrace();
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(403);// 返回错误码
            ctx.setResponseBody("token error!!!");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
