package com.standard.controller;

import com.standard.dto.request.LoginRequest;
import com.standard.dto.request.RefreshTokenRequest;
import com.standard.dto.request.RegisterRequest;
import com.standard.dto.response.ApiResponse;
import com.standard.dto.response.LoginResponse;
import com.standard.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        LoginResponse loginResponse = authenticationService.register(registerRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<ApiResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        LoginResponse loginResponse = authenticationService.refreshToken(refreshTokenRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
