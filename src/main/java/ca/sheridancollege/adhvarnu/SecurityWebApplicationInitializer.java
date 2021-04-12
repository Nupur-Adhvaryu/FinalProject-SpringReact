package ca.sheridancollege.adhvarnu;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import ca.sheridancollege.adhvarnu.security.SecurityConfig;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
		}
}
