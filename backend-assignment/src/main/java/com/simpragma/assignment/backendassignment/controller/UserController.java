package com.simpragma.assignment.backendassignment.controller;

import com.simpragma.assignment.backendassignment.dto.SimpleResponseDto;
import com.simpragma.assignment.backendassignment.dto.request.user.UserLogInRequestDto;
import com.simpragma.assignment.backendassignment.dto.request.user.UserRegisterRequestDto;
import com.simpragma.assignment.backendassignment.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1")
@Api(tags = {"User Controller"})
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "log in user", response = SimpleResponseDto.class)
  ResponseEntity login(@RequestBody UserLogInRequestDto userLogInRequestDto) {

    return ResponseEntity.ok(userService.loginUser(userLogInRequestDto));
  }

  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "register a  user", response = SimpleResponseDto.class)
  ResponseEntity register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {

    return new ResponseEntity<>(userService.registerUser(userRegisterRequestDto),
        HttpStatus.CREATED);
  }
}
