package com.mangkyu.moim.hexagonal.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {

    private String email;
    private String password;

}
