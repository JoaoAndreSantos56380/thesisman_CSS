package pt.ul.fc.css.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/consultant/register", "/", "/api/**")
						.permitAll()
						.anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/user/login")
						.defaultSuccessUrl("/user/home", true)
						.permitAll())
				.logout((logout) -> logout.permitAll());

		return http.build();
	}

	// Ensure you have the password encoder bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
