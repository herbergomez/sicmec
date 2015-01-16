
package com.uesocc.sicmec.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.uesocc.sicmec.security.CustomAuthenticationProvider;
import com.uesocc.sicmec.security.LoginFailureHandler;

@Configuration
@ComponentScan(basePackages = {"com.uesocc.sicmec.*"})
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAuthenticationProvider authenticationProvider;
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth
//			.inMemoryAuthentication().
//				withUser("user").password("password").roles("USER").and().
//				withUser("admin").password("password2").roles("USER", "ADMIN");
//	}
	    
	@Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.authenticationProvider(authenticationProvider);
        
	}
		
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		  
		http.csrf().disable().headers().disable().authorizeRequests()
				 	.antMatchers("/admin/**").hasAnyRole("Administración")	//Any URL that starts with "/Administration/" must be an administrative user. For our example, that would be the user "admin".
				 	.antMatchers("/control/**").hasAnyRole("Medico")
				 	.antMatchers("/farm/**").hasAnyRole("Admin_Farmacia","Farmacia")
				 		.anyRequest().authenticated()
				 			.and()
				 				.formLogin().loginPage("/login").		
				 				//loginProcessingUrl("/authorize").
				 				permitAll().failureHandler(new LoginFailureHandler())
				 				.and().
				 				rememberMe().//Remember me config....
				 				and().logout()
				 				.permitAll().
				 				and().exceptionHandling().accessDeniedPage("/403");
	}
	
	@Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**");
	  }

	
	
}
