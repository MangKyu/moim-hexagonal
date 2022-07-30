package com.mangkyu.moim.hexagonal.app.member.common.domain.in;

import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;

public interface AppendMemberInfoUseCase {

    void appendMemberInfo(final MemberInfo memberInfo);

}
