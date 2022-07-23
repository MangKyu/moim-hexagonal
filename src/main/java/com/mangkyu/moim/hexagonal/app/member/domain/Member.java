package com.mangkyu.moim.hexagonal.app.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;
    private String email;
    private String password;

}
