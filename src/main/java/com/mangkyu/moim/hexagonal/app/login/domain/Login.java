package com.mangkyu.moim.hexagonal.app.login.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Login {

    private String email;
    private String password;

}
