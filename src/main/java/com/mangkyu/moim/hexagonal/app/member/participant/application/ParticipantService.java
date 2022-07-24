package com.mangkyu.moim.hexagonal.app.member.participant.application;

import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.in.ParticipantUseCase;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.LoadParticipantPort;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.SaveParticipantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService implements ParticipantUseCase {

    private final SaveParticipantPort saveParticipantPort;
    private final LoadParticipantPort loadParticipantPort;
    private final PasswordEncoder passwordEncoder;
    
    public Participant addParticipant(final Participant participant) {
        final Participant foundParticipant = loadParticipantPort.findByLoginId(participant.getLoginId());
        if (foundParticipant != null) {
            throw new MemberException(LogLevel.INFO, MemberErrorCode.DUPLICATE_LOGINID);
        }

        participant.encryptPassword(passwordEncoder);
        return saveParticipantPort.save(participant);
    }

    @Override
    public Participant modifyParticipant(final Long id, final Participant participant) {
        loadParticipantPort.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_EXIST_MEMBER));

        return saveParticipantPort.save(participant);
    }

}
