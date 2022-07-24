package com.mangkyu.moim.hexagonal.app.member.participant.adapter.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class AddParticipantRequest {

    @NotBlank
    private final String name;

    @NotNull
    @JsonFormat(pattern = "yyyyMMdd")
    private final LocalDate birth;

    @NotNull
    private final Gender gender;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String loginId;

    // 최소 8자, 하나 이상의 (문자, 숫자, 특수 문자)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @NotBlank
    private final String password;

    @NotBlank
    private final String limitedIngredient;

    @NotBlank
    private final String introduce;

}
