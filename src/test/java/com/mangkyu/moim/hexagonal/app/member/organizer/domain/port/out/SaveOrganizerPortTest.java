package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.SaveOrganizerPersistenceAdapter;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerRepository;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.organizer;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveOrganizerPortTest {

    private SaveOrganizerPort target;

    @Autowired
    private OrganizerRepository organizerRepository;

    @BeforeEach
    void setUp() {
        target = new SaveOrganizerPersistenceAdapter(organizerRepository);
    }

    @Test
    void 주최자저장() {
        final Organizer result = target.save(organizer());

        assertThat(result).isNotNull();
        assertThat(organizerRepository.findAll()).isNotEmpty();
    }
}