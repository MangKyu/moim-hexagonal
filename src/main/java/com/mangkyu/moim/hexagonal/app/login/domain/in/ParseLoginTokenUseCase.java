package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginMember;

public interface ParseLoginTokenUseCase {

    LoginMember parseClaims(String token);

}
