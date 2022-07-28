package com.mangkyu.moim.hexagonal.app.member.common.domain.in;

import com.mangkyu.moim.hexagonal.app.member.common.application.MemberService;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.SaveMemberPort;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MemberUseCaseTest {

    private MemberUseCase target;
    private LoadMemberPort loadMemberPort;
    private SaveMemberPort saveMemberPort;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        loadMemberPort = mock(LoadMemberPort.class);
        saveMemberPort = mock(SaveMemberPort.class);
        passwordEncoder = spy(BCryptPasswordEncoder.class);
        target = new MemberService(loadMemberPort, saveMemberPort, passwordEncoder);
    }

    @Test
    void 비밀번호변경실패_존재하지않는사용자() {
        final Member member = member();

        doThrow(new MemberException(MemberErrorCode.NOT_EXIST_MEMBER))
                .when(loadMemberPort)
                .findById(member.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.changePassword(member.getId(), "ashfojdo"));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.NOT_EXIST_MEMBER);
    }

    @Test
    void 비밀번호변경성공() {
        final Member member = member();

        doThrow(new MemberException(MemberErrorCode.NOT_EXIST_MEMBER))
                .when(loadMemberPort)
                .findById(member.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.changePassword(member.getId(), "ashfojdo"));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.NOT_EXIST_MEMBER);
    }

}