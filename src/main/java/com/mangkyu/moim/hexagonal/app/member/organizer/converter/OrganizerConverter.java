package com.mangkyu.moim.hexagonal.app.member.organizer.converter;

import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerResponse;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerRoleRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.ModifyOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizerConverter {

    OrganizerConverter INSTANCE = Mappers.getMapper(OrganizerConverter.class);

    @Mapping(source = "request.id", target = "id")
    OrganizerEntity toOrganizerEntity(final Organizer request);

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

    @Mappings({
            @Mapping(source = "request.name", target = "member.name"),
            @Mapping(source = "request.birth", target = "member.birth"),
            @Mapping(source = "request.gender", target = "member.gender"),
            @Mapping(source = "request.email", target = "member.email"),
            @Mapping(source = "request.belongs", target = "belongs")
    })
    Organizer toOrganizer(final ModifyOrganizerRequest request);

    @Mappings({
            @Mapping(source = "request.belongs", target = "belongs")
    })
    Organizer toOrganizer(final AddOrganizerRoleRequest request);

    @Mappings({
            @Mapping(source = "request.member.id", target = "id"),
            @Mapping(source = "request.member.name", target = "name"),
            @Mapping(source = "request.member.birth", target = "birth"),
            @Mapping(source = "request.member.gender", target = "gender"),
            @Mapping(source = "request.member.email", target = "email"),
            @Mapping(source = "request.member.loginId", target = "loginId"),
            @Mapping(source = "request.belongs", target = "belongs")
    })
    AddOrganizerResponse toAddOrganizerResponse(Organizer request);
}
