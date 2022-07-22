package com.mangkyu.moim.hexagonal.member.domain.port.out;

import com.mangkyu.moim.hexagonal.member.adapter.persistence.MemberRepository;
import com.mangkyu.moim.hexagonal.member.adapter.persistence.SaveMemberPersistenceAdapter;
import com.mangkyu.moim.hexagonal.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.moim.hexagonal.member.MemberTestSource.member;
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
    void save() {
        final Member member = member();
        target.save(member);

        assertThat(memberRepository.findAll()).isNotEmpty();

    }

}