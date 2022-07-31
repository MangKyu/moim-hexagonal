package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;

public interface GenerateLoginTokenUseCase {

    LoginToken generate(Member member);
}
