package com.load.fundation.auth.infrastructure.api;

import com.load.fundation.auth.application.dto.AuthRequest;
import com.load.fundation.auth.application.dto.AuthResponse;
import com.load.fundation.auth.domain.port.in.AuthUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authUseCase.authenticate(authRequest);
    }
}
