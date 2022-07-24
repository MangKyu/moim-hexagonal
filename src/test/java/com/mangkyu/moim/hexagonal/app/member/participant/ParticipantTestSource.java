package com.mangkyu.moim.hexagonal.app.member.participant;

import com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantEntity;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.AddParticipantRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.ModifyParticipantRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;

import java.time.LocalDate;

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

    public static ModifyParticipantRequest modifyParticipantRequest() {
        return ModifyParticipantRequest.builder()
                .email("mangkyu@naver.com")
                .introduce("newIntroduce")
                .limitedIngredient("newLimitedIngredient")
                .build();
    }

    public static AddParticipantRequest addParticipantRequest() {
        return AddParticipantRequest.builder()
                .name("mangkyu")
                .birth(LocalDate.of(1994, 12, 26))
                .gender(Gender.MALE)
                .loginId("mangkyu")
                .email("mangkyu@naver.com")
                .password("dkssudgktpdy123!@#")
                .introduce("introduce")
                .limitedIngredient("limitedIngredient")
                .build();
    }

    public static AddParticipantRequest addParticipantRequest(final String email, final String password) {
        return AddParticipantRequest.builder()
                .name("mangkyu")
                .birth(LocalDate.of(1994, 12, 26))
                .gender(Gender.MALE)
                .loginId("mangkyu")
                .email(email)
                .password(password)
                .introduce("introduce")
                .limitedIngredient("limitedIngredient")
                .build();
    }

}
