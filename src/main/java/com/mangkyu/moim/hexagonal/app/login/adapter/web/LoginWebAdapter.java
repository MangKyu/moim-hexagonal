package com.mangkyu.moim.hexagonal.app.login.adapter.web;

import com.mangkyu.moim.hexagonal.app.login.converter.LoginConverter;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import com.mangkyu.moim.hexagonal.app.login.domain.in.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class LoginWebAdapter {

    private final LoginUseCase loginUseCase;

    @PostMapping("/api/login")
    public ResponseEntity<LoginToken> login(@RequestBody @Valid final LoginRequest loginRequest) {
        final LoginToken loginToken = loginUseCase.login(LoginConverter.INSTANCE.toLogin(loginRequest));
        return ResponseEntity.ok(loginToken);
    }

}
