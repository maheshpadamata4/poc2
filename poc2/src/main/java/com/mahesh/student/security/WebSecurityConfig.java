package com.mahesh.student.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class WebSecurityConfig extends  WebSecurityConfigurerAdapter {
	 @Autowired
	    private DataSource dataSource;
	     
	    @Bean
	    public UserDetailsService userDetailsService() {
	        return new StudentDetailService();
	    }
	     
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	    }
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .antMatchers("/students").authenticated()
	            .anyRequest().permitAll()
	            .and()
	            .formLogin()
	                .usernameParameter("email")
	                .defaultSuccessUrl("/students")
	                .permitAll()
	            .and()
	            .logout().logoutSuccessUrl("/").permitAll();
	    }
	     
	 
}
