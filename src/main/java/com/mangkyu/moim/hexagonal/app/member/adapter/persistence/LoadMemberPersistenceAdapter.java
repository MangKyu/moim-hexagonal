package com.mangkyu.moim.hexagonal.app.member.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.domain.port.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadMemberPersistenceAdapter implements LoadMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Member findByEmail(final String email) {
        final MemberEntity memberEntity = memberRepository.findByEmail(email);
        if (memberEntity == null) {
            return null;
        }

        return MemberConverter.INSTANCE.toMember(memberEntity);
    }
}
