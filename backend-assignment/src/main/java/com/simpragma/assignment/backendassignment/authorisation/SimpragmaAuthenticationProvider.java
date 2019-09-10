package com.simpragma.assignment.backendassignment.authorisation;

import com.simpragma.assignment.backendassignment.dto.autherisation.SimpragmaAuthenticationToken;
import com.simpragma.assignment.backendassignment.dto.autherisation.SimpragmaUser;
import com.simpragma.assignment.backendassignment.dto.autherisation.SimpragmaUserDetails;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SimpragmaAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  private SimpragmaAccessTokenValidator validator;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
  }

  @Override
  protected UserDetails retrieveUser(String username,
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {

    SimpragmaAuthenticationToken simpragmaAuthenticationToken = (SimpragmaAuthenticationToken) usernamePasswordAuthenticationToken;
    String token = simpragmaAuthenticationToken.getToken();

    SimpragmaUser simpragmaUser = validator.validate(token);

    if (Objects.isNull(simpragmaUser)) {
      throw new IllegalStateException("JWT Token is incorrect");
    }

    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(simpragmaUser.getRole());
    return new SimpragmaUserDetails(simpragmaUser.getUserName(), simpragmaUser.getId(),
        token,
        grantedAuthorities);
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return (SimpragmaAuthenticationToken.class.isAssignableFrom(aClass));
  }
}