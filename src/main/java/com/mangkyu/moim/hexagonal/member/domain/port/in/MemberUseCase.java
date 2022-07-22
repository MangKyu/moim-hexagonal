package com.mangkyu.moim.hexagonal.member.domain.port.in;

import com.mangkyu.moim.hexagonal.member.domain.Member;

public interface MemberUseCase {
    void addMember(final Member member);
}
