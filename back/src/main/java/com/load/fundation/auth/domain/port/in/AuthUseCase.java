package com.load.fundation.auth.domain.port.in;

import com.load.fundation.auth.application.dto.AuthRequest;
import com.load.fundation.auth.application.dto.AuthResponse;

public interface AuthUseCase {
    AuthResponse authenticate(AuthRequest authRequest);
}
