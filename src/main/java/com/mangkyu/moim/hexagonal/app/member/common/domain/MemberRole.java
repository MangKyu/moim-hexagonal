package com.mangkyu.moim.hexagonal.app.member.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    ROLE_ORGANIZER,
    ROLE_PARTICIPANT,
    ;
}
