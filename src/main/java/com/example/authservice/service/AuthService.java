package com.example.authservice.service;

import com.example.authservice.dto.TokenResponseDto;
import com.example.authservice.exception.GlobalException;
import com.example.authservice.request.LoginRequest;
import com.example.authservice.util.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }


    public TokenResponseDto login(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(authentication))
                    .userDto(userService.getUser(loginRequest.getUsername()))
                    .build();

        }catch (Exception e){
            throw  GlobalException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("user bulunamadı")
                    .build();
        }
    }
}





















