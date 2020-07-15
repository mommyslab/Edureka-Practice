package com.doehrs.secondms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

@Configuration
public class FirstmsClientConfig {

    @Bean
    public OAuth2RestTemplate getOAuth2RestTemplate(final OAuth2ClientContext clientContext) {
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
        return oAuth2RestTemplate;
    }

    @Bean
    public OAuth2ProtectedResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setId("1");
        details.setClientId("secondms");
        details.setClientSecret("456");
        details.setAccessTokenUri("http://localhost:9001/oauth/token");
        details.setScope(Arrays.asList("read", "write", "admin"));
        details.setGrantType("client_credentials");
        return details;
    }

}
