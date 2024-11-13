package com.example.board_test.domain.auth.service;


import com.example.board_test.domain.auth.dto.request.AuthenticationRequest;
import com.example.board_test.domain.auth.dto.response.JsonWebTokenResponse;

public interface AuthService {

    JsonWebTokenResponse auth(AuthenticationRequest request);
    JsonWebTokenResponse refresh( String token);

}
