package com.simpragma.assignment.backendassignment.config;

import com.simpragma.assignment.backendassignment.authorisation.SimpragmaAuthenticationEntryPoint;
import com.simpragma.assignment.backendassignment.authorisation.SimpragmaAuthenticationProvider;
import com.simpragma.assignment.backendassignment.authorisation.SimpragmaAuthenticatioFilter;
import com.simpragma.assignment.backendassignment.authorisation.SimpragmaAuthorisationSuccessHandler;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SimpragmaSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SimpragmaAuthenticationProvider authenticationProvider;
  @Autowired
  private SimpragmaAuthenticationEntryPoint entryPoint;

  @Bean
  @Override
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(authenticationProvider));
  }

  @Bean
  public SimpragmaAuthenticatioFilter authenticationTokenFilter() {
    SimpragmaAuthenticatioFilter filter = new SimpragmaAuthenticatioFilter();
    filter.setAuthenticationManager(authenticationManager());
    filter.setAuthenticationSuccessHandler(new SimpragmaAuthorisationSuccessHandler());
    return filter;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
        .authorizeRequests().antMatchers("**/article/**").authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(entryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();

  }
}
