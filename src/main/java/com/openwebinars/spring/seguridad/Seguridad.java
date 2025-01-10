package com.openwebinars.spring.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Seguridad {

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	protected BCryptPasswordEncoder getPassWordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(getPassWordEncoder());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/registro/**")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/registro/**")).permitAll()
						.requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()
						.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login")
						.permitAll()
						.defaultSuccessUrl("/",true)
						.failureUrl("/login?error=true"))
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout=true")
						.permitAll());

		http.csrf(csrf -> csrf.disable());
		http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

		return http.build();
	}

}
