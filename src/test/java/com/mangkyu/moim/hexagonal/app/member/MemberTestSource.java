package com.mangkyu.moim.hexagonal.app.member;

import com.mangkyu.moim.hexagonal.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

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
                .build();
    }

    public static MemberEntity memberEntity() {
        return MemberConverter.INSTANCE.toMemberEntity(member());
    }


}
