package com.mangkyu.moim.hexagonal.member.application;

import com.mangkyu.moim.hexagonal.member.domain.Member;
import com.mangkyu.moim.hexagonal.member.domain.port.in.MemberUseCase;
import com.mangkyu.moim.hexagonal.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final SaveMemberPort saveMemberPort;

    public void addMember(final Member member) {
        saveMemberPort.save(member);
    }

}
