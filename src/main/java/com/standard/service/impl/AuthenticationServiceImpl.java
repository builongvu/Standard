package com.standard.service.impl;

import com.standard.constant.Constants;
import com.standard.constant.ErrorEnum;
import com.standard.dto.request.LoginRequest;
import com.standard.dto.request.RefreshTokenRequest;
import com.standard.dto.request.RegisterRequest;
import com.standard.dto.response.LoginResponse;
import com.standard.entity.RefreshToken;
import com.standard.entity.Role;
import com.standard.entity.User;
import com.standard.exception.ApplicationException;
import com.standard.repository.RefreshTokenRepository;
import com.standard.repository.RoleRepository;
import com.standard.repository.UserRepository;
import com.standard.security.UserDetailsImpl;
import com.standard.security.jwt.JwtProvider;
import com.standard.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetailsImpl, false);
        String refreshToken = jwtProvider.generateToken(userDetailsImpl, true);

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .token(refreshToken)
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiration))
                .user(userDetailsImpl.getUser())
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

        return LoginResponse.builder()
                .tokenType(Constants.TOKEN_TYPE)
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ApplicationException(ErrorEnum.DUPLICATE, "Username");
        }

        Role role = roleRepository.findByName(Constants.ROLE_USER).orElseThrow(
                () -> new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "Role", "name", Constants.ROLE_USER)
        );
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
        String token = jwtProvider.generateToken(userDetailsImpl, false);
        String refreshToken = jwtProvider.generateToken(userDetailsImpl, true);

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .token(refreshToken)
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiration))
                .user(userDetailsImpl.getUser())
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

        return LoginResponse.builder()
                .tokenType(Constants.TOKEN_TYPE)
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshTokenEntity = refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken())
                .orElseThrow(() -> new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND,
                        "RefreshToken", "token", refreshTokenRequest.getRefreshToken()));

        if (refreshTokenEntity.getExpiryDate().isAfter(Instant.now())) {
            UserDetailsImpl userDetailsImpl = new UserDetailsImpl(refreshTokenEntity.getUser());
            String token = jwtProvider.generateToken(userDetailsImpl, false);
            return LoginResponse.builder()
                    .tokenType(Constants.TOKEN_TYPE)
                    .token(token)
                    .build();
        }

        refreshTokenRepository.delete(refreshTokenEntity);
        throw new ApplicationException(ErrorEnum.TOKEN_EXPIRED);
    }

}
