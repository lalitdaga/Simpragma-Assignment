package com.simpragma.assignment.backendassignment.authorisation;

import com.simpragma.assignment.backendassignment.dto.autherisation.SimpragmaAuthenticationToken;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 * AuthenticationFilter, this filter will filter all the requests and match the url
 * with the given pattern in the constructor, if the pattern matches then it will ask for autherisation,
 * otherwise it will pass normally, in our case all the apis related to article will be accessible if the user is
 * authorised
 */

@Slf4j
public class SimpragmaAuthenticatioFilter extends AbstractAuthenticationProcessingFilter {

  public SimpragmaAuthenticatioFilter() {
    super("/article/**");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {

    String authenticationToken = httpServletRequest.getHeader("Authorisation");

    if (authenticationToken == null) {
      throw new IllegalStateException("JWT Token is missing");
    }

    SimpragmaAuthenticationToken token = new SimpragmaAuthenticationToken(authenticationToken);
    return getAuthenticationManager().authenticate(token);
  }


  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult);
    chain.doFilter(request, response);
  }
}
