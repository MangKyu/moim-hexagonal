package com.mangkyu.moim.hexagonal.member;

import com.mangkyu.moim.hexagonal.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.member.adapter.web.AddMemberRequest;
import com.mangkyu.moim.hexagonal.member.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.member.domain.Member;

public class MemberTestSource {

    public static AddMemberRequest addMemberRequest() {
        return AddMemberRequest.builder()
                .email("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .build();
    }

    public static Member member() {
        return Member.builder()
                .id(1L)
                .email("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .build();
    }

    public static MemberEntity memberEntity() {
        return MemberConverter.INSTANCE.toMemberEntity(member());
    }

    public static AddMemberRequest addMemberRequest(final String email, final String password) {
        return AddMemberRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

}
