package com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.common.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadMemberPersistenceAdapter implements LoadMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Member findByLoginId(final String loginId) {
        final MemberEntity memberEntity = memberRepository.findByLoginId(loginId);
        if (memberEntity == null) {
            return null;
        }

        return MemberConverter.INSTANCE.toMember(memberEntity);
    }

    @Override
    public Optional<Member> findById(final Long id) {
        return memberRepository.findById(id)
                .map(MemberConverter.INSTANCE::toMember);
    }
}
