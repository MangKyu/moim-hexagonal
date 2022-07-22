package com.mangkyu.moim.hexagonal.member.application;

import com.mangkyu.moim.hexagonal.member.domain.Member;
import com.mangkyu.moim.hexagonal.member.domain.port.in.MemberUseCase;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements MemberUseCase {

    public void addMember(final Member member) {

    }

}
