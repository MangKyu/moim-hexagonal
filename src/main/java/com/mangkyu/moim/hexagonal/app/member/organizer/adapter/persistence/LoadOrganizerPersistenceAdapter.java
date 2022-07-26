package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberRepository;
import com.mangkyu.moim.hexagonal.app.member.common.converter.MemberConverter;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.organizer.converter.OrganizerConverter;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.LoadOrganizerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadOrganizerPersistenceAdapter implements LoadOrganizerPort {

    private final OrganizerRepository organizerRepository;
    private final MemberRepository memberRepository;

    @Override
    public Organizer findByLoginId(final String loginId) {
        final OrganizerEntity organizerEntity = organizerRepository.findByMember_LoginId(loginId);
        if (organizerEntity == null) {
            return null;
        }

        return OrganizerConverter.INSTANCE.toOrganizer(organizerEntity);
    }

    @Override
    public Optional<Organizer> findById(final Long id) {
        return organizerRepository.findByMember_Id(id)
                .map(OrganizerConverter.INSTANCE::toOrganizer);
    }

    @Override
    public Optional<Member> findMemberById(final Long id) {
        return memberRepository.findById(id)
                .map(MemberConverter.INSTANCE::toMember);
    }

}
