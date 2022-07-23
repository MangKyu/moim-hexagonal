package com.mangkyu.moim.hexagonal.app.login.adapter.web;

import com.mangkyu.moim.hexagonal.app.login.converter.LoginConverter;
import com.mangkyu.moim.hexagonal.app.login.domain.in.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
class LoginWebAdapter {

    private final LoginUseCase loginUseCase;

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid final LoginRequest loginRequest) {
        final String token = loginUseCase.login(LoginConverter.INSTANCE.toLogin(loginRequest));
        return ResponseEntity.ok(Map.of("token", token));
    }

}
