package com.mangkyu.moim.hexagonal.app.member.participant.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.AppendMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.LoadParticipantPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.memberInfo;
import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.participant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class AppendParticipantInfoServiceTest {

    private AppendMemberInfoUseCase target;
    private LoadParticipantPort loadParticipantPort;

    @BeforeEach
    void setUp() {
        loadParticipantPort = mock(LoadParticipantPort.class);
        target = new AppendParticipantInfoService(loadParticipantPort);
    }

    @Test
    void 참여자정보추가() {
        final MemberInfo memberInfo = memberInfo();
        final Participant participant = participant();

        doReturn(Optional.of(participant))
                .when(loadParticipantPort)
                .findById(memberInfo.getId());

        target.appendMemberInfo(memberInfo);

        assertThat(memberInfo.getIntroduce()).isEqualTo(participant.getIntroduce());
        assertThat(memberInfo.getLimitedIngredient()).isEqualTo(participant.getLimitedIngredient());
    }

}