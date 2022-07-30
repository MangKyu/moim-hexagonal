package com.mangkyu.moim.hexagonal.app.member.common.domain.in;

import com.mangkyu.moim.hexagonal.app.member.common.application.MemberInfoService;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class GetMemberInfoUseCaseTest {

    private GetMemberInfoUseCase target;

    @BeforeEach
    void setUp() {
        target = new MemberInfoService(List.of(mock(AppendMemberInfoUseCase.class)));
    }

    @Test
    void 구성원정보조회() {
        final MemberInfo memberInfo = target.getMemberInfo(1L);
        assertThat(memberInfo).isNotNull();
    }

}