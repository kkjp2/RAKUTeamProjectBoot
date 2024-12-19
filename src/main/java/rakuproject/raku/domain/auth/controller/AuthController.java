package rakuproject.raku.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.auth.dto.request.AuthenticationRequest;
import rakuproject.raku.domain.auth.dto.response.JsonWebTokenResponse;
import rakuproject.raku.domain.auth.service.AuthService;
import rakuproject.raku.domain.auth.service.CompanyAuthServiceImpl;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CompanyAuthServiceImpl companyAuthService;


    @PostMapping
    public ResponseEntity<JsonWebTokenResponse> auth(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.auth(request));
    }

    @PostMapping("/companyusers")
    public ResponseEntity<String> companyAuth(
            @RequestBody AuthenticationRequest request
    ){
        String response = companyAuthService.companyAuth(request);
        return ResponseEntity.ok(response);
    }

}

