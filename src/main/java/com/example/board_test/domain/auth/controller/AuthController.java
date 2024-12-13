package com.example.board_test.domain.auth.controller;

import com.example.board_test.domain.auth.dto.request.AuthenticationRequest;
import com.example.board_test.domain.auth.dto.response.JsonWebTokenResponse;
import com.example.board_test.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<JsonWebTokenResponse> auth(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.auth(request));
    }

//    @PostMapping //auth
//    public void auth( @RequestBody AuthenticationRequest request)
//    {
//        authService.auth(request);
//    }


}
