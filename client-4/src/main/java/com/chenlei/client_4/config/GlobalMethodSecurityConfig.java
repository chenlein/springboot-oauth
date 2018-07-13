package com.chenlei.client_4.config;

import com.chenlei.client_4.security.oauth2.provider.expression.ExtendOAuth2MethodSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * @Author: 陈磊
 * @Date: 2018/7/12
 * @Description:
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        // 往ExpressionHandler中设置ApplicationContext，否则在鉴权时无法通过@调用CustomSecurityExpression，比如：@cse.permitAll(authentication, 'customSecurityExpression')
        ExtendOAuth2MethodSecurityExpressionHandler extendOAuth2MethodSecurityExpressionHandler = new ExtendOAuth2MethodSecurityExpressionHandler();
        extendOAuth2MethodSecurityExpressionHandler.setApplicationContext(this.applicationContext);

        return extendOAuth2MethodSecurityExpressionHandler;
    }

}
