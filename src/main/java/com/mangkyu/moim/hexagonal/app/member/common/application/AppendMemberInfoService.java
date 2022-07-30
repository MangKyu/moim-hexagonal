package com.mangkyu.moim.hexagonal.app.member.common.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.AppendMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppendMemberInfoService implements AppendMemberInfoUseCase {

    private final LoadMemberPort loadMemberPort;

    public void appendMemberInfo(final MemberInfo memberInfo) {
        final Member member = loadMemberPort.findById(memberInfo.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_EXIST_MEMBER));

        memberInfo.update(member);
    }
}
