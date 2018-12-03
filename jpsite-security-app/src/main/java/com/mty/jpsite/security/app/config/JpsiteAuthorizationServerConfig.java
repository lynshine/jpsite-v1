package com.mty.jpsite.security.app.config;

import com.mty.jpsite.security.core.properties.OAuth2ClientProperties;
import com.mty.jpsite.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 *
 */
@Configuration
@EnableAuthorizationServer
public class JpsiteAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;
    /**
     * 注入authenticationManager
     * 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private TokenStore jwtTokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
                builder.withClient(config.getClientId())
                        .secret(config.getClientSecret())
                        .accessTokenValiditySeconds(config.getAccessTokenValidateSeconds())
                        .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                        .scopes("all", "read", "write");

            }
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // security.passwordEncoder(passwordEncoder);
    }

    //
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore);
    }
}
