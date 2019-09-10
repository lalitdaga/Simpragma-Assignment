package com.simpragma.assignment.backendassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private String username;

  private String password;

  private String email;

  private String address;

  @Builder.Default
  private String role = "ROLE_USER";

}
