package com.rmportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.rmportal.security.AuthEntryPoint;
import com.rmportal.security.AuthFailureHandler;
import com.rmportal.security.AuthService;
import com.rmportal.security.AuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthFailureHandler authFailureHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		    auth.userDetailsService(authService);//.passwordEncoder(new BCryptPasswordEncoder(workload));
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		http.authorizeRequests().antMatchers("/api/v1/details","/api/v1/details/**").permitAll();
		http.exceptionHandling().authenticationEntryPoint(authEntryPoint);
        http.formLogin().successHandler(authSuccessHandler);
        http.formLogin().failureHandler(authFailureHandler);
        http.formLogin().usernameParameter("username").passwordParameter("password");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
	
	
}
