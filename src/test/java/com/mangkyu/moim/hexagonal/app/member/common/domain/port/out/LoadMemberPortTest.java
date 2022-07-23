package com.mangkyu.moim.hexagonal.app.member.common.domain.port.out;

import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.LoadMemberPersistenceAdapter;
import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberEntity;
import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberRepository;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.domain.port.out.LoadMemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.moim.hexagonal.app.member.common.MemberTestSource.memberEntity;
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

        final Member result = target.findByLoginId(savedMemberEntity.getLoginId());

        assertThat(result).isNotNull();

    }

    @Test
    void 사용자조회_존재하지않음() {
        final Member result = target.findByLoginId("notexists");

        assertThat(result).isNull();
    }

}