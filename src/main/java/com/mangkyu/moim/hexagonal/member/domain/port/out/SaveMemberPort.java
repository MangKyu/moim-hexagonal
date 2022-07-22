package com.mangkyu.moim.hexagonal.member.domain.port.out;

import com.mangkyu.moim.hexagonal.member.domain.Member;

public interface SaveMemberPort {
    Member save(Member member);
}
