package com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.participant.converter.ParticipantConverter;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.LoadParticipantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadParticipantPersistenceAdapter implements LoadParticipantPort {

    private final ParticipantRepository participantRepository;

    @Override
    public Participant findByLoginId(final String loginId) {
        final ParticipantEntity participantEntity = participantRepository.findByMember_LoginId(loginId);
        if (participantEntity == null) {
            return null;
        }

        return ParticipantConverter.INSTANCE.toParticipant(participantEntity);
    }

    @Override
    public Optional<Participant> findById(final Long id) {
        return participantRepository.findByMember_Id(id)
                .map(ParticipantConverter.INSTANCE::toParticipant);
    }

}
