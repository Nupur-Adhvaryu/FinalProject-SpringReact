package ca.sheridancollege.adhvarnu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//remove for production
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.authorizeRequests().antMatchers("/secure/**").hasRole("USER")
				.antMatchers("/", "/js/**", "/css/**", "/images/**", "/**").permitAll()
				
				//remove next line for production
				.antMatchers("/h2-console/**").permitAll()
				
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
}
