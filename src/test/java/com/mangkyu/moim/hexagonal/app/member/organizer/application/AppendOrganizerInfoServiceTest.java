package com.mangkyu.moim.hexagonal.app.member.organizer.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.AppendMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.LoadOrganizerPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.memberInfo;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.organizer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AppendOrganizerInfoServiceTest {

    private AppendMemberInfoUseCase target;
    private LoadOrganizerPort loadOrganizerPort;

    @BeforeEach
    void setUp() {
        loadOrganizerPort = mock(LoadOrganizerPort.class);
        target = new AppendOrganizerInfoService(loadOrganizerPort);
    }

    @Test
    void 주최자정보추가_구성원이없음() {
        final MemberInfo memberInfo = memberInfo();

        doReturn(Optional.empty())
                .when(loadOrganizerPort)
                .findById(memberInfo.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.appendMemberInfo(memberInfo));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.NOT_EXIST_MEMBER);
    }

    @Test
    void 주최자정보추가() {
        final MemberInfo memberInfo = memberInfo();
        final Organizer organizer = organizer();

        doReturn(Optional.of(organizer))
                .when(loadOrganizerPort)
                .findById(memberInfo.getId());

        target.appendMemberInfo(memberInfo);

        assertThat(memberInfo.getBelongs()).isEqualTo(organizer.getBelongs());
    }

}