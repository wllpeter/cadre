package com.ddb.xaplan.cadre.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextRepository;

import com.ddb.xaplan.cadre.common.DefaultAccessDeniedHandlerImpl;
import com.ddb.xaplan.cadre.common.RestAuthenticationEntryPoint;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private DefaultAccessDeniedHandlerImpl defaultAccessDeniedHandlerImpl;

	protected String HEADER_NAME = "token";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().formLogin().loginPage("/security/needlogin");

		http.csrf().ignoringAntMatchers("/**", "/mgmt/**");
		http.exceptionHandling().accessDeniedHandler(defaultAccessDeniedHandlerImpl)
				.authenticationEntryPoint(this.authenticationEntryPoint);

		http.authorizeRequests().antMatchers("/**", "/mgmt", "/mgmt/**").permitAll().anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// ... more configuration, e.g. for form login

		http.csrf().disable(); // We don't need CSRF for redis based
								// authentication
		http.securityContext().securityContextRepository(getSecurityContextRepository());
	}

	public SecurityContextRepository getSecurityContextRepository() {
		return new RedisSecurityContextRepository(redisTemplate);
	}
}
