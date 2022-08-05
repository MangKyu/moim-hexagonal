package com.mangkyu.moim.hexagonal.app.member.common.adapter.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberInfoResponse {

    private final Long id;
    private final String name;
    private final LocalDate birth;
    private final Gender gender;
    private final String email;
    private final String loginId;
    private final String belongs;
    private final String limitedIngredient;
    private final String introduce;
    private final Set<MemberRole> roles;

}
