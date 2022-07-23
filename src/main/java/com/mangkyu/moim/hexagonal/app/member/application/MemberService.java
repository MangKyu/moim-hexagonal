package com.mangkyu.moim.hexagonal.app.member.application;

import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.domain.port.in.MemberUseCase;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final SaveMemberPort saveMemberPort;
    private final LoadMemberPort loadMemberPort;

    public Member addMember(final Member member) {
        // TODO(MinKyu): 커스텀 예외로 수정
        final Member foundMember = loadMemberPort.findByEmail(member.getEmail());
        if (foundMember != null) {
            throw new IllegalArgumentException("중복되는 사용자 이메일입니다.");
        }

        return saveMemberPort.save(member);
    }

}
