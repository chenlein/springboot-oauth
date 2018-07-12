package com.chenlei.client_4.security.oauth2.provider.expression;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2SecurityExpressionMethods;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author: 陈磊
 * @Date: 2018/7/12
 * @Description:
 */
public class OAuth3SecurityExpressionMethods extends OAuth2SecurityExpressionMethods {

    private final Authentication authentication;

    private Set<String> missingScopes = new LinkedHashSet<String>();

    public OAuth3SecurityExpressionMethods(Authentication authentication) {
        super(authentication);
        this.authentication = authentication;
    }

    @Override
    public boolean hasAnyScopeMatching(String... scopesRegex) {
        boolean result = OAuth3ExpressionUtils.hasAnyScopeMatching(authentication, scopesRegex);
        if (!result) {
            missingScopes.addAll(Arrays.asList(scopesRegex));
        }
        return result;
    }
}
