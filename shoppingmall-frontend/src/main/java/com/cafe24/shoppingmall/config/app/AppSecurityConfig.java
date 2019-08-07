package com.cafe24.shoppingmall.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cafe24.shoppingmall.security.CustomPasswordEncoder;
import com.cafe24.shoppingmall.security.CustomUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/assets/**")
			.antMatchers("/favicon.ico");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// AuthorizedURL(Basic ACL)
		http
			// HTML 에디터 적용하기 위함
			.headers()
			.frameOptions()
			.disable()
		
			.and()
			.authorizeRequests()
			// 인증이 되었을 경우
			.antMatchers("/user/modify/**", "/user/logout", "/user/view/**", "/user/orders/**").authenticated()
			// ADMIN 권한
			.antMatchers("/admin", "/admin/**").hasAuthority("ROLE_ADMIN")

			// 모두 허용
			.anyRequest().permitAll()

			// FormLoginConfigurer
			.and()
			.formLogin()
			.loginPage("/user/login")
			.loginProcessingUrl("/user/auth")
			.failureUrl("/user/login?result=fail")
			
			.successHandler(authenticationSuccessHandler())
			
			.usernameParameter("username")
			.passwordParameter("password")

			// LogoutConfigurer
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)

			// ExceptionHandlingConfigurer
			.and()
			.exceptionHandling()
			.accessDeniedPage("/views/error/404.jsp")

			// RememberMeConfigurer
			.and()
			.rememberMe()
			.key("shoppingmall")
			.rememberMeParameter("remember-me")

			// CSRFConfigurer(Temporary for Test)
			.and()
			.csrf()
			.disable();

			//.and()
			//.addFilterBefore(cafe24AuthenticationProcessingFilter(), BasicAuthenticationFilter.class);
	}

	// 사용자 세부 서비스를 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		auth
			.userDetailsService(userDetailsService)
			.and()
			.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomUrlAuthenticationSuccessHandler();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(customPasswordEncoder());
		authProvider.setUserDetailsService(userDetailsService);

		return authProvider;
	}
	
	@Bean
	public PasswordEncoder customPasswordEncoder() {
		return new CustomPasswordEncoder();
	}
}
