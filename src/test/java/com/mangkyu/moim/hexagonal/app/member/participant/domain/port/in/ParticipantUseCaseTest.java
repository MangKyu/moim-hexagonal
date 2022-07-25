package com.mangkyu.moim.hexagonal.app.member.participant.domain.port.in;

import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import com.mangkyu.moim.hexagonal.app.member.participant.application.ParticipantService;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.LoadParticipantPort;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out.SaveParticipantPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.participant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipantUseCaseTest {

    private ParticipantUseCase target;

    private SaveParticipantPort saveParticipantPort;
    private LoadParticipantPort loadParticipantPort;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        saveParticipantPort = mock(SaveParticipantPort.class);
        loadParticipantPort = mock(LoadParticipantPort.class);
        passwordEncoder = spy(BCryptPasswordEncoder.class);
        target = new ParticipantService(saveParticipantPort, loadParticipantPort, passwordEncoder);
    }

    @Test
    void 구성원수정_존재하지않음() {
        final Participant participant = participant();
        doThrow(new MemberException(MemberErrorCode.NOT_EXIST_MEMBER))
                .when(loadParticipantPort)
                .findById(participant.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.modifyParticipant(participant.getId(), participant));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.NOT_EXIST_MEMBER);
    }

    @Test
    void 구성원수정성공() {
        final Participant participant = participant();
        doReturn(Optional.of(participant))
                .when(loadParticipantPort)
                .findById(participant.getId());

        doReturn(participant)
                .when(saveParticipantPort)
                .save(any(Participant.class));

        target.modifyParticipant(participant.getId(), participant);
    }

    @Test
    void 구성원추가_중복되는이메일() {
        final Participant participant = participant();
        doReturn(participant)
                .when(loadParticipantPort)
                .findByLoginId(participant.getLoginId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.addParticipant(participant));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.DUPLICATE_LOGINID);
        verify(saveParticipantPort, times(0)).save(any(Participant.class));
    }

    @Test
    void 구성원추가성공() {
        final Participant participant = participant();

        doReturn(null)
                .when(loadParticipantPort)
                .findByLoginId(participant.getLoginId());

        target.addParticipant(participant);

        verify(saveParticipantPort, times(1)).save(any(Participant.class));
        verify(passwordEncoder, times(1)).encode(any(CharSequence.class));
    }

    @Test
    void 구성원역할추가실패_존재하지않는사용자() {
        final Participant participant = participant();
        doThrow(new MemberException(MemberErrorCode.NOT_EXIST_MEMBER))
                .when(loadParticipantPort)
                .findById(participant.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.addRole(participant.getId(), participant));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.NOT_EXIST_MEMBER);
    }

    @Test
    void 구성원역할추가실패_중복된권한() {
        final Participant participant = participant();
        participant.addRole();

        doReturn(Optional.of(participant))
                .when(loadParticipantPort)
                .findById(participant.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.addRole(participant.getId(), participant));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.DUPLICATE_ROLE);
    }

    @Test
    void 구성원역할추가성공() {
        final Participant participant = participant();

        doReturn(Optional.of(participant))
                .when(loadParticipantPort)
                .findById(participant.getId());

        target.addRole(participant.getId(), participant);

        verify(saveParticipantPort, times(1)).save(any(Participant.class));
    }

}