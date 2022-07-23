package com.mangkyu.moim.hexagonal.app.member.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.domain.Member;

public interface SaveMemberPort {
    Member save(Member member);
}
