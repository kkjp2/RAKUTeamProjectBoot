package com.example.board_test.domain.auth.service;

import com.example.board_test.domain.auth.dto.request.AuthenticationRequest;
import com.example.board_test.domain.auth.dto.response.JsonWebTokenResponse;
import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.global.jwt.JwtUtil;
import com.example.board_test.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Override
    public JsonWebTokenResponse auth(AuthenticationRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getId(), request.getPwd()
                        )
                );

        MemberDTO memberDTO = ((CustomUserDetails) authentication.getPrincipal()).getMemberDTO();

//        System.out.println(memberDTO.getEmail());
        return JsonWebTokenResponse.builder().accessToken(
                        jwtUtil.generateAccessToken(memberDTO.getId()
                        ))
                .refreshToken(jwtUtil.generateRefreshToken(memberDTO.getId()))
                .build();
    }

    @Override
    public JsonWebTokenResponse refresh(String token) {
        return null;
    }
}
