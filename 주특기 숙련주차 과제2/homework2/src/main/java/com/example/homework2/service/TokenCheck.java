package com.example.homework2.service;

import com.example.homework2.entity.User;
import com.example.homework2.jwt.JwtUtil;
import com.example.homework2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service //왜 서비스를 넣어줘야 돌아가지??  private final로 선언해줘서 그런가?
@RequiredArgsConstructor
public class TokenCheck {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public User tokenChecking(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            return user;
        }throw new IllegalArgumentException("로그인이 필요합니다");

    }
}