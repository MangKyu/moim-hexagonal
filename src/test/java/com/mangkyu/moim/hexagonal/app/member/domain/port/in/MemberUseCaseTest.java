package com.mangkyu.moim.hexagonal.app.member.domain.port.in;

import com.mangkyu.moim.hexagonal.app.member.application.MemberService;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.SaveMemberPort;
import com.mangkyu.moim.hexagonal.app.member.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.errors.MemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.mangkyu.moim.hexagonal.app.member.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MemberUseCaseTest {

    private MemberUseCase target;
    private SaveMemberPort saveMemberPort;
    private LoadMemberPort loadMemberPort;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        saveMemberPort = mock(SaveMemberPort.class);
        loadMemberPort = mock(LoadMemberPort.class);
        passwordEncoder = spy(BCryptPasswordEncoder.class);
        target = new MemberService(saveMemberPort, loadMemberPort, passwordEncoder);
    }

    @Test
    void 구성원추가_중복되는이메일() {
        final Member member = member();
        doReturn(member)
                .when(loadMemberPort)
                        .findByEmail(member.getEmail());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.addMember(member));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.DUPLICATE_EMAIL);
        verify(saveMemberPort, times(0)).save(any(Member.class));
    }

    @Test
    void 구성원추가성공() {
        final Member member = member();

        doReturn(null)
                .when(loadMemberPort)
                .findByEmail(member.getEmail());

        target.addMember(member);

        verify(saveMemberPort, times(1)).save(any(Member.class));
        verify(passwordEncoder, times(1)).encode(any(CharSequence.class));
    }

}