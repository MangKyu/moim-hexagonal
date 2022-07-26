package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.application.LoginTokenService;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;

class GenerateLoginTokenUseCaseTest {

    private GenerateLoginTokenUseCase target;

    @BeforeEach
    void setUp() {
        target = new LoginTokenService();
    }

    @Test
    void 토큰발급() {
        final Member member = member();

        final String result = target.generate(member);

        assertThat(result).isNotNull();
    }

}