package com.weather;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;



@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIEN_ID = "weatherApiClient";
    static final String GRANT_TYPE = "password";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String RESOURCES_IDS = "weahtherResourceId";
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;
	
	
	private int expiration = 3600;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
		//configurer.authenticationManager(authenticationManager);
		//configurer.userDetailsService(userDetailsService);
		
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        configurer.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
	
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/*clients.inMemory().withClient("weather-ui").secret("secret").accessTokenValiditySeconds(expiration)
		.scopes("read", "write").authorizedGrantTypes("password", "refresh_token").resourceIds("resource");*/
		
		clients
        .inMemory()
        .withClient(CLIEN_ID)
        .secret(SecurityConfig.SIGNING_KEY)
        .authorizedGrantTypes(GRANT_TYPE)
        .scopes(SCOPE_READ, SCOPE_WRITE)
        .resourceIds(RESOURCES_IDS);
		
		
	}
}
