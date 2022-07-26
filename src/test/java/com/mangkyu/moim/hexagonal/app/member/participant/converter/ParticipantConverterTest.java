package com.mangkyu.moim.hexagonal.app.member.participant.converter;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantEntity;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.AddParticipantRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.AddParticipantResponse;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.AddParticipantRoleRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.ModifyParticipantRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.*;
import static org.assertj.core.api.Assertions.assertThat;

class ParticipantConverterTest {


    @Test
    void participant에서participantEntity로변환() {
        final Member member = member();
        final Participant participant = participant(member);
        participant.addRole(MemberRole.ROLE_PARTICIPANT);

        final ParticipantEntity result = ParticipantConverter.INSTANCE.toParticipantEntity(participant);

        assertThat(result.getId()).isEqualTo(participant.getId());
        assertThat(result.getLimitedIngredient()).isEqualTo(participant.getLimitedIngredient());
        assertThat(result.getIntroduce()).isEqualTo(participant.getIntroduce());
        assertThat(result.getName()).isEqualTo(participant.getName());
        assertThat(result.getBirth()).isEqualTo(participant.getBirth());
        assertThat(result.getGender()).isEqualTo(participant.getGender());
        assertThat(result.getEmail()).isEqualTo(participant.getEmail());
        assertThat(result.getLoginId()).isEqualTo(participant.getLoginId());
        assertThat(result.getPassword()).isEqualTo(participant.getPassword());
        assertThat(result.getRoles()).isEqualTo(participant.getRoles());
    }

    @Test
    void organizerEntity에서organizer로변환() {
        final ParticipantEntity participantEntity = participantEntity();
        final Participant result = ParticipantConverter.INSTANCE.toParticipant(participantEntity);

        assertThat(result.getId()).isEqualTo(participantEntity.getId());
        assertThat(result.getLimitedIngredient()).isEqualTo(participantEntity.getLimitedIngredient());
        assertThat(result.getIntroduce()).isEqualTo(participantEntity.getIntroduce());
        assertThat(result.getName()).isEqualTo(participantEntity.getName());
        assertThat(result.getBirth()).isEqualTo(participantEntity.getBirth());
        assertThat(result.getGender()).isEqualTo(participantEntity.getGender());
        assertThat(result.getEmail()).isEqualTo(participantEntity.getEmail());
        assertThat(result.getLoginId()).isEqualTo(participantEntity.getLoginId());
        assertThat(result.getPassword()).isEqualTo(participantEntity.getPassword());
    }

    @Test
    void participant에서로addParticipantResponse변환() {
        final Participant participant = participant();
        participant.addRole(MemberRole.ROLE_PARTICIPANT);

        final AddParticipantResponse result = ParticipantConverter.INSTANCE.toAddParticipantResponse(participant);

        assertThat(result.getId()).isEqualTo(participant.getId());
        assertThat(result.getName()).isEqualTo(participant.getName());
        assertThat(result.getBirth()).isEqualTo(participant.getBirth());
        assertThat(result.getGender()).isEqualTo(participant.getGender());
        assertThat(result.getEmail()).isEqualTo(participant.getEmail());
        assertThat(result.getLoginId()).isEqualTo(participant.getLoginId());
        assertThat(result.getIntroduce()).isEqualTo(participant.getIntroduce());
        assertThat(result.getLimitedIngredient()).isEqualTo(participant.getLimitedIngredient());
        assertThat(result.getRoles()).isEqualTo(participant.getRoles());
    }

    @Test
    void addParticipantRequest에서participant로변환() {
        final AddParticipantRequest request = addParticipantRequest();

        final Participant result = ParticipantConverter.INSTANCE.toParticipant(request);

        assertThat(result.getName()).isEqualTo(request.getName());
        assertThat(result.getBirth()).isEqualTo(request.getBirth());
        assertThat(result.getGender()).isEqualTo(request.getGender());
        assertThat(result.getEmail()).isEqualTo(request.getEmail());
        assertThat(result.getLoginId()).isEqualTo(request.getLoginId());
        assertThat(result.getPassword()).isEqualTo(request.getPassword());
        assertThat(result.getIntroduce()).isEqualTo(request.getIntroduce());
        assertThat(result.getLimitedIngredient()).isEqualTo(request.getLimitedIngredient());
    }

    @Test
    void addParticipantRoleRequest에서participant로변환() {
        final AddParticipantRoleRequest request = addParticipantRoleRequest();

        final Participant result = ParticipantConverter.INSTANCE.toParticipant(request);
        assertThat(result.getIntroduce()).isEqualTo(request.getIntroduce());
        assertThat(result.getLimitedIngredient()).isEqualTo(request.getLimitedIngredient());
    }

    @Test
    void modifyParticipantRequest에서participant로변환() {
        final ModifyParticipantRequest request = modifyParticipantRequest();

        final Participant result = ParticipantConverter.INSTANCE.toParticipant(request);

        assertThat(result.getName()).isEqualTo(request.getName());
        assertThat(result.getBirth()).isEqualTo(request.getBirth());
        assertThat(result.getGender()).isEqualTo(request.getGender());
        assertThat(result.getEmail()).isEqualTo(request.getEmail());
        assertThat(result.getIntroduce()).isEqualTo(request.getIntroduce());
        assertThat(result.getLimitedIngredient()).isEqualTo(request.getLimitedIngredient());
    }

}