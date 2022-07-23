package com.mangkyu.moim.hexagonal.member.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByEmail(String email);

}
