package com.mangkyu.moim.hexagonal.app.member.participant.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.AppendMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.LoadParticipantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppendParticipantInfoService implements AppendMemberInfoUseCase {

    private final LoadParticipantPort loadParticipantPort;

    public void appendMemberInfo(final MemberInfo memberInfo) {
        final Participant participant = loadParticipantPort.findById(memberInfo.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_EXIST_MEMBER));

        memberInfo.update(participant);
    }
}
