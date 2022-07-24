package com.mangkyu.moim.hexagonal.app.member.participant.domain.port.in;

import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;

public interface ParticipantUseCase {

    Participant addParticipant(final Participant participant);

    Participant modifyParticipant(final Long id, final Participant participant);
}
