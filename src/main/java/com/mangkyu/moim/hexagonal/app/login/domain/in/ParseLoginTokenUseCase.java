package com.mangkyu.moim.hexagonal.app.login.domain.in;

public interface ParseLoginTokenUseCase {

    String parseClaims(String token);

}
