package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.adapter.persistence.MemberEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrganizerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String belongs;

    @OneToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

}
