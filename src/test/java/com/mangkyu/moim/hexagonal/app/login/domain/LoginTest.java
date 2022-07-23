package com.mangkyu.moim.hexagonal.app.login.domain;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.login;
import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;

class LoginTest {

    @Test
    void 비밀번호가일치함() {
        final Login login = login();
        final boolean result = login.passwordMatches(new BCryptPasswordEncoder(), member().getPassword());

        assertThat(result).isTrue();
    }

    @Test
    void 비밀번호가일치하지않음() {
        final Login login = login();
        final boolean result = login.passwordMatches(new BCryptPasswordEncoder(), "invalid password");

        assertThat(result).isFalse();
    }

}