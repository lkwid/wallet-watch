package lkwid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lkwid.service.AccountService;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String REALM = "USER_REALM";
	
	@Autowired
	private AccountService accountService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password("admin").roles("USER", "ADMIN");
		auth.userDetailsService(accountService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.headers().frameOptions().disable()
		.and()
		.csrf().disable()
		.httpBasic()
		.realmName(REALM)
		.and()
		.authorizeRequests()		
		.antMatchers("/api/**").hasRole("USER")
		.anyRequest().authenticated();		
	}

}
