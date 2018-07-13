package com.chenlei.client_4.config;

import com.chenlei.client_4.security.oauth2.provider.expression.ExtendOAuth2MethodSecurityExpressionHandler;
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

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new ExtendOAuth2MethodSecurityExpressionHandler();
    }

}
