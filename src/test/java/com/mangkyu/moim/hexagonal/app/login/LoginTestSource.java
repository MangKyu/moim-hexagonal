package com.mangkyu.moim.hexagonal.app.login;

import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginRequest;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;

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

}
