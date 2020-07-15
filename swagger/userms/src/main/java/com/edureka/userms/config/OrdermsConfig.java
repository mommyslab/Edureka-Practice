package com.edureka.userms.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class OrdermsConfig {
	
	@Bean
	@Qualifier("restTemplate")
	@LoadBalanced // internally uses Ribbon
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Primary
	@Qualifier("oAuth2RestTemplate")
	@LoadBalanced
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

