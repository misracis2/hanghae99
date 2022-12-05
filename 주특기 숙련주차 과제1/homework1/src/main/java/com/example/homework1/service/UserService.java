package com.example.homework1.service;

import com.example.homework1.dto.LoginDto;
import com.example.homework1.dto.LoginRequestDto;
import com.example.homework1.dto.SignupDto;
import com.example.homework1.dto.SignupRequestDto;
import com.example.homework1.entity.User;
import com.example.homework1.jwt.JwtUtil;
import com.example.homework1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Transactional
    public SignupDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        //회원 중복 확인
        Optional<User> check = userRepository.findByUsername(username);
        if (check.isPresent()){
            throw new IllegalArgumentException("중복된 아이디입니다");
        }
        User user = new User(username, password);
        userRepository.save(user);
        return new SignupDto("회원가입을 완료했습니다", HttpStatus.OK.value());
    }

    public LoginDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        //사용자확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        return new LoginDto("로그인을 완료했습니다", HttpStatus.OK.value());
    }
}
