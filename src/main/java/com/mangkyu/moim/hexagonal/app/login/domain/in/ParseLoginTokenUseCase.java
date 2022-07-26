package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginTokenClaims;

public interface ParseLoginTokenUseCase {

    LoginTokenClaims parseClaims(String token);

}
