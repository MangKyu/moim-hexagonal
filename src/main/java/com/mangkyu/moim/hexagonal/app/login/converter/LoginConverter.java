package com.mangkyu.moim.hexagonal.app.login.converter;

import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginRequest;
import com.mangkyu.moim.hexagonal.app.login.adapter.web.LoginResponse;
import com.mangkyu.moim.hexagonal.app.login.domain.Login;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginConverter {

    LoginConverter INSTANCE = Mappers.getMapper(LoginConverter.class);

    Login toLogin(final LoginRequest request);

    LoginResponse toLoginResponse(final LoginToken request);


}
