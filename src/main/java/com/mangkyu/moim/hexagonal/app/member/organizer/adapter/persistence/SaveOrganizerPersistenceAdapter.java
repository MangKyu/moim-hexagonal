package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerRepository;
import com.mangkyu.moim.hexagonal.app.member.organizer.converter.OrganizerConverter;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.SaveOrganizerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaveOrganizerPersistenceAdapter implements SaveOrganizerPort {

    private final OrganizerRepository organizerRepository;

    @Override
    public Organizer save(final Organizer organizer) {
        final OrganizerEntity entity = OrganizerConverter.INSTANCE.toOrganizerEntity(organizer);
        final OrganizerEntity savedEntity = organizerRepository.save(entity);
        return OrganizerConverter.INSTANCE.toOrganizer(savedEntity);
    }
}
