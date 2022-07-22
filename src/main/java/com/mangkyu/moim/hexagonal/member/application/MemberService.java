package com.mangkyu.moim.hexagonal.member.application;

import com.mangkyu.moim.hexagonal.member.adapter.web.AddMemberRequest;
import com.mangkyu.moim.hexagonal.member.domain.port.in.MemberUseCase;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements MemberUseCase {

    public void addMember(final AddMemberRequest addMemberRequest) {

    }

}
