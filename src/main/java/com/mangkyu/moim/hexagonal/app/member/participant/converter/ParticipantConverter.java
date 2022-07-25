package com.mangkyu.moim.hexagonal.app.member.participant.converter;

import com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence.ParticipantEntity;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.AddParticipantRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.AddParticipantResponse;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.AddParticipantRoleRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.adapter.web.ModifyParticipantRequest;
import com.mangkyu.moim.hexagonal.app.member.participant.domain.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipantConverter {

    ParticipantConverter INSTANCE = Mappers.getMapper(ParticipantConverter.class);

    @Mapping(source = "request.id", target = "id")
    ParticipantEntity toParticipantEntity(final Participant request);

    @Mapping(source = "participantEntity.id", target = "id")
    Participant toParticipant(final ParticipantEntity participantEntity);

    @Mappings({
            @Mapping(source = "request.name", target = "member.name"),
            @Mapping(source = "request.birth", target = "member.birth"),
            @Mapping(source = "request.gender", target = "member.gender"),
            @Mapping(source = "request.email", target = "member.email"),
            @Mapping(source = "request.loginId", target = "member.loginId"),
            @Mapping(source = "request.password", target = "member.password"),
            @Mapping(source = "request.limitedIngredient", target = "limitedIngredient"),
            @Mapping(source = "request.introduce", target = "introduce")
    })
    Participant toParticipant(final AddParticipantRequest request);

    @Mappings({
            @Mapping(source = "request.name", target = "member.name"),
            @Mapping(source = "request.birth", target = "member.birth"),
            @Mapping(source = "request.gender", target = "member.gender"),
            @Mapping(source = "request.email", target = "member.email"),
            @Mapping(source = "request.limitedIngredient", target = "limitedIngredient"),
            @Mapping(source = "request.introduce", target = "introduce")
    })
    Participant toParticipant(final ModifyParticipantRequest request);

    @Mappings({
            @Mapping(source = "request.member.id", target = "id"),
            @Mapping(source = "request.member.name", target = "name"),
            @Mapping(source = "request.member.birth", target = "birth"),
            @Mapping(source = "request.member.gender", target = "gender"),
            @Mapping(source = "request.member.email", target = "email"),
            @Mapping(source = "request.member.loginId", target = "loginId"),
            @Mapping(source = "request.limitedIngredient", target = "limitedIngredient"),
            @Mapping(source = "request.introduce", target = "introduce")
    })
    AddParticipantResponse toAddParticipantResponse(final Participant request);


    Participant toParticipant(AddParticipantRoleRequest request);
}
