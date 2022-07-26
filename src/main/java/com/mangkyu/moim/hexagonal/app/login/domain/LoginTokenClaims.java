package com.mangkyu.moim.hexagonal.app.login.domain;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class LoginTokenClaims {

    private final Long id;
    private final Gender gender;
    private final String email;
    private final String loginId;
    private final Set<MemberRole> roles;

}
