package com.mangkyu.moim.hexagonal.app.member.participant.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantRepository;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.SaveParticipantPersistenceAdapter;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.participant;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveParticipantPortTest {

    private SaveParticipantPort target;

    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() {
        target = new SaveParticipantPersistenceAdapter(participantRepository);
    }

    @Test
    void 주최자저장() {
        final Participant result = target.save(participant());

        assertThat(result).isNotNull();
        assertThat(participantRepository.findAll()).isNotEmpty();
    }
}