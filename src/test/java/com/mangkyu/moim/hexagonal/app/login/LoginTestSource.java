package com.mangkyu.moim.hexagonal.app.login;

import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginRequest;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginTokenClaims;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;

import java.util.Set;

public class LoginTestSource {

    public static Login login() {
        return Login.builder()
                .loginId("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .build();
    }

    public static LoginRequest loginRequest() {
        return LoginRequest.builder()
                .loginId("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .build();
    }

    public static LoginRequest loginRequest(final String email, final String password) {
        return LoginRequest.builder()
                .loginId(email)
                .password(password)
                .build();
    }

    public static LoginTokenClaims loginTokenClaims() {
        return LoginTokenClaims.builder()
                .id(1L)
                .gender(Gender.MALE)
                .email("mangkyu@naver.com")
                .loginId("mangkyu1226")
                .roles(Set.of(MemberRole.ROLE_ORGANIZER))
                .build();
    }

}
