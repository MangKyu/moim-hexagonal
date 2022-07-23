package com.mangkyu.moim.hexagonal.app.member.organizer;

import com.mangkyu.moim.hexagonal.app.member.MemberTestSource;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

public class OrganizerTestSource {

    public static Organizer organizer() {
        return Organizer.builder()
                .id(1L)
                .belongs("주최자")
                .member(MemberTestSource.member())
                .build();
    }

    public static Organizer organizer(final Member member) {
        return Organizer.builder()
                .id(1L)
                .belongs("주최자")
                .member(member)
                .build();
    }

    public static OrganizerEntity organizerEntity() {
        return OrganizerEntity.builder()
                .id(1L)
                .belongs("주최자")
                .member(MemberTestSource.memberEntity())
                .build();
    }

}
