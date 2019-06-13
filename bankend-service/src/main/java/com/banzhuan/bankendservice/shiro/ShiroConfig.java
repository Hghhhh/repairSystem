package com.banzhuan.bankendservice.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Shiro的配置类
 * @author lenovo
 *
 */
@Configuration
@RefreshScope
public class ShiroConfig {

    @Value("${jwt.header}")
    private String jwtHeader;

    @Value("${jwt.secret-key}")
    private String secretkey;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,Filter> map = new HashMap<>();
        map.put("jwt",new JwtFilter(jwtHeader,tokenHead));
        map.put("jwtPerms",new JwtPermFilter());
        shiroFilterFactoryBean.setFilters(map);
        Map<String,String> pathFilter = new HashMap<>();
        //配置过滤器，连接url
        pathFilter.put("/auth/apply-token","jwt");
        pathFilter.put("/api/delete/", "jwtPerms['api:delete']");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(pathFilter);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("jwtRealm")JwtRealm userRealm){
        //一大堆配置，为了禁用session，牛批
        userRealm.setCachingEnabled(false);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSubjectFactory(new AgileSubjectFactory());
        DefaultSubjectDAO defaultSubjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = (DefaultSessionStorageEvaluator)defaultSubjectDAO.getSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
        defaultSessionManager.setSessionValidationSchedulerEnabled(false);
        securityManager.setSessionManager(defaultSessionManager);
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name="jwtRealm")
    public JwtRealm getRealm(){

        return new JwtRealm(secretkey);
    }
}