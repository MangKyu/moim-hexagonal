package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.application.LoginTokenService;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.login;
import static org.assertj.core.api.Assertions.assertThat;

class GenerateLoginTokenUseCaseTest {

    private GenerateLoginTokenUseCase target;

    @BeforeEach
    void setUp() {
        target = new LoginTokenService();
    }

    @Test
    void 토큰발급() {
        final Login login = login();

        final String result = target.generate(login.getEmail());

        assertThat(result).isNotNull();
    }

}