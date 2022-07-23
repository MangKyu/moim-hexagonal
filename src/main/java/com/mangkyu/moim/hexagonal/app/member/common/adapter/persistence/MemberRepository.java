package com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByLoginId(String email);

}
