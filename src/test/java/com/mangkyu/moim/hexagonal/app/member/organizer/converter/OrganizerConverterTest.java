package com.mangkyu.moim.hexagonal.app.member.organizer.converter;

import com.mangkyu.moim.hexagonal.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.member.MemberTestSource.memberEntity;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.organizer;
import static org.assertj.core.api.Assertions.assertThat;

class OrganizerConverterTest {

    @Test
    void organizer에서organizerEntity로변환() {
        final MemberEntity memberEntity = memberEntity();
        final Organizer organizer = organizer();

        final OrganizerEntity result = OrganizerConverter.INSTANCE.toOrganizerEntity(organizer);

        assertThat(result.getMember().getId()).isEqualTo(memberEntity.getId());
        assertThat(result.getMember().getEmail()).isEqualTo(memberEntity.getEmail());
        assertThat(result.getMember().getPassword()).isEqualTo(memberEntity.getPassword());
        assertThat(result.getBelongs()).isEqualTo(organizer.getBelongs());
    }

}