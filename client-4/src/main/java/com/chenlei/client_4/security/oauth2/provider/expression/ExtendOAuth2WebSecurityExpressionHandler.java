package com.chenlei.client_4.security.oauth2.provider.expression;

import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2ExpressionParser;
import org.springframework.security.oauth2.provider.expression.OAuth2SecurityExpressionMethods;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @Author: 陈磊
 * @Date: 2018/7/12
 * @Description: 扩展Oauth2支持的鉴权表达式，用于WebSecurity
 */
public class ExtendOAuth2WebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

    public ExtendOAuth2WebSecurityExpressionHandler() {
        this.setExpressionParser(new OAuth2ExpressionParser(getExpressionParser()));
    }

    @Override
    protected StandardEvaluationContext createEvaluationContextInternal(Authentication authentication, FilterInvocation invocation) {
        StandardEvaluationContext standardEvaluationContext = super.createEvaluationContextInternal(authentication, invocation);
        standardEvaluationContext.setVariable("oauth2", new ExtendOAuth2SecurityExpressionMethods(authentication));
        return standardEvaluationContext;
    }
}
