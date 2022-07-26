package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.application.LoginTokenService;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginTokenClaims;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParseLoginTokenUseCaseTest {

    private ParseLoginTokenUseCase target;

    @BeforeEach
    void setUp() {
        target = new LoginTokenService();
    }

    @ValueSource(strings = {"", "accessToken"})
    @ParameterizedTest
    void 토큰파싱_잘못된토큰(final String token) {
        final CommonException result = assertThrows(
                CommonException.class,
                () -> target.parseClaims(token));

        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.UNAUTHORIZED);
    }

    @Test
    void 토큰파싱() {
        final Member member = member();

        final LoginTokenClaims claims = target.parseClaims(generateToken(member));

        assertThat(claims.getLoginId()).isEqualTo(member.getLoginId());
    }

    private String generateToken(final Member member) {
        return "Bearer " + new LoginTokenService().generate(member);
    }

}