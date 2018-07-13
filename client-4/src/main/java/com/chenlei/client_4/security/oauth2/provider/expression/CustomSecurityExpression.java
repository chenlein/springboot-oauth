package com.chenlei.client_4.security.oauth2.provider.expression;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Author: 陈磊
 * @Date: 2018/7/13
 * @Description: 自定义鉴权方法
 */

@Component("cse")
public class CustomSecurityExpression {

    /**
     * 允许所有请求，打印鉴权参数
     * 可用参数包括：
     *      org.springframework.security.access.expression.method.MethodSecurityExpressionRoot的所有变量
     *      org.springframework.security.web.access.expression.WebSecurityExpressionRoot的所有变量
     * @return
     */
    public boolean permitAll(Authentication authentication, String... parameter) {
        System.out.println(authentication);
        System.out.println(parameter);
        return true;
    }

}
