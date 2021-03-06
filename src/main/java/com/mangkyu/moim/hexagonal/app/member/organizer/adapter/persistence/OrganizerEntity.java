package com.mangkyu.moim.hexagonal.app.member.organizer.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence.MemberEntity;
import lombok.*;
import lombok.experimental.Delegate;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class OrganizerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String belongs;

    @Delegate
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MemberEntity member;

}
