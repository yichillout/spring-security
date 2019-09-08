package com.jasper.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.jasper.security.core.properties.SecurityProperties;
import com.jasper.security.core.validate.code.ValidateCodeFilter;

/**
 * 
 * @author jasper
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private AuthenticationSuccessHandler SecurityDemoAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler SecurityDemoAuthenctiationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(SecurityDemoAuthenctiationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin() 
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
			.successHandler(SecurityDemoAuthenticationSuccessHandler)
			.failureHandler(SecurityDemoAuthenctiationFailureHandler)
			.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/require", 
					securityProperties.getBrowser().getLoginPage(),
					"/code/image").permitAll() 
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable();

		// http.httpBasic().and().authorizeRequests().anyRequest().authenticated();

	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.formLogin()
//			.loginPage("/user-signin.html")
//			.loginProcessingUrl("/authentication/form")
//			.and()
//			.authorizeRequests()
//			.antMatchers("/user-signin.html").permitAll() 
//			.anyRequest()
//			.authenticated()
//			.and()
//			.csrf().disable();
//
//	}

}
