package com.mangkyu.moim.hexagonal.app.member.common.domain.out;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;

public interface SaveMemberPort {

    Member save(Member member);

}
