package com.mangkyu.moim.hexagonal.app.member.common.adapter.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ModifyMemberPasswordRequest {

    // 최소 8자, 하나 이상의 (문자, 숫자, 특수 문자)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @NotBlank
    private final String password;

}
