package com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;

import java.util.Optional;

public interface LoadParticipantPort {

    Participant findByLoginId(final String loginId);

    Optional<Participant> findById(final Long id);

    Optional<Member> findMemberById(final Long id);
}
