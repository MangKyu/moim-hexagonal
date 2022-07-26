package com.mangkyu.moim.hexagonal.app.member.organizer.converter;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerResponse;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerRoleRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.ModifyOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerTestSource.*;
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

    @Test
    void addOrganizerRequest에서organizer로변환() {
        final AddOrganizerRequest request = addOrganizerRequest();

        final Organizer result = OrganizerConverter.INSTANCE.toOrganizer(request);

        assertThat(result.getName()).isEqualTo(request.getName());
        assertThat(result.getBirth()).isEqualTo(request.getBirth());
        assertThat(result.getGender()).isEqualTo(request.getGender());
        assertThat(result.getEmail()).isEqualTo(request.getEmail());
        assertThat(result.getLoginId()).isEqualTo(request.getLoginId());
        assertThat(result.getPassword()).isEqualTo(request.getPassword());
        assertThat(result.getBelongs()).isEqualTo(request.getBelongs());
    }

    @Test
    void addOrganizerRequestRole에서organizer로변환() {
        final AddOrganizerRoleRequest request = addOrganizerRoleRequest();

        final Organizer result = OrganizerConverter.INSTANCE.toOrganizer(request);

        assertThat(result.getBelongs()).isEqualTo(request.getBelongs());
    }

    @Test
    void modifyOrganizerRequest에서organizer로변환() {
        final ModifyOrganizerRequest request = modifyOrganizerRequest();

        final Organizer result = OrganizerConverter.INSTANCE.toOrganizer(request);

        assertThat(result.getName()).isEqualTo(request.getName());
        assertThat(result.getBirth()).isEqualTo(request.getBirth());
        assertThat(result.getGender()).isEqualTo(request.getGender());
        assertThat(result.getEmail()).isEqualTo(request.getEmail());
        assertThat(result.getBelongs()).isEqualTo(request.getBelongs());
    }

    @Test
    void organizer에서로addOrganizerResponse변환() {
        final Organizer organizer = organizer();
        organizer.addRole(MemberRole.ROLE_ORGANIZER);

        final AddOrganizerResponse result = OrganizerConverter.INSTANCE.toAddOrganizerResponse(organizer);

        assertThat(result.getId()).isEqualTo(organizer.getId());
        assertThat(result.getName()).isEqualTo(organizer.getName());
        assertThat(result.getBirth()).isEqualTo(organizer.getBirth());
        assertThat(result.getGender()).isEqualTo(organizer.getGender());
        assertThat(result.getEmail()).isEqualTo(organizer.getEmail());
        assertThat(result.getLoginId()).isEqualTo(organizer.getLoginId());
        assertThat(result.getBelongs()).isEqualTo(organizer.getBelongs());
        assertThat(result.getRoles()).isEqualTo(organizer.getRoles());
    }

}