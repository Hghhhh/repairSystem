package com.banzhuan.authservice;

import com.banzhuan.authservice.entity.AgileSubjectFactory;
import com.banzhuan.authservice.entity.HmacRealm;
import com.banzhuan.authservice.filter.HmacFilter;
import com.banzhuan.authservice.service.AccountService;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
public class ShiroConfig {

    @Value("${shiro.hmac.algorithm}")
    private String enAlgorithm;

    @Value("${shiro.hmac.secretkey}")
    private String secretkey;

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,Filter> map = new HashMap<>();
        map.put("hmac",new HmacFilter());
        shiroFilterFactoryBean.setFilters(map);
        Map<String,String> pathFilter = new HashMap<>();
        pathFilter.put("/auth/apply-token","hmac");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(pathFilter);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("hmacRealm")HmacRealm userRealm){
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
    @Bean(name="hmacRealm")
    public HmacRealm getRealm(AccountService accountService){

        return new HmacRealm(accountService,secretkey,enAlgorithm);
    }
}