package com.mangkyu.moim.hexagonal.app.member.organizer;

import com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.ModifyOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;

import java.time.LocalDate;

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

    public static OrganizerEntity organizerEntity(final Long id) {
        return OrganizerEntity.builder()
                .id(id)
                .belongs("주최자")
                .member(MemberTestSource.memberEntity())
                .build();
    }

    public static ModifyOrganizerRequest modifyOrganizerRequest() {
        return ModifyOrganizerRequest.builder()
                .email("mangkyu@naver.com")
                .belongs("newBelongs")
                .build();
    }

    public static AddOrganizerRequest addOrganizerRequest() {
        return AddOrganizerRequest.builder()
                .name("mangkyu")
                .birth(LocalDate.of(1994, 12, 26))
                .gender(Gender.MALE)
                .loginId("mangkyu")
                .email("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .belongs("belongs")
                .build();
    }

    public static AddOrganizerRequest addOrganizerRequest(final String email, final String password) {
        return AddOrganizerRequest.builder()
                .name("mangkyu")
                .birth(LocalDate.now())
                .gender(Gender.MALE)
                .loginId("mangkyu")
                .email(email)
                .password(password)
                .belongs("belongs")
                .build();
    }
}
