package com.chenlei.client_4.security.oauth2.provider.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2ExpressionParser;

/**
 * @Author: 陈磊
 * @Date: 2018/7/13
 * @Description: 扩展Oauth2支持的鉴权表达式，用于GlobalMethodSecurity
 */
public class ExtendOAuth2MethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    public ExtendOAuth2MethodSecurityExpressionHandler() {
        this.setExpressionParser(new OAuth2ExpressionParser(getExpressionParser()));
    }

    @Override
    public StandardEvaluationContext createEvaluationContextInternal(Authentication authentication, MethodInvocation methodInvocation) {
        StandardEvaluationContext standardEvaluationContext = super.createEvaluationContextInternal(authentication, methodInvocation);
        standardEvaluationContext.setVariable("oauth2", new ExtendOAuth2SecurityExpressionMethods(authentication));
        return standardEvaluationContext;
    }
}
