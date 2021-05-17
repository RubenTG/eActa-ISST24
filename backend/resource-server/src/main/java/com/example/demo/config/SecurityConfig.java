package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
				.authorizeRequests()
					.antMatchers("/h2-console/**").permitAll()
			.and()
				.authorizeRequests()
					.anyRequest().authenticated()
			.and()
				.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
	    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
	    jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakGrantedAuthoritiesConverter());
	    return jwtConverter;
	}

}
