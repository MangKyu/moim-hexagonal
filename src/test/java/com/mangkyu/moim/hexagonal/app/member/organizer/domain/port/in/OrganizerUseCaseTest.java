package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.in;

import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberErrorCode;
import com.mangkyu.moim.hexagonal.app.member.common.errors.MemberException;
import com.mangkyu.moim.hexagonal.app.member.organizer.application.OrganizerService;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.LoadOrganizerPort;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out.SaveOrganizerPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.organizer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizerUseCaseTest {

    private OrganizerUseCase target;

    private SaveOrganizerPort saveOrganizerPort;
    private LoadOrganizerPort loadOrganizerPort;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        saveOrganizerPort = mock(SaveOrganizerPort.class);
        loadOrganizerPort = mock(LoadOrganizerPort.class);
        passwordEncoder = spy(BCryptPasswordEncoder.class);
        target = new OrganizerService(saveOrganizerPort, loadOrganizerPort, passwordEncoder);
    }

    @Test
    void 구성원수정_존재하지않음() {
        final Organizer organizer = organizer();
        doThrow(new MemberException(MemberErrorCode.NOT_EXIST_MEMBER))
                .when(loadOrganizerPort)
                .findById(organizer.getId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.modifyOrganizer(organizer.getId(), organizer));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.NOT_EXIST_MEMBER);
    }

    @Test
    void 구성원수정성공() {
        final Organizer organizer = organizer();
        doReturn(Optional.of(organizer))
                .when(loadOrganizerPort)
                .findById(organizer.getId());

        doReturn(organizer)
                .when(saveOrganizerPort)
                .save(any(Organizer.class));

        target.modifyOrganizer(organizer.getId(), organizer);
    }

    @Test
    void 구성원추가_중복되는이메일() {
        final Organizer organizer = organizer();
        doReturn(organizer)
                .when(loadOrganizerPort)
                .findByLoginId(organizer.getLoginId());

        final MemberException result = assertThrows(
                MemberException.class,
                () -> target.addOrganizer(organizer));

        assertThat(result.getErrorCode()).isEqualTo(MemberErrorCode.DUPLICATE_LOGINID);
        verify(saveOrganizerPort, times(0)).save(any(Organizer.class));
    }

    @Test
    void 구성원추가성공() {
        final Organizer organizer = organizer();

        doReturn(null)
                .when(loadOrganizerPort)
                .findByLoginId(organizer.getLoginId());

        target.addOrganizer(organizer);

        verify(saveOrganizerPort, times(1)).save(any(Organizer.class));
        verify(passwordEncoder, times(1)).encode(any(CharSequence.class));
    }

}