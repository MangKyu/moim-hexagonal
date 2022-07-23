package com.mangkyu.moim.hexagonal.app.member.participant;

import com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.ModifyOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantEntity;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;

public class ParticipantTestSource {

    public static Participant participant() {
        return Participant.builder()
                .id(1L)
                .limitedIngredient("제한재료")
                .introduce("자기소개")
                .member(MemberTestSource.member())
                .build();
    }

    public static Participant participant(final Member member) {
        return Participant.builder()
                .limitedIngredient("제한재료")
                .introduce("자기소개")
                .member(member)
                .build();
    }

    public static ParticipantEntity participantEntity() {
        return ParticipantEntity.builder()
                .id(1L)
                .limitedIngredient("제한재료")
                .introduce("주최자")
                .member(MemberTestSource.memberEntity())
                .build();
    }

}
