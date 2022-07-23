package com.mangkyu.moim.hexagonal.app.member.organizer.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.LoadOrganizerPersistenceAdapter;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerRepository;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.organizerEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LoadOrganizerPortTest {

    private LoadOrganizerPort target;

    @Autowired
    private OrganizerRepository organizerRepository;

    @BeforeEach
    void setUp() {
        target = new LoadOrganizerPersistenceAdapter(organizerRepository);
    }

    @Test
    void 사용자조회_존재함() {
        final OrganizerEntity savedEntity = organizerRepository.save(organizerEntity());

        final Organizer result = target.findByLoginId(savedEntity.getLoginId());

        assertThat(result).isNotNull();

    }

    @Test
    void 사용자조회_존재하지않음() {
        final Organizer result = target.findByLoginId("loginId");

        assertThat(result).isNull();
    }

}