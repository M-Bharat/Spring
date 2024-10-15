package com.example.sessiontimeoutdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 @Bean
	    public static PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	            .withUser("user")
	            .password(passwordEncoder().encode("password"))
	            .roles("USER");
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
	                .antMatchers("/", "/login", "/sessionTimeoutUrl", "/error").permitAll()
	                .anyRequest().authenticated()
	            .and()
	            .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/dashboard")
	                .permitAll()
	            .and()
	            .logout()
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout")
	                .invalidateHttpSession(true)
	                .deleteCookies("JSESSIONID")
	            .and()
	            .sessionManagement()
	                .invalidSessionUrl("/sessionTimeoutUrl")
	                .maximumSessions(1)
	                .expiredUrl("/login?expired");

	        return http.build();
	    }
}