package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

import java.util.Optional;

public interface LoadOrganizerPort {

    Organizer findByLoginId(final String loginId);

    Optional<Organizer> findById(final Long id);

    Optional<Member> findMemberById(final Long id);
}
