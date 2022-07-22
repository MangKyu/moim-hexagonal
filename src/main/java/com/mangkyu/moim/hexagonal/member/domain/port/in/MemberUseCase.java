package com.mangkyu.moim.hexagonal.member.domain.port.in;

import com.mangkyu.moim.hexagonal.member.domain.Member;

public interface MemberUseCase {
    Member addMember(final Member member);
}
