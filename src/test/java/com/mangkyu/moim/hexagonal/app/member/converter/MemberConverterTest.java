package com.mangkyu.moim.hexagonal.app.member.converter;

import com.mangkyu.moim.hexagonal.app.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.organizer.adapter.web.AddMemberResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mangkyu.moim.hexagonal.app.member.MemberTestSource.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberConverterTest {

    @Test
    void Member에서MemberEntity로변환() {
        final Member member = member();
        final MemberEntity memberEntity = MemberConverter.INSTANCE.toMemberEntity(member);

        assertAll(
                () -> assertThat(member.getEmail()).isEqualTo(memberEntity.getEmail()),
                () -> assertThat(member.getPassword()).isEqualTo(memberEntity.getPassword())
        );
    }

    @Test
    void MemberEntity에서Member로변환() {
        final MemberEntity memberEntity = MemberConverter.INSTANCE.toMemberEntity(member());
        ReflectionTestUtils.setField(memberEntity, "id", 1L);

        final Member member = MemberConverter.INSTANCE.toMember(memberEntity);

        assertAll(
                () -> assertThat(member.getId()).isEqualTo(memberEntity.getId()),
                () -> assertThat(member.getEmail()).isEqualTo(memberEntity.getEmail()),
                () -> assertThat(member.getPassword()).isEqualTo(memberEntity.getPassword())
        );
    }

    @Test
    void Member에서AddMemberResponse로변환() {
        final Member member = member();
        final AddMemberResponse response = MemberConverter.INSTANCE.toAddMemberResponse(member);

        assertAll(
                () -> assertThat(response.getId()).isEqualTo(member.getId()),
                () -> assertThat(response.getEmail()).isEqualTo(member.getEmail())
        );
    }

}