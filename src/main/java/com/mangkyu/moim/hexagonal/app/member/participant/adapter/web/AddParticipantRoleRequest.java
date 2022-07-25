package com.mangkyu.moim.hexagonal.app.member.participant.adapter.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class AddParticipantRoleRequest {

    @NotBlank
    private final String limitedIngredient;

    @NotBlank
    private final String introduce;

}
