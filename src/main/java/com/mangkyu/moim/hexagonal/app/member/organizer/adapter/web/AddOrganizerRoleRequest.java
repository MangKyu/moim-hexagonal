package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class AddOrganizerRoleRequest {

    @NotBlank
    private final String belongs;

}
