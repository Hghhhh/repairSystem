package com.banzhuan.bankendservice.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基于JWT标准的无状态认证过滤器
 *
 */
@Slf4j
public class JwtFilter extends AccessControlFilter {

    private final String JWT_PARAM;
    private final String JWT_TOKEN_HEAD;

    public JwtFilter(String jwtParam,String tokenHead){
        this.JWT_PARAM = jwtParam;
        this.JWT_TOKEN_HEAD = tokenHead;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(isJwtSubmission((HttpServletRequest) request)){
            AuthenticationToken token = createToken((HttpServletRequest) request);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (AuthenticationException e) {
                log.error(e.getMessage(),e);
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }
        }
        return false;
    }

    protected AuthenticationToken createToken(HttpServletRequest request) {
        String jwt = request.getHeader(JWT_PARAM);
        String host = request.getRemoteHost();
        log.info("authenticate jwt token:"+jwt);
        System.out.println("jwt:"+jwt);
        return new JwtToken(jwt.substring(JWT_TOKEN_HEAD.length()), host);
    }

    protected boolean isJwtSubmission(HttpServletRequest request) {
        String jwt = request.getHeader(JWT_PARAM);
        return StringUtils.isNotBlank(jwt)&&jwt.startsWith(JWT_TOKEN_HEAD);
    }

}
