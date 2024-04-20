package com.example.udemysecurity.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
			authorize ->
				authorize
				.requestMatchers("/myAccount/**", "/myBalance", "/myLoans", "/myCards").authenticated()
				.requestMatchers("/notices", "/contact", "/login").permitAll()
			)
			.formLogin(formLogin -> formLogin.permitAll()
				// formLogin -> formLogin
				// .loginPage("/login")
				// .permitAll()
			);
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	// @Bean
	// public InMemoryUserDetailsManager userDetailsManager() {
	//
	// 	// // 첫번째 방법. 사용 권장하지 않음
	// 	// UserDetails admin = User.withDefaultPasswordEncoder()
	// 	// 	.username("admin")
	// 	// 	.password("1234")
	// 	// 	.authorities("admin")
	// 	// 	.build();
	// 	//
	// 	// UserDetails user = User.withDefaultPasswordEncoder()
	// 	// 	.username("user")
	// 	// 	.password("1234")
	// 	// 	.authorities("read")
	// 	// 	.build();
	//
	// 	// 두번째 방법.
	// 	UserDetails admin = User.withUsername("admin")
	// 		.password("1234")
	// 		.authorities("admin")
	// 		.build();
	//
	// 	UserDetails user = User.withUsername("user")
	// 		.password("1234")
	// 		.authorities("read")
	// 		.build();
	//
	//
	// 	return new InMemoryUserDetailsManager(admin, user);
	// }


}
