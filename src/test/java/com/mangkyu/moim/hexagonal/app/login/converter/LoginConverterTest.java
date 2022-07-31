package com.mangkyu.moim.hexagonal.app.login.converter;

import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginRequest;
import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginResponse;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.loginRequest;
import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.loginToken;
import static org.assertj.core.api.Assertions.assertThat;

class LoginConverterTest {

    @Test
    void LoginRequest에서Login으로변환() {
        final LoginRequest request = loginRequest();

        final Login login = LoginConverter.INSTANCE.toLogin(request);

        assertThat(login.getLoginId()).isEqualTo(request.getLoginId());
        assertThat(login.getPassword()).isEqualTo(request.getPassword());
    }

    @Test
    void LoginToken에서LoginResponse로변환() {
        final LoginToken request = loginToken();

        final LoginResponse result = LoginConverter.INSTANCE.toLoginResponse(request);

        assertThat(result.getAccessToken()).isEqualTo(request.getAccessToken());
        assertThat(result.getRefreshToken()).isEqualTo(request.getRefreshToken());
        assertThat(result.getExpiresIn()).isEqualTo(request.getExpiresIn());
        assertThat(result.getTokenType()).isEqualTo(request.getTokenType());
    }

}