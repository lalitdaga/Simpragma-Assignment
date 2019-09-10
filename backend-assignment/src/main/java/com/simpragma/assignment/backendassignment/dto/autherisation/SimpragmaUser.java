package com.simpragma.assignment.backendassignment.dto.autherisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpragmaUser {

  private String userName;
  private long id;
  private String role;
}
