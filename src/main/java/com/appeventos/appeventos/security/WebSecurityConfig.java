package com.appeventos.appeventos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/")
			.permitAll()
		.antMatchers(HttpMethod.GET, "/cadastrar-novo-usuario").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/cadastrar-novo-usuario").hasRole("ADMIN")
		.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login-error")
			.permitAll()
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		// sites uteis:
		// https://www.baeldung.com/spring-security-login
		// https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#simple-conditionals-if-and-unless
		// https://juhahinkula.github.io/2016-07-16-crudboot-manytomany/
		
		// sobre iterStat:
		// https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#keeping-iteration-status
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**", "/style/**");
	}

}
