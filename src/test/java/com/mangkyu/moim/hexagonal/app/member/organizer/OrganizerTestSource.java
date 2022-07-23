package com.mangkyu.moim.hexagonal.app.member.organizer;

import com.mangkyu.moim.hexagonal.app.member.MemberTestSource;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

public class OrganizerTestSource {

    public static Organizer organizer() {
        return Organizer.builder()
                .id(1L)
                .belongs("주최자")
                .member(MemberTestSource.member())
                .build();
    }

}
