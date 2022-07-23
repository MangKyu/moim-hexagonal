package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.application.LoginService;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.errors.LoginErrorCode;
import com.mangkyu.moim.hexagonal.app.login.errors.LoginException;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.LoadMemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.login;
import static com.mangkyu.moim.hexagonal.app.member.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoginUseCaseTest {

    private LoginUseCase target;
    private LoadMemberPort loadMemberPort;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        loadMemberPort = mock(LoadMemberPort.class);
        passwordEncoder = spy(BCryptPasswordEncoder.class);
        target = new LoginService(loadMemberPort, passwordEncoder);
    }

    @Test
    void 로그인실패_존재하지않는이메일() {
        final Login login = login();

        doReturn(null)
                .when(loadMemberPort)
                .findByEmail(login.getEmail());

        final LoginException result = assertThrows(
                LoginException.class,
                () -> target.login(login));

        assertThat(result.getErrorCode()).isEqualTo(LoginErrorCode.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    void 로그인실패_잘못된비밀번호() {
        final Member member = member();
        final Login login = Login.builder()
                .email(member.getEmail())
                .password("invalid password")
                .build();

        doReturn(member)
                .when(loadMemberPort)
                .findByEmail(login.getEmail());

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
                .findByEmail(login.getEmail());

        final String result = target.login(login);
        assertThat(result).isEqualTo("login success");
    }

}