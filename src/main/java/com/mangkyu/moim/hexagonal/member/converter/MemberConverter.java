package com.mangkyu.moim.hexagonal.member.converter;

import com.mangkyu.moim.hexagonal.member.adapter.web.AddMemberRequest;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberConverter {

    MemberConverter INSTANCE = Mappers.getMapper(MemberConverter.class);

    Member toMember(final AddMemberRequest request);

}
