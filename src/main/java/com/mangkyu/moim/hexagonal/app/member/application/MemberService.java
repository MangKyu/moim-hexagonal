package com.mangkyu.moim.hexagonal.app.member.application;

import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.domain.port.in.MemberUseCase;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.LoadMemberPort;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.SaveMemberPort;
import com.mangkyu.moim.hexagonal.app.member.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.errors.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {

    private final SaveMemberPort saveMemberPort;
    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;

    public Member addMember(final Member member) {
        final Member foundMember = loadMemberPort.findByEmail(member.getEmail());
        if (foundMember != null) {
            throw new MemberException(LogLevel.INFO, MemberErrorCode.DUPLICATE_EMAIL);
        }

        member.encryptPassword(passwordEncoder);
        return saveMemberPort.save(member);
    }

}
