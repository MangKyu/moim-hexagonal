package com.mangkyu.moim.hexagonal.member.converter;

import com.mangkyu.moim.hexagonal.member.adapter.web.AddMemberRequest;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import org.junit.jupiter.api.Test;

import static com.mangkyu.moim.hexagonal.member.MemberTestSource.addMemberRequest;
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

}