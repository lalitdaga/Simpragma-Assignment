package com.simpragma.assignment.backendassignment.dto.request.user;

import lombok.Data;

@Data
public class UserLogInRequestDto {

  private String username;
  private String password;
}
