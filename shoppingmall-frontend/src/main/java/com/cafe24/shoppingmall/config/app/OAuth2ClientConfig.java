package com.cafe24.shoppingmall.config.app;

import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfig {
	
	@Autowired(required=false)
	HttpClient httpClient;
	
	@Bean
	public OAuth2ProtectedResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        
        // 어떤 식으로 토큰을 받을지 설정
        resourceDetails.setAccessTokenUri("http://localhost:8888/oauth/token");
	    resourceDetails.setClientId("shoppingmall");
	    resourceDetails.setId("shoppingmall_api");
	    resourceDetails.setClientSecret("1234");
        resourceDetails.setGrantType("client_credentials");
        resourceDetails.setScope(Arrays.asList("write", "read"));
	    resourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
	    
	    return resourceDetails;
	}
	
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate() {
		HttpClient httpClient = this.httpClient;
		if(httpClient == null) {
			httpClient = HttpClients.createDefault();
		}
		
	    OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
	    
	    // brave
	    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
	    
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        System.out.println("access token: " + restTemplate.getAccessToken());
        
	    return restTemplate;		
	}
}
