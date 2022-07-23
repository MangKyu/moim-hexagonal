package com.mangkyu.moim.hexagonal.app.login.domain.in;

public interface GenerateLoginTokenUseCase {

    String generate(String email);

}
