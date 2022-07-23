package com.mangkyu.moim.hexagonal.app.login.domain.in;

import com.mangkyu.moim.hexagonal.app.login.domain.Login;

public interface LoginUseCase {

    String login(final Login login);

}
