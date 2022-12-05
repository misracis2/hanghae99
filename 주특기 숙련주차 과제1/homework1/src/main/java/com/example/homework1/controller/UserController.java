package com.example.homework1.controller;

import com.example.homework1.dto.LoginDto;
import com.example.homework1.dto.LoginRequestDto;
import com.example.homework1.dto.SignupDto;
import com.example.homework1.dto.SignupRequestDto;
import com.example.homework1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public SignupDto signup(@RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public LoginDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
