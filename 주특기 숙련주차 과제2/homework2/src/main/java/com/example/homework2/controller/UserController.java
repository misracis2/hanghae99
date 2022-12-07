package com.example.homework2.controller;

import com.example.homework2.dto.LoginDto;
import com.example.homework2.dto.LoginRequestDto;
import com.example.homework2.dto.SignupRequestDto;
import com.example.homework2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public LoginDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
