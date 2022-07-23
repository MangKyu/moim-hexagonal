package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.in;

import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

public interface OrganizerUseCase {

    Organizer addOrganizer(final Organizer organizer);
}
