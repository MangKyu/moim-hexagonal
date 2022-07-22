package com.mangkyu.moim.hexagonal.member.adapter.persistence;

import com.mangkyu.moim.hexagonal.member.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import com.mangkyu.moim.hexagonal.member.domain.port.out.SaveMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaveMemberPersistenceAdapter implements SaveMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public void save(final Member member) {
        memberRepository.save(MemberConverter.INSTANCE.toMemberEntity(member));
    }

}
