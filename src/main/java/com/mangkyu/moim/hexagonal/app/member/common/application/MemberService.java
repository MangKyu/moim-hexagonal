package com.mangkyu.moim.hexagonal.app.member.common.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.in.MemberUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.common.domain.out.SaveMemberPort;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(final Long id, final String password) {
        final Member member = loadMemberPort.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_EXIST_MEMBER));

        member.updatePassword(passwordEncoder, password);

        saveMemberPort.save(member);
    }

}
