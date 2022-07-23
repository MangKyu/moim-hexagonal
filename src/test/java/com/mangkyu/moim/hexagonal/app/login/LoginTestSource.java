package com.mangkyu.moim.hexagonal.app.login;

import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginRequest;

public class LoginTestSource {

    public static LoginRequest loginRequest() {
        return LoginRequest.builder()
                .email("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .build();
    }

    public static LoginRequest loginRequest(final String email, final String password) {
        return LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

}
