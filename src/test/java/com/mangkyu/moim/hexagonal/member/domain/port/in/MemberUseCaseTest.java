package com.mangkyu.moim.hexagonal.member.domain.port.in;

import com.mangkyu.moim.hexagonal.member.application.MemberService;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import com.mangkyu.moim.hexagonal.member.domain.port.out.SaveMemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.member.MemberTestSource.member;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MemberUseCaseTest {

    private MemberUseCase target;
    private SaveMemberPort saveMemberPort;

    @BeforeEach
    void setUp() {
        saveMemberPort = mock(SaveMemberPort.class);
        target = new MemberService(saveMemberPort);
    }

    @Test
    void 구성원추가() {
        final Member member = member();

        target.addMember(member);

        verify(saveMemberPort, times(1)).save(any(Member.class));
    }


}