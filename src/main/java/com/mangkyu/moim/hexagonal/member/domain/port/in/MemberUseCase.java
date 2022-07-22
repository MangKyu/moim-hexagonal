package com.mangkyu.moim.hexagonal.member.domain.port.in;

import com.mangkyu.moim.hexagonal.member.adapter.web.AddMemberRequest;

public interface MemberUseCase {
    void addMember(final AddMemberRequest addMemberRequest);
}
