package com.mangkyu.moim.hexagonal.app.member.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;
    private String name;
    private LocalDate birth;
    private Gender gender;
    private String email;
    private String loginId;
    private String password;

    @Builder.Default
    private Set<MemberRole> roles = new HashSet<>();

    public void encryptPassword(final PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void addRole(final MemberRole role) {
        roles.add(role);
    }
}
