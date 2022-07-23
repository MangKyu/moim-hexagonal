package com.mangkyu.moim.hexagonal.app.member.common.domain;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void 비밀번호암호화() {
        final Member member = member();
        final String beforePassword = member.getPassword();

        member.encryptPassword(new BCryptPasswordEncoder());

        assertThat(beforePassword).isNotEqualTo(member.getPassword());
    }

}