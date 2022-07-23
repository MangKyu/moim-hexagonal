package com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.participant.converter.ParticipantConverter;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.SaveParticipantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaveParticipantPersistenceAdapter implements SaveParticipantPort {

    private final ParticipantRepository participantRepository;

    @Override
    public Participant save(final Participant participant) {
        final ParticipantEntity savedEntity = participantRepository.save(ParticipantConverter.INSTANCE.toParticipantEntity(participant));
        return ParticipantConverter.INSTANCE.toParticipant(savedEntity);
    }

}
