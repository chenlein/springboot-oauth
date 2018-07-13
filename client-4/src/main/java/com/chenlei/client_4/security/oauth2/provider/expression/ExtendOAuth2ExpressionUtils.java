package com.chenlei.client_4.security.oauth2.provider.expression;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

/**
 * @Author: 陈磊
 * @Date: 2018/7/12
 * @Description:
 */
public abstract class ExtendOAuth2ExpressionUtils {

    public static boolean hasMatchingAnyScope(Authentication authentication, String[] scopes) {

        if (authentication instanceof OAuth2Authentication) {
            OAuth2Request clientAuthentication = ((OAuth2Authentication) authentication).getOAuth2Request();
            for (String scopeRegex : clientAuthentication.getScope()) {
                for (String scope : scopes) {
                    if (scope.matches(scopeRegex)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
