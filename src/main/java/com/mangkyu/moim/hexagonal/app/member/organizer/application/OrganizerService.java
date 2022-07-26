package com.mangkyu.moim.hexagonal.app.member.organizer.application;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.in.OrganizerUseCase;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.LoadOrganizerPort;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.SaveOrganizerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizerService implements OrganizerUseCase {

    private final SaveOrganizerPort saveOrganizerPort;
    private final LoadOrganizerPort loadOrganizerPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Organizer addOrganizer(final Organizer organizer) {
        final Organizer foundOrganizer = loadOrganizerPort.findByLoginId(organizer.getLoginId());
        if (foundOrganizer != null) {
            throw new MemberException(LogLevel.INFO, MemberErrorCode.DUPLICATE_LOGINID);
        }

        organizer.encryptPassword(passwordEncoder);

        organizer.addRole(MemberRole.ROLE_ORGANIZER);
        return saveOrganizerPort.save(organizer);
    }

    @Override
    public Organizer modifyOrganizer(final Long id, final Organizer organizer) {
        loadOrganizerPort.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_EXIST_MEMBER));

        return saveOrganizerPort.save(organizer);
    }

    @Override
    public Organizer addRole(final Long id, final Organizer organizer) {
        final Member member = loadOrganizerPort.findMemberById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_EXIST_MEMBER));

        organizer.addRole(member);

        return saveOrganizerPort.save(organizer);
    }

}
