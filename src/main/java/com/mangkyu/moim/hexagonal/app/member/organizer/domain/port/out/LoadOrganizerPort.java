package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

import java.util.Optional;

public interface LoadOrganizerPort {

    Organizer findByLoginId(final String loginId);

    Optional<Organizer> findByMember_Id(final Long id);
}
