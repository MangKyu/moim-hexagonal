package com.mangkyu.moim.hexagonal.app.member.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.domain.port.out.SaveMemberPort;
import com.mangkyu.moim.hexagonal.app.member.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
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
        final MemberEntity memberEntity = memberRepository.save(MemberConverter.INSTANCE.toMemberEntity(member));
        return MemberConverter.INSTANCE.toMember(memberEntity);
    }

}
