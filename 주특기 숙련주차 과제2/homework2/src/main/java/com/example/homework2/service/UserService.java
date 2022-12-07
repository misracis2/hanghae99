package com.example.homework2.service;

import com.example.homework2.dto.LoginDto;
import com.example.homework2.dto.LoginRequestDto;
import com.example.homework2.dto.SignupDto;
import com.example.homework2.dto.SignupRequestDto;
import com.example.homework2.entity.User;
import com.example.homework2.entity.UserRoleEnum;
import com.example.homework2.jwt.JwtUtil;
import com.example.homework2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Transactional
    public SignupDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        //아이디 제한 사항 검사
        if(username.length() < 4 ){
            throw new IllegalArgumentException("아이디는 4글자 이상만 가능합니다");
        }else if(username.length() > 10){
            throw new IllegalArgumentException("아이디는 10글자 이하만 가능합니다");
        }
        if(!Pattern.matches("^[a-z0-9]*$", username)){
            throw new IllegalArgumentException("영어 소문자, 숫자만 사용 가능합니다");
        }
        //비밀번호 제한 사항 검사
        if(password.length() < 8 ){
            throw new IllegalArgumentException("비밀번호는 8글자 이상만 가능합니다");
        }else if(password.length() > 15){
            throw new IllegalArgumentException("비밀번호는 15글자 이하만 가능합니다");
        }
        if(!Pattern.matches("^[A-Za-z0-9]*$", password)){
            throw new IllegalArgumentException("영어 대소문자, 숫자만 사용 가능합니다");
        }
        //회원 중복 확인
        Optional<User> check = userRepository.findByUsername(username);
        if (check.isPresent()){
            throw new IllegalArgumentException("중복된 아이디입니다");
        }
        //role 기본값 USER
        UserRoleEnum role = UserRoleEnum.USER;
        //ADMIN_TOKEN 정당 여부 확인
        if(signupRequestDto.isAdmin()){
            if(signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                role = UserRoleEnum.ADMIN;
            }else{
                throw new IllegalArgumentException("관리자 암호가 올바르지 않습니다");
            }
        }
        //user 정보 저장
        User user = new User(username, password, role);
        userRepository.save(user);
        return new SignupDto("회원가입을 완료했습니다", HttpStatus.OK.value());
    }

    public LoginDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        //사용자 회원가입 여부 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return new LoginDto("로그인을 완료했습니다", HttpStatus.OK.value());
    }
}
