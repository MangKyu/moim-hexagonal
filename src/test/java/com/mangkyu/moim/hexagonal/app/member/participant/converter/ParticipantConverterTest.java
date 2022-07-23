package com.mangkyu.moim.hexagonal.app.member.participant.converter;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantEntity;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.participant;
import static com.mangkyu.moim.hexagonal.app.member.participant.ParticipantTestSource.participantEntity;
import static org.assertj.core.api.Assertions.assertThat;

class ParticipantConverterTest {


    @Test
    void participant에서participantEntity로변환() {
        final Member member = member();
        final Participant participant = participant(member);

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

}