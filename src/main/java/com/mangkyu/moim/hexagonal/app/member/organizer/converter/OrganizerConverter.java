package com.mangkyu.moim.hexagonal.app.member.organizer.converter;

import com.mangkyu.moim.hexagonal.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence.OrganizerEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.domain.Organizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizerConverter {

    OrganizerConverter INSTANCE = Mappers.getMapper(OrganizerConverter.class);


    @Mappings({
            @Mapping(source = "organizer.id", target = "id"),
            @Mapping(source = "memberEntity", target = "member")
    })
    OrganizerEntity toOrganizerEntity(final Organizer organizer, final MemberEntity memberEntity);

}
