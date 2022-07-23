package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Long> {

}
