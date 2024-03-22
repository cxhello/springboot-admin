package com.cxhello.admin.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.service.ResourceService;
import com.cxhello.admin.service.impl.ResourceServiceImpl;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cxhello
 * @create 2019/11/18 15:05
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ResourceService resourceService(){
        return new ResourceServiceImpl();
    }

    /**
     * 管理Shiro中一些bean的生命周期
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自定义的认证类，继承子AuthorizingRealm，负责用户的认证和权限处理
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public MyShiroRealm shiroRealm(){
        MyShiroRealm realm = new MyShiroRealm();
        return realm;
    }

    /**
     * 将Realm加入securityManager
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /**
     * shiro filter 工厂类
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //1.定义ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //2.设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //3.配置拦截器
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/previlige/no");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //允许访问静态资源
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/actuator/**", "anon");
        //登录路径
        filterChainDefinitionMap.put("/login", "anon");
        List<Resource> resourceList = resourceService().findAll();
        for (Resource resource : resourceList) {
            filterChainDefinitionMap.put(resource.getSourceUrl(), "perms[" + resource.getSourceKey() + "]");
        }
        //过滤连接自定义，从上往下顺序执行，所以用/**放在最下边
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
