package com.simpragma.assignment.backendassignment.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationConstants {

  RESPONSE("response"),
  SUCCESS("success"),
  FAILED("failed"),
  USER_CREATED("a new user created"),
  ARTICLE_CREATED("a new article created"),
  DATA("data"),
  AUTH_TOKEN("authToken"),
  MESSAGE("message");

  private String value;
}
