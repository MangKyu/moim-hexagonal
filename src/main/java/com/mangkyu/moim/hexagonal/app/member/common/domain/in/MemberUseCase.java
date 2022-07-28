package com.mangkyu.moim.hexagonal.app.member.common.domain.in;

public interface MemberUseCase {
    void changePassword(final Long id, final String password);
}
