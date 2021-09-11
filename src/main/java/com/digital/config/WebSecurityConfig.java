package com.digital.config;

import com.digital.handler.AuthenticationSuccessHandler;
import com.digital.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Default Configuration for Web Security Module
 *
 * @author babacoul
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImplementation userDetailsServiceImplementation;

  private final AuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  public WebSecurityConfig(UserDetailsServiceImplementation userDetailsServiceImplementation,
      AuthenticationSuccessHandler authenticationSuccessHandler) {
    this.userDetailsServiceImplementation = userDetailsServiceImplementation;
    this.authenticationSuccessHandler = authenticationSuccessHandler;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest()
            .permitAll();
  }

  @Override public void configure(WebSecurity web) throws Exception {

  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsServiceImplementation)
        .passwordEncoder(new BCryptPasswordEncoder());
  }
}
