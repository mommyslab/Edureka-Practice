package com.edureka.userms.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //FIXME: Disabling security for ease of integration with firstms
        /*http.requestMatchers()
                .antMatchers("/api/usersms/client/**")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/secondms/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/api/secondms/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/api/secondms/**").access("#oauth2.hasScope('admin')");
        */
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();

        http.headers().frameOptions().disable();
        http.headers().disable();

//        http.requestMatchers()
//                .antMatchers("/**")
//                .and()
//                .authorizeRequests()
//                .anyRequest().denyAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);

        return defaultTokenServices;
    }

    public static class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

        @Override
        public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
            jwtAccessTokenConverter.setAccessTokenConverter(this);
        }

        @Override
        public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
            final OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
            oAuth2Authentication.setDetails(map);
            map.entrySet().stream().forEach(es-> System.out.println("key: " + es.getKey() +
                    "-" + es.getValue()));
            return oAuth2Authentication;
        }
    }

    @GetMapping("/oauthdata")
    public void getOauthdata() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("*******");
        final Object principal = authentication.getPrincipal();
        System.out.println("principal: "+ principal);
    }
}
