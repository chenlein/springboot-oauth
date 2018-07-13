package com.chenlei.client_4.config;

import com.chenlei.client_4.security.oauth2.provider.expression.ExtendOAuth2WebSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @Author: 陈磊
 * @Date: 2018/6/30
 * @Description:
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    /**
     * 因为AuthorizationServerProperties只在EnableAuthorizationServer时才会启用，所以这里不能直接使用Autowired
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.authorization")
    public AuthorizationServerProperties getAuthorizationServerProperties() {
        return new AuthorizationServerProperties();
    }

    /**
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(this.getAuthorizationServerProperties().getCheckTokenAccess());
        remoteTokenServices.setClientId(this.oAuth2ClientProperties.getClientId());
        remoteTokenServices.setClientSecret(this.oAuth2ClientProperties.getClientSecret());

        // 往ExpressionHandler中设置ApplicationContext，否则在鉴权时无法通过@调用CustomSecurityExpression，比如：@cse.permitAll(authentication, 'customSecurityExpression')
        ExtendOAuth2WebSecurityExpressionHandler extendOAuth2WebSecurityExpressionHandler = new ExtendOAuth2WebSecurityExpressionHandler();
        extendOAuth2WebSecurityExpressionHandler.setApplicationContext(this.applicationContext);

        resources.tokenServices(remoteTokenServices).expressionHandler(extendOAuth2WebSecurityExpressionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/resource/web/**").access("#oauth2.hasMatchingScope('read')")  // 使用扩展的表达式鉴权
                .antMatchers("/resource/cse/web/**").access("@cse.permitAll(authentication, 'customSecurityExpression')")  // 使用自定义表达式鉴权
                .and().anonymous().disable();
    }

}
