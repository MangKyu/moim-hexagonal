package com.mangkyu.moim.hexagonal.app.member.converter;

import com.mangkyu.moim.hexagonal.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddOrganizerRequest;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddMemberResponse;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberConverter {

    MemberConverter INSTANCE = Mappers.getMapper(MemberConverter.class);

    Member toMember(final AddOrganizerRequest request);

    MemberEntity toMemberEntity(final Member member);

    Member toMember(MemberEntity memberEntity);

    AddMemberResponse toAddMemberResponse(Member member);
}
