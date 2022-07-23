package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.application.LoginService;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.errors.LoginErrorCode;
import com.mangkyu.moim.hexagonal.app.login.errors.LoginException;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.LoadMemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.login;
import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoginUseCaseTest {

    private LoginUseCase target;
    private LoadMemberPort loadMemberPort;
    private PasswordEncoder passwordEncoder;
    private GenerateLoginTokenUseCase generateLoginTokenUseCase;

    @BeforeEach
    void setUp() {
        loadMemberPort = mock(LoadMemberPort.class);
        passwordEncoder = spy(BCryptPasswordEncoder.class);
        generateLoginTokenUseCase = mock(GenerateLoginTokenUseCase.class);
        target = new LoginService(loadMemberPort, passwordEncoder, generateLoginTokenUseCase);
    }

    @Test
    void 로그인실패_존재하지않는이메일() {
        final Login login = login();

        doReturn(null)
                .when(loadMemberPort)
                .findByLoginId(login.getLoginId());

        final LoginException result = assertThrows(
                LoginException.class,
                () -> target.login(login));

        assertThat(result.getErrorCode()).isEqualTo(LoginErrorCode.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    void 로그인실패_잘못된비밀번호() {
        final Member member = member();
        final Login login = Login.builder()
                .loginId(member.getEmail())
                .password("invalid password")
                .build();

        doReturn(member)
                .when(loadMemberPort)
                .findByLoginId(login.getLoginId());

        final LoginException result = assertThrows(
                LoginException.class,
                () -> target.login(login));

        assertThat(result.getErrorCode()).isEqualTo(LoginErrorCode.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    void 로그인성공() {
        final Member member = member();
        final Login login = login();

        doReturn(member)
                .when(loadMemberPort)
                .findByLoginId(login.getLoginId());

        doReturn("token")
                .when(generateLoginTokenUseCase)
                .generate(login.getLoginId());

        final String result = target.login(login);
        assertThat(result).isNotNull();
    }

}