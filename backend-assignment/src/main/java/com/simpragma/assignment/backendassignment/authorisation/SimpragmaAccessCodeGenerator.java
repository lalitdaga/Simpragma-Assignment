package com.simpragma.assignment.backendassignment.authorisation;

import com.simpragma.assignment.backendassignment.dto.autherisation.SimpragmaUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class SimpragmaAccessCodeGenerator {

  public String generate(SimpragmaUser simpragmaUser) {
    Claims claims = Jwts.claims()
        .setSubject(simpragmaUser.getUserName());
    claims.put("userId", String.valueOf(simpragmaUser.getId()));
    claims.put("role", simpragmaUser.getRole());

    return Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS512, "youtube")
        .compact();
  }
}