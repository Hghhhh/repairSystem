package com.banzhuan.authservice.filter;

import com.banzhuan.authservice.entity.HmacToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 基于HMAC（ 散列消息认证码）的无状态认证过滤器
 */
@Slf4j
public class HmacFilter extends AccessControlFilter {

    public static final String DEFAULT_CLIENTKEY_PARAM = "clientKey";
    public static final String DEFAULT_TIMESTAMP_PARAM = "timeStamp";
    public static final String DEFAUL_DIGEST_PARAM = "digest";

    /**
     * 是否放行
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                      Object mappedValue) throws Exception {
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            return true;
            //已经认证过直接放行
        }
        return false;
        //转到拒绝访问处理逻辑
    }

    /**
     * 拒绝处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        //如果是Hmac鉴权的请求
        if(isHmacSubmission(request)){
            //创建令牌
            AuthenticationToken token = createToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                //认证
                subject.login(token);
                //认证成功，过滤器链继续
                return true;
            } catch (AuthenticationException e) {
                //认证失败，发送401状态并附带异常信息
                log.error(e.getMessage(),e);
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }
        }
        //打住，访问到此为止
        return false;
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String clientKey = request.getParameter(DEFAULT_CLIENTKEY_PARAM);
        String timeStamp= request.getParameter(DEFAULT_TIMESTAMP_PARAM);
        String digest= request.getParameter(DEFAUL_DIGEST_PARAM);
        Map<String, String[]> parameters = request.getParameterMap();
        String host = request.getRemoteHost();
        return new HmacToken(clientKey, timeStamp, digest, host,parameters);
    }

    protected boolean isHmacSubmission(ServletRequest request) {
        String clientKey = request.getParameter(DEFAULT_CLIENTKEY_PARAM);
        String timeStamp= request.getParameter(DEFAULT_TIMESTAMP_PARAM);
        String digest= request.getParameter(DEFAUL_DIGEST_PARAM);
        return (request instanceof HttpServletRequest)
                && StringUtils.isNotBlank(clientKey)
                && StringUtils.isNotBlank(timeStamp)
                && StringUtils.isNotBlank(digest);
    }
}