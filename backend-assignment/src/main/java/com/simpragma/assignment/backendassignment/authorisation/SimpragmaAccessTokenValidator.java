package com.simpragma.assignment.backendassignment.authorisation;

import com.simpragma.assignment.backendassignment.dto.autherisation.SimpragmaUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpragmaAccessTokenValidator {

  private String secret = "youtube";

  public SimpragmaUser validate(String token) {
    SimpragmaUser simpragmaUser = null;
    try {
      Claims body = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();

      simpragmaUser = new SimpragmaUser();

      simpragmaUser.setUserName(body.getSubject());
      simpragmaUser.setId(Long.parseLong((String) body.get("userId")));
      simpragmaUser.setRole((String) body.get("role"));
    } catch (Exception e) {
      log.error("exception occured ",e);
    }

    return simpragmaUser;
  }
}
