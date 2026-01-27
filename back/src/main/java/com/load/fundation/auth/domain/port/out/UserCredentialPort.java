package com.load.fundation.auth.domain.port.out;

import java.util.Map;

public interface UserCredentialPort {
    Map<String, Object> findUserCredentialsByUsername(String username);
}
