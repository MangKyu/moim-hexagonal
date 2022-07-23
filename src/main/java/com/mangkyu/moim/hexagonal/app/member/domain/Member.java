package com.mangkyu.moim.hexagonal.app.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;
    private String email;
    private String password;

    public void encryptPassword(final PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
