package com.mangkyu.moim.hexagonal.app.member.participant.converter;

import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantEntity;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipantConverter {

    ParticipantConverter INSTANCE = Mappers.getMapper(ParticipantConverter.class);

    @Mapping(source = "participant.id", target = "id")
    ParticipantEntity toParticipantEntity(final Participant participant);

    @Mapping(source = "participantEntity.id", target = "id")
    Participant toParticipant(final ParticipantEntity participantEntity);

}
