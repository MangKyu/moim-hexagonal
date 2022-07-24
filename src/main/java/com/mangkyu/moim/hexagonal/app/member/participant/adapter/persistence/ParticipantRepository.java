package com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    ParticipantEntity findByMember_LoginId(String loginId);

    Optional<ParticipantEntity> findByMember_Id(Long id);

}
