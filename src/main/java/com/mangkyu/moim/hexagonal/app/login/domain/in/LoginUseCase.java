package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;

public interface LoginUseCase {

    LoginToken login(final Login login);

}
