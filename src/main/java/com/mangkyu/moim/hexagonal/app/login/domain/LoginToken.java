package com.mangkyu.moim.hexagonal.app.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class LoginToken {

    private final String accessToken;
    private final String refreshToken;
    private final Long expiresIn;
    private final String tokenType;

}
