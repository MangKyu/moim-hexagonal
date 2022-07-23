package com.mangkyu.moim.hexagonal.app.member.domain.port.in;

import com.mangkyu.moim.hexagonal.app.member.domain.Member;

public interface MemberUseCase {
    Member addMember(final Member member);
}
