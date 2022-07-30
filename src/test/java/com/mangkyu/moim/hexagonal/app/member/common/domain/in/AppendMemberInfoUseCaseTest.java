package com.mangkyu.moim.hexagonal.app.member.common.domain.in;

import com.mangkyu.moim.hexagonal.app.member.common.application.AppendMemberInfoService;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.memberInfo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class AppendMemberInfoUseCaseTest {

    private AppendMemberInfoUseCase target;
    private LoadMemberPort loadMemberPort;

    @BeforeEach
    void setUp() {
        loadMemberPort = mock(LoadMemberPort.class);
        target = new AppendMemberInfoService(loadMemberPort);
    }

    @Test
    void 구성원정보추가_구성원이없음() {
        final MemberInfo memberInfo = memberInfo();

        doReturn(Optional.empty())
                .when(loadMemberPort)
                .findById(memberInfo.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.appendMemberInfo(memberInfo));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.NOT_EXIST_MEMBER);
    }

    @Test
    void 구성원정보추가() {
        final MemberInfo memberInfo = memberInfo();
        final Member member = member();

        doReturn(Optional.of(member))
                .when(loadMemberPort)
                .findById(memberInfo.getId());

        target.appendMemberInfo(memberInfo);

        assertThat(memberInfo.getName()).isEqualTo(member.getName());
        assertThat(memberInfo.getBirth()).isEqualTo(member.getBirth());
        assertThat(memberInfo.getGender()).isEqualTo(member.getGender());
        assertThat(memberInfo.getEmail()).isEqualTo(member.getEmail());
        assertThat(memberInfo.getLoginId()).isEqualTo(member.getLoginId());
        assertThat(memberInfo.getPassword()).isEqualTo(member.getPassword());
        assertThat(memberInfo.getRoles()).isEqualTo(member.getRoles());
    }

}