package com.simpragma.assignment.backendassignment.service.impl;

import com.simpragma.assignment.backendassignment.constants.ApplicationConstants;
import com.simpragma.assignment.backendassignment.dto.autherisation.SimpragmaUser;
import com.simpragma.assignment.backendassignment.dto.SimpleResponseDto;
import com.simpragma.assignment.backendassignment.dto.request.user.UserLogInRequestDto;
import com.simpragma.assignment.backendassignment.dto.request.user.UserRegisterRequestDto;
import com.simpragma.assignment.backendassignment.entity.User;
import com.simpragma.assignment.backendassignment.repository.UserRepository;
import com.simpragma.assignment.backendassignment.authorisation.SimpragmaAccessCodeGenerator;
import com.simpragma.assignment.backendassignment.service.UserService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SimpragmaAccessCodeGenerator simpragmaAccessCodeGenerator;

  @Override
  public SimpleResponseDto loginUser(UserLogInRequestDto userLogInRequestDto) {
    SimpleResponseDto loginResponseDto = new SimpleResponseDto();

    User user = userRepository.findByUsernameAndPassword(userLogInRequestDto.getUsername(),
        userLogInRequestDto.getPassword());

    if (Objects.nonNull(user)) {
      String authToken = simpragmaAccessCodeGenerator
          .generate(SimpragmaUser.builder().id(user.getId()).userName(user.getUsername()).role(user.getRole()).build());
      loginResponseDto
          .put(ApplicationConstants.MESSAGE.getValue(), ApplicationConstants.SUCCESS.getValue());
      loginResponseDto
          .put(ApplicationConstants.AUTH_TOKEN.getValue(), authToken);
    } else {
      loginResponseDto
          .put(ApplicationConstants.MESSAGE.getValue(), ApplicationConstants.FAILED.getValue());
    }
    return loginResponseDto;
  }

  @Override
  public SimpleResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {

    SimpleResponseDto registerResponseDto = new SimpleResponseDto();

    User user = userRepository.save(User.builder()
        .email(userRegisterRequestDto.getEmail())
        .username(userRegisterRequestDto.getUsername())
        .password(userRegisterRequestDto.getPassword())
        .address(userRegisterRequestDto.getAddress())
        .role(userRegisterRequestDto.getRole())
        .build());

    if (Objects.nonNull(user)) {
      registerResponseDto.put(ApplicationConstants.MESSAGE.getValue(),
          ApplicationConstants.USER_CREATED.getValue());
    } else {
      registerResponseDto
          .put(ApplicationConstants.MESSAGE.getValue(), ApplicationConstants.FAILED.getValue());
    }
    return registerResponseDto;
  }
}
