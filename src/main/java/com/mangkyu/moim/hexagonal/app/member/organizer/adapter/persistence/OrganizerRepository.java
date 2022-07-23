package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Long> {

    OrganizerEntity findByMember_LoginId(String loginId);

    Optional<OrganizerEntity> findByMember_Id(Long id);
}
