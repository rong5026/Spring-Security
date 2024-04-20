package com.example.testsecurity.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.OAuth2ClientDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
				.requestMatchers("/admin").hasRole("ADMIN")
				.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated()  // 로그인한 사람만 접근 가능
			);

		// http
		// 	.formLogin((auth) -> auth.loginPage("/login")
		// 		.loginProcessingUrl("/loginProc")
		// 		.permitAll()
		// 	);

			http
				.httpBasic(Customizer.withDefaults());

		// 배포시에는 csrf 없어야함.
		// http
		// 	.csrf((auth) -> auth.disable());

		// REST API에서는 세션을 사용하지 않음
		http
			.sessionManagement((auth) -> auth
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true));

		// 세션 고정 보안설정
		http
			.sessionManagement((auth) -> auth
				.sessionFixation().changeSessionId());


		return http.build();
	}

}
