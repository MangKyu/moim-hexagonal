package com.mangkyu.moim.hexagonal.member.converter;

import com.mangkyu.moim.hexagonal.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.member.adapter.web.AddMemberRequest;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.member.MemberTestSource.addMemberRequest;
import static com.mangkyu.moim.hexagonal.member.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberConverterTest {

    @Test
    void AddMemberRequest에서Member로변환() {
        final AddMemberRequest request = addMemberRequest();
        final Member member = MemberConverter.INSTANCE.toMember(request);

        assertAll(
                () -> assertThat(request.getEmail()).isEqualTo(member.getEmail()),
                () -> assertThat(request.getPassword()).isEqualTo(member.getPassword())
        );
    }

    @Test
    void Member에서MemberEntity로변환() {
        final Member member = member();
        final MemberEntity memberEntity = MemberConverter.INSTANCE.toMemberEntity(member);

        assertAll(
                () -> assertThat(member.getEmail()).isEqualTo(memberEntity.getEmail()),
                () -> assertThat(member.getPassword()).isEqualTo(memberEntity.getPassword())
        );
    }

}