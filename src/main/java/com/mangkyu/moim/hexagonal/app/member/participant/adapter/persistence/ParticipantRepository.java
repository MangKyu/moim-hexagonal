package com.mangkyu.moim.hexagonal.app.member.participant.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

}
