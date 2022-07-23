package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

public interface SaveOrganizerPort {
    Organizer save(final Organizer organizer);
}
