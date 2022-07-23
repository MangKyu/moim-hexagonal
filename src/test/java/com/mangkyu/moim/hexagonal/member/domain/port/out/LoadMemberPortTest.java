package com.mangkyu.moim.hexagonal.member.domain.port.out;

import com.mangkyu.moim.hexagonal.member.adapter.persistence.LoadMemberPersistenceAdapter;
import com.mangkyu.moim.hexagonal.member.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.member.adapter.persistence.MemberRepository;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.moim.hexagonal.member.MemberTestSource.memberEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LoadMemberPortTest {

    private LoadMemberPort target;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        target = new LoadMemberPersistenceAdapter(memberRepository);
    }

    @Test
    void 사용자조회_존재함() {
        final MemberEntity savedMemberEntity = memberRepository.save(memberEntity());

        final Member result = target.findByEmail(savedMemberEntity.getEmail());

        assertThat(result).isNotNull();

    }

    @Test
    void 사용자조회_존재하지않음() {
        final Member result = target.findByEmail("notexists@email.com");

        assertThat(result).isNull();
    }

}