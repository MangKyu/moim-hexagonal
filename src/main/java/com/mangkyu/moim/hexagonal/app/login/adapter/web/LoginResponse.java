package com.mangkyu.moim.hexagonal.app.login.adapter.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class LoginResponse {

    private final String accessToken;
    private final String refreshToken;
    private final Long expiresIn;
    private final String tokenType;
}
