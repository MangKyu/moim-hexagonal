package com.mangkyu.moim.hexagonal.member;

import com.mangkyu.moim.hexagonal.member.adapter.web.AddMemberRequest;

public class MemberTestSource {

    public static AddMemberRequest addMemberRequest() {
        return AddMemberRequest.builder()
                .email("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .build();
    }

    public static AddMemberRequest addMemberRequest(final String email, final String password) {
        return AddMemberRequest.builder()
                .email(email)
                .password(password)
                .build();
    }

}