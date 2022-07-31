package com.mangkyu.moim.hexagonal.app.login.domain.out;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;

public interface SaveLoginTokenPort {

    LoginToken save(LoginToken loginToken);

}
