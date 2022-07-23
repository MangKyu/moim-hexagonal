package com.mangkyu.moim.hexagonal.app.member.common.converter;

import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberConverter {

    MemberConverter INSTANCE = Mappers.getMapper(MemberConverter.class);


    MemberEntity toMemberEntity(final Member member);

    Member toMember(MemberEntity memberEntity);

}
