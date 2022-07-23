package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

public interface LoadOrganizerPort {

    Organizer findByLoginId(final String loginId);

}
