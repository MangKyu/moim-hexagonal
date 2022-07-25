package com.mangkyu.moim.hexagonal.app.member.common.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birth;
    private Gender gender;
    private String email;
    private String loginId;
    private String password;

    @ElementCollection(targetClass = MemberRole.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<MemberRole> roles = new HashSet<>();

}
