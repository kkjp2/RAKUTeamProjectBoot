package rakuproject.raku.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.auth.dto.request.AuthenticationRequest;
import rakuproject.raku.domain.auth.dto.response.JsonWebTokenResponse;
import rakuproject.raku.domain.auth.service.AuthService;

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

