package com.mangkyu.moim.hexagonal.app.member.common.domain.out;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {

    Member findByLoginId(String email);

    Optional<Member> findById(final Long id);
}
