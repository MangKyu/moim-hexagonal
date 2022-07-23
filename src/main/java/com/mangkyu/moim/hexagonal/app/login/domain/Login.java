package com.mangkyu.moim.hexagonal.app.login.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
public class Login {

    private String email;
    private String password;

    public boolean passwordMatches(final PasswordEncoder passwordEncoder, final String password) {
        return passwordEncoder.matches(this.password, password);
    }

}
