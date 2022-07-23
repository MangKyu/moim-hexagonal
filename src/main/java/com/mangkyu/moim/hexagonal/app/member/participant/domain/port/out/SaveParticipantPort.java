package com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;

public interface SaveParticipantPort {
    Participant save(final Participant participant);
}
