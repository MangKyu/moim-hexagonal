package com.mangkyu.moim.hexagonal.app.login.application;

import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.domain.in.LoginUseCase;
import com.mangkyu.moim.hexagonal.app.login.errors.LoginErrorCode;
import com.mangkyu.moim.hexagonal.app.login.errors.LoginException;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(final Login login) {
        final Member member = loadMemberPort.findByEmail(login.getEmail());
        if (member == null) {
            throw new LoginException(LogLevel.INFO, LoginErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        if (!login.passwordMatches(passwordEncoder, member.getPassword())) {
            throw new LoginException(LogLevel.INFO, LoginErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        return login.generateToken();
    }
}
