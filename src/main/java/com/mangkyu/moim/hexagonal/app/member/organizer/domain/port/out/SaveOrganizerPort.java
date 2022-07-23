package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

public interface SaveOrganizerPort {
    void save(final Organizer organizer, final MemberEntity memberEntity);
}
