package com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.common.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaveMemberPersistenceAdapter implements SaveMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Member save(final Member member) {
        final MemberEntity savedMemberEntity = memberRepository.save(MemberConverter.INSTANCE.toMemberEntity(member));
        return MemberConverter.INSTANCE.toMember(savedMemberEntity);
    }
}
