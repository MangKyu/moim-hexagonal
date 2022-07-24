package com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.LoadParticipantPersistenceAdapter;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantEntity;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantRepository;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.participantEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LoadParticipantPortTest {

    private LoadParticipantPort target;

    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() {
        target = new LoadParticipantPersistenceAdapter(participantRepository);
    }

    @Test
    void 아이디로사용자조회_존재하지않음() {
        final Optional<Participant> result = target.findById(-1L);

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void 아이디로사용자조회_존재함() {
        final ParticipantEntity savedEntity = participantRepository.save(participantEntity());

        final Optional<Participant> result = target.findById(savedEntity.getMember().getId());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void 로그인아이디로사용자조회_존재함() {
        final ParticipantEntity savedEntity = participantRepository.save(participantEntity());

        final Participant result = target.findByLoginId(savedEntity.getLoginId());

        assertThat(result).isNotNull();
    }

    @Test
    void 로그인아이디로사용자조회_존재하지않음() {
        final Participant result = target.findByLoginId("loginId");

        assertThat(result).isNull();
    }

}