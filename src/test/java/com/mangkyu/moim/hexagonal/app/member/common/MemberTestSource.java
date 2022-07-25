package com.mangkyu.moim.hexagonal.app.member.common;

import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.common.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MemberTestSource {


    public static Member member() {
        return Member.builder()
                .id(1L)
                .name("mangkyu")
                .birth(LocalDate.of(1994, 12, 26))
                .gender(Gender.MALE)
                .email("mangkyu@naver.com")
                .loginId("mangkyu")
                .password(new BCryptPasswordEncoder().encode("dkssudgktpdy123!@#"))
                .roles(new HashSet<>())
                .build();
    }

    public static MemberEntity memberEntity() {
        return MemberConverter.INSTANCE.toMemberEntity(member());
    }


}
