package it.uniroma3.triathlon.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final String usersQuery = "SELECT username, password, enabled FROM users WHERE username = ?";
	private final String rolesQuery = "SELECT u.username, ruoli.role authority" +
			"FROM utenti u JOIN ruoli_utente ruoli ON u.id = ruoli.utente_id WHERE u.username = ?";

	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;

	@Autowired
	private RedirectLoginSuccessHandler successHandler;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(rolesQuery);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] paginePubbliche = new String[]{"/", "/accesso", "../css/**", "../js/**"};
		http.csrf().disable()
		.formLogin()
		.loginPage("/accedi")
		.permitAll()
		.successHandler(successHandler)
		.and()
		.authorizeRequests()
		.antMatchers(paginePubbliche).permitAll()
		.antMatchers("/utente/**").hasRole("UTENTE")
		.anyRequest().permitAll()
		.and()
		.logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder())
		.usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(rolesQuery);
	}
}
