package com.standard.service;

import com.standard.dto.request.LoginRequest;
import com.standard.dto.request.RefreshTokenRequest;
import com.standard.dto.request.RegisterRequest;
import com.standard.dto.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(RegisterRequest registerRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
