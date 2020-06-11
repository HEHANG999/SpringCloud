package com.project.config;
import com.project.util.CustomRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * shiro配置
 */
@Configuration
public class ShiroConfig {

    //将自己的验证方式加入容器
    @Bean
    /**创建Realm*/
    public CustomRealm myShiroRealm() {
        CustomRealm myShiroRealm = new CustomRealm();
        return myShiroRealm;
    }



    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联Realm
        securityManager.setRealm(myShiroRealm());

        return securityManager;
    }



    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理，关联securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * 可实现权限相关的拦截器
         * 常用的过滤器：
         *       anon：无需认证（登录）也可访问
         *       authc：必须认证才可访问
         *       user：如果使用rememberMe的功能可以直接访问
         *       perms：该资源必须得到资源权限才可访问
         *       role：该资源必须得到角色权限才可访问
         */
        Map<String,String> map = new LinkedHashMap<String, String>();//保证了排序
        /*map.put("/add","authc");//拦截某个路径
        map.put("/update","authc");*/

        map.put("/test.html", "anon");//放行某个路径，anon必须用在authc前面！否则失效！
        map.put("/toLogin", "anon");//放行请求表单
        map.put("/*","authc");//拦截所有！


        //认证不成功，修改默认跳转的登录页面（默认为login.jsp）
        shiroFilterFactoryBean.setLoginUrl("/login");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


       /* Map<String,String> map = new HashMap<String, String>();
        map.put("/logout","logout"); //登出
        map.put("/**","authc");      //**对所有用户认证 authc-拦截的url
        //map.put("/login","anon");  //anon-放行的URL

        //登录
        shiroFilterFactoryBean.setLoginUrl("/loginUser");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);*/
        return shiroFilterFactoryBean;
    }

   /* @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }*/

}
