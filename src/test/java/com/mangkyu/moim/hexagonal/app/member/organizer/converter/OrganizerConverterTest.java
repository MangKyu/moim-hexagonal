package com.mangkyu.moim.hexagonal.app.member.organizer.converter;

import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.member.MemberTestSource.member;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.organizer;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.organizerEntity;
import static org.assertj.core.api.Assertions.assertThat;

class OrganizerConverterTest {

    @Test
    void organizer에서organizerEntity로변환() {
        final Member member = member();
        final Organizer organizer = organizer(member);

        final OrganizerEntity result = OrganizerConverter.INSTANCE.toOrganizerEntity(organizer);

        assertThat(result.getMember().getId()).isEqualTo(member.getId());
        assertThat(result.getMember().getEmail()).isEqualTo(member.getEmail());
        assertThat(result.getMember().getPassword()).isEqualTo(member.getPassword());
        assertThat(result.getBelongs()).isEqualTo(organizer.getBelongs());
    }

    @Test
    void organizerEntity에서organizer로변환() {
        final OrganizerEntity organizerEntity = organizerEntity();

        final Organizer result = OrganizerConverter.INSTANCE.toOrganizer(organizerEntity);

        assertThat(result.getMember().getId()).isEqualTo(organizerEntity.getId());
        assertThat(result.getMember().getEmail()).isEqualTo(organizerEntity.getEmail());
        assertThat(result.getMember().getPassword()).isEqualTo(organizerEntity.getPassword());
        assertThat(result.getBelongs()).isEqualTo(organizerEntity.getBelongs());
    }

}