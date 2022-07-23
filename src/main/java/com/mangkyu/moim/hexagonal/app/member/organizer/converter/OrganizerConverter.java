package com.mangkyu.moim.hexagonal.app.member.organizer.converter;

import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizerConverter {

    OrganizerConverter INSTANCE = Mappers.getMapper(OrganizerConverter.class);

    @Mapping(source = "organizer.id", target = "id")
    OrganizerEntity toOrganizerEntity(final Organizer organizer);

}
