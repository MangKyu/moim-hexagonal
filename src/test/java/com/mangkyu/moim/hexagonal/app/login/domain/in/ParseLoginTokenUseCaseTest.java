package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.application.LoginTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParseLoginTokenUseCaseTest {

    private ParseLoginTokenUseCase target;

    @BeforeEach
    void setUp() {
        target = new LoginTokenService();
    }

    @ValueSource(strings = {"", "token"})
    @ParameterizedTest
    void 토큰파싱_잘못된토큰(final String token) {
        final CommonException result = assertThrows(
                CommonException.class,
                () -> target.parseEmail(token));

        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.UNAUTHORIZED);
    }

    @Test
    void 토큰파싱() {
        final String email = "mangkyu@email.com";

        final String result = target.parseEmail(generateToken(email));

        assertThat(result).isEqualTo(email);
    }

    private String generateToken(final String email) {
        return "Bearer " + new LoginTokenService().generate(email);
    }

}