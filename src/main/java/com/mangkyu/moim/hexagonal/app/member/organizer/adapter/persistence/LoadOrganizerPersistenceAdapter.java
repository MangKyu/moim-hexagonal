package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.organizer.converter.OrganizerConverter;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.LoadOrganizerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class LoadOrganizerPersistenceAdapter implements LoadOrganizerPort {

    private final OrganizerRepository organizerRepository;

    @Override
    public Organizer findByLoginId(final String loginId) {
        final OrganizerEntity organizerEntity = organizerRepository.findByMember_LoginId(loginId);
        if (organizerEntity == null) {
            return null;
        }

        return OrganizerConverter.INSTANCE.toOrganizer(organizerEntity);
    }
}
