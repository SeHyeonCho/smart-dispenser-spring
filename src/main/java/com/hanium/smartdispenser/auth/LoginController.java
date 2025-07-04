package com.hanium.smartdispenser.auth;

import com.hanium.smartdispenser.auth.dto.*;
import com.hanium.smartdispenser.auth.exception.PasswordMismatchException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> singUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        if (!signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordConfirm())) {
            throw new PasswordMismatchException();
        }
        return ResponseEntity
                .status(201).
                body(new SignUpResponseDto(loginService.singUp(signUpRequestDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(loginService.login(loginRequestDto));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<AccessTokenResponseDto> refresh(@RequestBody AccessTokenRequestDto request) {
        Long userId = refreshTokenService.get(request.getRefreshToken());
        if (refreshTokenService.validate(request.getRefreshToken(), userId)) {

            //예외 수저할것
            throw new RuntimeException("refresh 토큰 오류");
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(userId, request.getUserRole());
        return ResponseEntity.ok(new AccessTokenResponseDto(newAccessToken));
    }
}
