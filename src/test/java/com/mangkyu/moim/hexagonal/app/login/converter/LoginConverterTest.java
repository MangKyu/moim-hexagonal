package com.mangkyu.moim.hexagonal.app.login.converter;

import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginRequest;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.loginRequest;
import static org.assertj.core.api.Assertions.assertThat;

class LoginConverterTest {

    @Test
    void LoginRequest에서Login으로변환() {
        final LoginRequest request = loginRequest();

        final Login login = LoginConverter.INSTANCE.toLogin(request);

        assertThat(login.getEmail()).isEqualTo(request.getEmail());
        assertThat(login.getPassword()).isEqualTo(request.getPassword());
    }

}