package com.mangkyu.moim.hexagonal.app.member.common.domain.out;

import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberRepository;
import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.SaveMemberPersistenceAdapter;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.member;
import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.memberEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveMemberPortTest {

    private SaveMemberPort target;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        target = new SaveMemberPersistenceAdapter(memberRepository);
    }

    @Test
    void loginId로사용자조회_존재함() {
        final Member member = target.save(member());

        final Optional<MemberEntity> result = memberRepository.findById(member.getId());

        assertThat(result.isPresent()).isTrue();

    }
}