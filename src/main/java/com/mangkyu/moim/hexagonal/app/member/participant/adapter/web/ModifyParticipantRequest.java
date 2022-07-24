package com.mangkyu.moim.hexagonal.app.member.participant.adapter.web;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ModifyParticipantRequest {

    private final String name;
    private final LocalDate birth;
    private final Gender gender;
    private final String email;
    private final String limitedIngredient;
    private final String introduce;

}
