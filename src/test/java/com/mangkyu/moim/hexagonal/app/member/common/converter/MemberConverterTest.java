package com.mangkyu.moim.hexagonal.app.member.common.converter;

import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.common.adapter.web.MemberInfoResponse;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberInfo;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Set;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
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
        final Member member = member();
        member.addRole(MemberRole.ROLE_ORGANIZER);
        
        final MemberEntity memberEntity = MemberConverter.INSTANCE.toMemberEntity(member);
        ReflectionTestUtils.setField(memberEntity, "id", 1L);

        final Member result = MemberConverter.INSTANCE.toMember(memberEntity);

        assertAll(
                () -> assertThat(result.getId()).isEqualTo(memberEntity.getId()),
                () -> assertThat(result.getEmail()).isEqualTo(memberEntity.getEmail()),
                () -> assertThat(result.getRoles()).isEqualTo(memberEntity.getRoles()),
                () -> assertThat(result.getPassword()).isEqualTo(memberEntity.getPassword())
        );
    }


    @Test
    void MemberInfo에서MemberInfoResponse로변환() {
        final MemberInfo memberInfo = memberInfo();
        final MemberInfoResponse result = MemberConverter.INSTANCE.toMemberInfoResponse(memberInfo);

        assertAll(
                () -> assertThat(result.getId()).isEqualTo(memberInfo.getId()),
                () -> assertThat(result.getName()).isEqualTo(memberInfo.getName()),
                () -> assertThat(result.getBirth()).isEqualTo(memberInfo.getBirth()),
                () -> assertThat(result.getGender()).isEqualTo(memberInfo.getGender()),
                () -> assertThat(result.getEmail()).isEqualTo(memberInfo.getEmail()),
                () -> assertThat(result.getLoginId()).isEqualTo(memberInfo.getLoginId()),
                () -> assertThat(result.getBelongs()).isEqualTo(memberInfo.getBelongs()),
                () -> assertThat(result.getIntroduce()).isEqualTo(memberInfo.getIntroduce()),
                () -> assertThat(result.getLimitedIngredient()).isEqualTo(memberInfo.getLimitedIngredient()),
                () -> assertThat(result.getRoles()).isEqualTo(memberInfo.getRoles())
        );
    }

    private MemberInfo memberInfo() {
        return MemberInfo.builder()
                .id(1L)
                .name("name")
                .birth(LocalDate.now())
                .gender(Gender.MALE)
                .email("email")
                .loginId("loginId")
                .password("password")
                .belongs("belongs")
                .limitedIngredient("limitedIngredient")
                .introduce("introduce")
                .roles(Set.of(MemberRole.ROLE_ORGANIZER))
                .build();
    }

}