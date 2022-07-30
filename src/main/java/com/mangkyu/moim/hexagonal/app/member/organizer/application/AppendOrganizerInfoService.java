package com.mangkyu.moim.hexagonal.app.member.organizer.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.AppendMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.LoadOrganizerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppendOrganizerInfoService implements AppendMemberInfoUseCase {

    private final LoadOrganizerPort loadOrganizerPort;

    public void appendMemberInfo(final MemberInfo memberInfo) {
        loadOrganizerPort.findById(memberInfo.getId()).
                ifPresent(memberInfo::update);
    }

}
