package com.simpragma.assignment.backendassignment.service;

import com.simpragma.assignment.backendassignment.dto.SimpleResponseDto;
import com.simpragma.assignment.backendassignment.dto.request.user.UserLogInRequestDto;
import com.simpragma.assignment.backendassignment.dto.request.user.UserRegisterRequestDto;

public interface UserService {

  public SimpleResponseDto loginUser(UserLogInRequestDto userLogInRequestDto);

  public SimpleResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
}
