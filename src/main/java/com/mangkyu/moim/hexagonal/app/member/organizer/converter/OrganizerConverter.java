package com.mangkyu.moim.hexagonal.app.member.organizer.converter;

import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerResponse;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizerConverter {

    OrganizerConverter INSTANCE = Mappers.getMapper(OrganizerConverter.class);

    @Mapping(source = "organizer.id", target = "id")
    OrganizerEntity toOrganizerEntity(final Organizer organizer);

    @Mapping(source = "organizerEntity.id", target = "id")
    Organizer toOrganizer(OrganizerEntity organizerEntity);

    @Mappings({
            @Mapping(source = "request.name", target = "member.name"),
            @Mapping(source = "request.birth", target = "member.birth"),
            @Mapping(source = "request.gender", target = "member.gender"),
            @Mapping(source = "request.email", target = "member.email"),
            @Mapping(source = "request.loginId", target = "member.loginId"),
            @Mapping(source = "request.password", target = "member.password"),
            @Mapping(source = "request.belongs", target = "belongs")
    })
    Organizer toOrganizer(final AddOrganizerRequest request);

    AddOrganizerResponse toAddOrganizerResponse(Organizer organizer);
}
