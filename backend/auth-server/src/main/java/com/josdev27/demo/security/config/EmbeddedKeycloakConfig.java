package com.josdev27.demo.security.config;

import javax.naming.CompositeName;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class EmbeddedKeycloakConfig {

	@Bean
	ServletRegistrationBean keycloakJaxRsApplication(KeycloakServerProperties keycloakServerProperties,
			DataSource dataSource) throws Exception {

		mockJndiEnvironment(dataSource);
		EmbeddedKeycloakApplication.keycloakServerProperties = keycloakServerProperties;
		ServletRegistrationBean servlet = new ServletRegistrationBean<>(new HttpServlet30Dispatcher());
		servlet.addInitParameter("javax.ws.rs.Application", EmbeddedKeycloakApplication.class.getName());
		servlet.addInitParameter(ResteasyContextParameters.RESTEASY_SERVLET_MAPPING_PREFIX,
				keycloakServerProperties.getContextPath());
		servlet.addInitParameter(ResteasyContextParameters.RESTEASY_USE_CONTAINER_FORM_PARAMS, "true");
		servlet.addUrlMappings(keycloakServerProperties.getContextPath() + "/*");
		servlet.setLoadOnStartup(1);
		servlet.setAsyncSupported(true);
		return servlet;
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
	    source.registerCorsConfiguration("/**", config);

	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	    bean.setOrder(0);
	    return bean;
	}
	
	@Bean
	FilterRegistrationBean keycloakSessionManagement(KeycloakServerProperties keycloakServerProperties) {
		FilterRegistrationBean filter = new FilterRegistrationBean<>();
		filter.setName("Keycloak Session Management");
		filter.setFilter(new EmbeddedKeycloakRequestFilter());
		filter.addUrlPatterns(keycloakServerProperties.getContextPath() + "/*");

		return filter;
	}

	private void mockJndiEnvironment(DataSource dataSource) throws NamingException {
		NamingManager.setInitialContextFactoryBuilder((env) -> (environment) -> new InitialContext() {
			@Override
			public Object lookup(Name name) {
				return lookup(name.toString());
			}

			@Override
			public Object lookup(String name) {
				if ("spring/datasource".equals(name)) {
					return dataSource;
				}
				return null;
			}

			@Override
			public NameParser getNameParser(String name) {
				return CompositeName::new;
			}

			@Override
			public void close() {
			}
		});
	}

}
