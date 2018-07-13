package com.chenlei.client_4.security.oauth2.provider.expression;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2SecurityExpressionMethods;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author: 陈磊
 * @Date: 2018/7/12
 * @Description: 扩展OAuth2SecurityExpressionMethods
 */
public class ExtendOAuth2SecurityExpressionMethods extends OAuth2SecurityExpressionMethods {

    private final Authentication authentication;

    private Set<String> missingScopes = new LinkedHashSet<String>();

    public ExtendOAuth2SecurityExpressionMethods(Authentication authentication) {
        super(authentication);
        this.authentication = authentication;
    }

    public boolean hasMatchingAnyScope(String... scopes) {
        boolean result = ExtendOAuth2ExpressionUtils.hasMatchingAnyScope(authentication, scopes);
        if (!result) {
            missingScopes.addAll(Arrays.asList(scopes));
        }
        return result;
    }

    /**
     * authentication范围是否包含scope
     * @param scope
     * @return
     */
    public boolean hasMatchingScope(String scope) {
        return this.hasMatchingAnyScope(scope);
    }

}
