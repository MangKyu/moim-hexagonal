package com.mangkyu.moim.hexagonal.app.member.common.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.AppendMemberInfoUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.GetMemberInfoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements GetMemberInfoUseCase {

    private final List<AppendMemberInfoUseCase> appendMemberInfoUseCaseList;

    @Override
    public MemberInfo getMemberInfo(final long id) {
        final MemberInfo memberInfo = new MemberInfo(id);
        appendMemberInfoUseCaseList.forEach(useCase -> useCase.appendMemberInfo(memberInfo));
        return memberInfo;
    }

}
