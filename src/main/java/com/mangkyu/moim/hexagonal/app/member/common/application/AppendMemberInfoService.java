package com.mangkyu.moim.hexagonal.app.member.common.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.AppendMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppendMemberInfoService implements AppendMemberInfoUseCase {

    private final LoadMemberPort loadMemberPort;

    public void appendMemberInfo(final MemberInfo memberInfo) {
        loadMemberPort.findById(memberInfo.getId())
                .ifPresent(memberInfo::update);
    }
}
